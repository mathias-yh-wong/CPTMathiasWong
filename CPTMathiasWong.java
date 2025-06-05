import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class CPTMathiasWong{
	public static void main(String[] args){
		
		//boolean variable + loop to keep game running
		boolean blnGame = true;
		
		while(true){
		
			//Setting up console
			Console c = new Console("Blackjack", 1280, 720);
			CPTMethods.consoleSetup(c);
			c.repaint();
			
			//Getting character from user keyboard
			char charMain = c.getChar();
			System.out.println("Entered character: "+charMain);
			
			//Directing to specific method
			if(Character.toUpperCase(charMain) == 'P'){
				playGame(c);
			}else if(Character.toUpperCase(charMain) == 'L'){
				viewLeaderboard(c);
			}else if(Character.toUpperCase(charMain) == 'Q'){
				c.closeConsole();
			}else if(Character.toUpperCase(charMain) == 'H'){
				helpMenu(c);
			}else if(Character.toUpperCase(charMain) == 'S'){
				funnyJoke(c);
			}else{
				c.println("Invalid input. Please restart the game.");
				blnGame = true;
			}
		
		}
		
	}
	
	public static void playGame(Console c){
		
		//declaring + initializing variables
		boolean blnPlay = true;
		String strName = "";
		int intMoney = 1000;
		
		//repainting console
		c.setBackgroundColor(Color.WHITE);
		c.fillRect(0, 0, 1280, 720);
		c.repaint();
		
		//prompting user
		c.setTextColor(Color.BLACK);
		c.println("Enter your username");
		strName = c.readLine();
		
		//"statitan" cheat
		if(strName.equalsIgnoreCase("statitan")){
			intMoney = 1000000;
		}
		
		//while loop to replay game 
		while(blnPlay = true && intMoney > 0){
			
			c.clear();
			
			//prompting user for bet amount
			c.println("You have $" + intMoney + ". Place your bet:");
			int intBet = c.readInt();
			c.clear();
			if(intMoney < intBet){
				c.println("Not enough money.");
				c.sleep(1000);
				return;
			}else if(intBet <= 0){
				c.println("Invalid bet.");
				return;
			}
			intMoney = intMoney - intBet;
			
			//creating deck of cards array
			int intDeck[][] = CPTMethods.deckOfCards();
			
			//dealing cards + declaring variables
			int[][] intPlayer = new int[5][2];
			int[][] intDealer = new int[5][2];
			int intPlayerCount = 2;
			int intDealerCount = 2;
			int intDeckNum = 0;
			boolean blnDoubleDown = true;
			
			//loading data into intPlayer + intDealer arrays
			for(int intCount = 0; intCount <= 1; intCount++){
				intPlayer[intCount][0] = intDeck[intDeckNum][0];
				intPlayer[intCount][1] = intDeck[intDeckNum++][1];
				intDealer[intCount][0] = intDeck[intDeckNum][0];
				intDealer[intCount][1] = intDeck[intDeckNum++][1];
			}
			
			//dealer's first card is dealt face up on the table
			c.println("The dealer's first card is: ");
			String strDealerTemp = CPTMethods.cardText(intDealer[0][0], intDealer[0][1]);
			System.out.println("dealer card: "+strDealerTemp);
			c.println(strDealerTemp);
			c.println();
			
			//showing the player's hand
			c.println("Your hand:");
			for(int intCount = 0; intCount <= 1; intCount++){
				String strPlayerTemp = CPTMethods.cardText(intPlayer[intCount][0], intPlayer[intCount][1]);
				c.println(strPlayerTemp);
				System.out.println("player card: "+strPlayerTemp);
			}
			c.println();
			
			//calculating sum of player's hand in CPTMethods
			int intPlayerSum = CPTMethods.handValue(intPlayer, intPlayerCount);
			System.out.println("sum: "+intPlayerSum);
			
			//blackjack scenario
			if(intPlayerSum == 21){
				c.println("BLACKJACK! You get 3x your bet.");
				intMoney = intMoney + intBet * 3;
				System.out.println("money: "+intMoney);
			}else{
				blnDoubleDown = false;
			}
			
			//double down scenario
			if(intPlayerSum == 9 || intPlayerSum == 10 || intPlayerSum == 11){
				c.println("Your total is " + intPlayerSum + ". Do you want to double down? (Y/N)");
				char charDoubleDown = c.getChar();
				if(Character.toUpperCase(charDoubleDown) == 'Y'){
					intBet = intBet * 2;
					
					intPlayer[intPlayerCount][0] = intDeck[intDeckNum][0];
					intPlayer[intPlayerCount][0] = intDeck[intDeckNum][1];
					
					String strDDCard = CPTMethods.cardText(intPlayer[intPlayerCount-1][0], intPlayer[intPlayerCount][1]);
					intDeckNum++;
					intPlayerCount++;
					
					c.println("Your new card is: "+strDDCard);
					intPlayerSum = CPTMethods.handValue(intPlayer, intPlayerCount);
					c.println("Your hand's total is: "+intPlayerSum);
					blnDoubleDown = true;
				}
			}
			
			//hit or stay
			c.println();
			c.println("hit or stay pick one");
			char charHitStay = c.getChar();
			if(Character.toUpperCase(charHitStay) == 'h'){
				c.closeConsole();
			}else if(Character.toUpperCase(charHitStay) == 's'){
				c.closeConsole();
			}
			
			//asking if user wants to play again
			c.println("Play Again? Y/N");
			char charPlayAgain = c.getChar();
			if(Character.toUpperCase(charPlayAgain) == 'Y'){
				blnPlay = true;
			}else if(Character.toUpperCase(charPlayAgain) == 'N'){
				blnPlay = false;
			}
			
		}
		
		//Outputting username + money to leaderboard file
		TextOutputFile leaderboard = new TextOutputFile("leaderboard.txt");
		leaderboard.println(strName);
		leaderboard.println(intMoney);
		
	}
	
	public static void viewLeaderboard(Console c){
		
		//repainting console
		c.setBackgroundColor(Color.WHITE);
		c.fillRect(0, 0, 1280, 720);
		c.repaint();
		c.setTextColor(Color.BLACK);
		
		//opening leaderboard text file
		TextInputFile leaderboard = new TextInputFile("leaderboard.txt");
		
		//counting number of players in the leaderboard file
		int intLines = 0;
		while(leaderboard.eof() == false){
			leaderboard.readLine();
			intLines++;
		}
		leaderboard.close();
		int intPlayers = intLines/2;
		
		//setting up array
		String[][] strLeaderboard = new String[intPlayers][2];
		leaderboard = new TextInputFile("leaderboard.txt");
		
		//loading data into the array
		for(int intCount = 0; intCount < intPlayers; intCount++){
			strLeaderboard[intCount][0] = leaderboard.readLine();
			strLeaderboard[intCount][1] = leaderboard.readLine();
			System.out.println(strLeaderboard[intCount][0]);
			System.out.println(strLeaderboard[intCount][1]);
		}
		
		//sorting array
		
		String strPTemp;
		String strSTemp;
		
		for(int intCount2 = 0; intCount2 < intPlayers-1; intCount2++){
			for(int intCount = 0; intCount < intPlayers-1; intCount++){
				// Convert people's score from string to integer and compare
				if(Integer.parseInt(strLeaderboard[intCount][1]) < Integer.parseInt(strLeaderboard[intCount+1][1])){
					// Swap occurs
					// Swapping name
					strPTemp = strLeaderboard[intCount][0];
					strLeaderboard[intCount][0] = strLeaderboard[intCount+1][0];
					strLeaderboard[intCount+1][0] = strPTemp;
					// Swapping score
					strSTemp = strLeaderboard[intCount][1];
					strLeaderboard[intCount][1] = strLeaderboard[intCount+1][1];
					strLeaderboard[intCount+1][1] = strSTemp;
					
				}
			}
		}
		
		//printing the leaderboard
		for(int intCount = 0; intCount < intPlayers; intCount++){
			c.println(strLeaderboard[intCount][0] + " - " + strLeaderboard[intCount][1]);
		}
		
		//prompting user to return to main menu
		c.println();
		c.println("Enter spacebar to return to main menu.");
		char charLead = c.getChar();
		if(charLead == ' '){
			c.clear();
			return;
		}
		
	}
	
	public static void helpMenu(Console c){
		
		//repainting console
		c.setBackgroundColor(Color.WHITE);
		c.fillRect(0, 0, 1280, 720);
		c.repaint();
		c.setTextColor(Color.BLACK);
		
		//main help menu
		c.println("BLACKJACK HELP MENU");
		c.println();
		c.println("1. Objective");
		c.println("2. Basic Rules");
		c.println("3. Betting");
		c.println("4. Special Rules");
		c.println("5. Controls");
		c.println();
		c.println("Enter an option (1-4) OR Enter Q to return to main menu");
		
		//prompt user to get character tp 
		char charHelp = c.getChar();
		System.out.println(charHelp);
		c.clear();
		
		//displaying info for option selected + prompt return to help menu
		if(charHelp == '1'){
			//displays objective
			c.println("OBJECTIVE:");
			c.println("Get as close to 21 as possible without going over.");
			c.println("Beat the dealer's hand to win your bet.");
			c.println("Enter spacebar to return to the main menu");
			charHelp = c.getChar();
			if(charHelp == ' '){
				c.clear();
				helpMenu(c);
			}
		}else if(charHelp == '2'){
			//displays basic rules
			c.println("BASIC RULES:");
			c.println("Each player is dealt 2 cards.");
			c.println("Face cards (J, Q, K) are worth 10.");
			c.println("Aces are worth 11 unless it causes a bust, then they count for 1");
			c.println("Player can choose to HIT (draw a card) or STAY (end turn).");
			c.println("Dealer hits until 17 is reached.");
			c.println("Enter spacebar to return to the main menu");
			charHelp = c.getChar();
			if(charHelp == ' '){
				c.clear();
				helpMenu(c);
			}
		}else if(charHelp == '3'){
			//displays betting rules
			c.println("BETTING:");
			c.println("Win: 2x your bet");
			c.println("Tie: Get your bet back");
			c.println("Lose/Bust: Lose your bet");
			c.println("Enter spacebar to return to the main menu");
			charHelp = c.getChar();
			if(charHelp == ' '){
				c.clear();
				helpMenu(c);
			}
		}else if(charHelp == '4'){
			//displays special rules
			c.println("SPECIAL RULES:");
			c.println("Blackjack: If your first two cards equal 21, you win 3x your bet.");
			c.println("Double Down: If your first two cards total 9, 10 or 11, you can double your bet and draw one more card.");
			c.println("5 Cards: If you draw 5 cards without busting, you automatically win (2x).");
			c.println("Enter spacebar to return to the main menu");
			charHelp = c.getChar();
			if(charHelp == ' '){
				c.clear();
				helpMenu(c);
			}
		}else if(charHelp == '5'){
			//displays controls
			c.println("CONTROLS:");
			c.println();
			c.println("Main menu:");
			c.println("P - Play");
			c.println("L - View Leaderboard");
			c.println("H - Help");
			c.println("Q - Quit");
			c.println();
			c.println("During game:");
			c.println("Left mouse - hit");
			c.println("Right mouse - stay");
			c.println("D - Double down");
			c.println();
			c.println("Enter spacebar to return to the main menu");
			charHelp = c.getChar();
			if(charHelp == ' '){
				c.clear();
				helpMenu(c);
			}
		}else if(charHelp == 'q' || charHelp == 'Q'){
			//returns to main menu
			c.clear();
			return;
		}
		
		
	}
	
	public static void funnyJoke(Console c){
		
		//repainting console
		c.setBackgroundColor(Color.WHITE);
		c.fillRect(0, 0, 1280, 720);
		c.repaint();
		c.setTextColor(Color.BLACK);
				
		//prints joke and prompts user to return to main menu
		c.println("What do you call a magician who lost their magic?");
		c.sleep(1000);
		c.println("Ian!");
		c.println();
		c.println("Enter spacebar to return to the main menu");
		char charJoke = c.getChar();
		if(charJoke == ' '){
			c.clear();
			return;
		}
		
	}
	
	
}
	
