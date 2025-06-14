import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

//Program Header

//Name: Mathias Wong
//Name of program: CPTMathiasWong
//Date updated: 12 June 2025
//Version number: V1.0

public class CPTMathiasWong{
	public static void main(String[] args){
		
		//boolean variable + loop to keep game running
		boolean blnGame = true;
		
		while(blnGame == true){
		
			//Setting up console
			Console c = new Console("Blackjack", 1280, 720);
			CPTMethods.consoleSetup(c);
			c.repaint();
			
			//Getting character from user keyboard
			char charMain = c.getChar();
			charMain = Character.toUpperCase(charMain);
			System.out.println("Entered character: "+charMain);
			
			//Directing to specific method
			if(charMain == 'P'){
				playGame(c);
			}else if(charMain == 'L'){
				viewLeaderboard(c);
			}else if(charMain == 'Q'){
				c.closeConsole();
			}else if(charMain == 'H'){
				helpMenu(c);
			}else if(charMain == 'S'){
				funnyJoke(c);
			}
		
		}
		
	}
	
	public static void playGame(Console c){
		
		//declaring + initializing variables
		//gameplay loop for play again
		boolean blnPlay = true;
		//username variable
		String strName = "";
		//money variable
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
		while(blnPlay == true && intMoney > 0){
			
			c.clear();
			
			//prompting user for bet amount
			c.println("Current balance: $"+ intMoney);
			c.println();
			c.print("Place your bet: $");
			int intBet = c.readInt();
			c.clear();
			
			//if bet is bigger than they can afford
			if(intMoney < intBet){
				c.println("Not enough money.");
				c.sleep(1000);
				return;
			}else if(intBet <= 0){
				//if bet is invalid
				c.println("Invalid bet.");
				c.sleep(1000);
				playGame(c);
			}
			intMoney = intMoney - intBet;
			
			//creating deck of cards array
			int intDeck[][] = CPTMethods.deckOfCards();
			
			//dealing cards + declaring variables
			
			//player hand array
			int[][] intPlayer = new int[5][2];
			
			//dealer hand array
			int[][] intDealer = new int[5][2];
			
			//counts number of cards in player's hand
			//2 at the start
			int intPlayerCount = 2;
			
			//counts number of cards in dealer's hand
			//2 at the start
			int intDealerCount = 2;
			
			//order of the shuffled deck
			//universal variable for both player and dealer
			//0 is 1st card
			int intDeckNum = 0;
			
			//sum of player's hand
			int intPlayerSum = 0;
			
			//sum of dealer's hand
			int intDealerSum = 0;
			
			//player turn ends when double down
			boolean blnDoubleDown = false;
			
			//tracking if player hits/stands off the 2 cards
			boolean blnPlayerHit = false;
			
			//variable to store text of player's current card
			String strPlayerTemp;
			
			//variable to store text of dealer's current card
			String strDealerTemp;
			
			
			//loading data into intPlayer + intDealer arrays
			for(int intCount = 0; intCount <= 1; intCount++){
				//card 1 - player
				intPlayer[intCount][0] = intDeck[intDeckNum][0];
				//card 2 - player
				intPlayer[intCount][1] = intDeck[intDeckNum++][1];
				//card 1 - dealer
				intDealer[intCount][0] = intDeck[intDeckNum][0];
				//card 2 - dealer
				intDealer[intCount][1] = intDeck[intDeckNum++][1];
			}
			
			//dealer's first card is dealt face up on the table
			c.sleep(250);
			c.println("The dealer's first card is: ");
			//getting text for dealer's first card
			strDealerTemp = CPTMethods.cardText(intDealer[0][0], intDealer[0][1]);
			System.out.println("dealer card: "+strDealerTemp);
			c.println(strDealerTemp);
			c.println();
			
			//showing the player's hand
			c.sleep(250);
			c.println("Your hand:");
			//getting text for player's two cards
			for(int intCount = 0; intCount <= 1; intCount++){
				strPlayerTemp = CPTMethods.cardText(intPlayer[intCount][0], intPlayer[intCount][1]);
				c.println(strPlayerTemp);
				c.sleep(250);
				System.out.println("player card: "+strPlayerTemp);
			}
			
			//calculating sum of player's hand in CPTMethods
			intPlayerSum = CPTMethods.handValue(intPlayer, intPlayerCount);
			System.out.println("sum: "+intPlayerSum);
			
			c.sleep(500);
			//blackjack scenario
			if(intPlayerSum == 21 && intPlayerCount == 2){
				c.println();
				c.println("BLACKJACK! You get 3x your bet.");
				intMoney = intMoney + intBet * 3;
				c.sleep(500);
				System.out.println("money: "+intMoney);
				c.println();
				c.println("-------------------------");
			}else{
				if(intPlayerSum == 9 || intPlayerSum == 10 || intPlayerSum == 11){
					c.println();
					c.println("Your total is " + intPlayerSum + ". Do you want to double down? Enter D to double down.");
					c.println();
					char charDoubleDown = c.getChar();
					charDoubleDown = Character.toUpperCase(charDoubleDown);
					if(charDoubleDown == 'D' && intMoney >= intBet * 2 ){
						intBet = intBet * 2;
						
						//load data for player's third card
						intPlayer[intPlayerCount][0] = intDeck[intDeckNum][0];
						intPlayer[intPlayerCount][1] = intDeck[intDeckNum][1];
						
						//get text for player's third card
						String strDDCard = CPTMethods.cardText(intPlayer[intPlayerCount][0], intPlayer[intPlayerCount][1]);
						//incrementing deckNum so doesn't deal the same card
						intDeckNum++;
						//incrementing playerCount to represent number of cards player hands
						intPlayerCount++;
						
						c.println("You doubled down. Your bet is now doubled and this will be your final card.");
						c.println();
						c.println("Your new card is: "+strDDCard);
						//calculating new value of player's hand
						intPlayerSum = CPTMethods.handValue(intPlayer, intPlayerCount);
						System.out.println("new sum: "+intPlayerSum);
						c.println("Your hand's total is: "+intPlayerSum);
						//setting double down to true and ending player's turn
						blnDoubleDown = true;
					}else if(intMoney < intBet * 2){
						c.println();
						c.println("Not enough money.");
					}else{
						c.println("You chose to not double down.");
					}
				}
			}
			
			//hit or stay
			c.println();
			if(blnDoubleDown == false && intPlayerSum < 21){
				//boolean variable for hit loop
				boolean blnHitAgain = true;
				
				//loop for player hitting again
				while(blnHitAgain == true && intPlayerSum < 21 && intPlayerCount < 5){
					c.println("Hit or Stay? LMB for Hit, RMB for Stay");
					int intMouse;
					
					while (true) {
						
						intMouse = c.currentMouseButton();
						if(intMouse == 1){
							blnPlayerHit = true;
							//load data for player's third card
							intPlayer[intPlayerCount][0] = intDeck[intDeckNum][0];
							intPlayer[intPlayerCount][1] = intDeck[intDeckNum][1];
							
							strPlayerTemp = CPTMethods.cardText(intPlayer[intPlayerCount][0], intPlayer[intPlayerCount][1]);
							c.println();
							c.println("Your new card is: "+strPlayerTemp);
							
							//incrementing deckNum so it pulls next card
							intDeckNum++;
							//incrementing playerCount so it updates number of cards in player's hand
							intPlayerCount++;
							
							//calculating new value of player's hand
							intPlayerSum = CPTMethods.handValue(intPlayer, intPlayerCount);
							
							System.out.println("new sum: "+intPlayerSum);
							c.println("Your hand's total is: "+intPlayerSum);
							c.println();
							
							//prevent from grabbing mouse input many times after click
							while(c.currentMouseButton() != 0){
								c.sleep(100);
							}
							
							break;
							
						}else if(intMouse == 3){
							//if user right clicks to stay, breaks loop
							blnHitAgain = false;
							c.println();
							c.println("You stayed. It is now the dealer's turn.");
							c.println();
							c.println("---------------------------");
							
							//prevent from grabbing mouse input many times after click
							while(c.currentMouseButton() != 0){
								c.sleep(100);
							}
							
							break;
						}
					
					}
					
				}
				
			}
			
			//5 card win scenario, continue to skip and avoid winning twice
			if(intPlayerCount == 5 && intPlayerSum <= 21){
				c.sleep(500);
				c.println("You got 5 cards! You automatically win 3x your bet.");
				intMoney = intMoney + intBet * 3;
				c.println("Press any key to continue.");
				c.getChar();
				System.out.println("money: "+intMoney);
				continue;
			}else if(intPlayerSum > 21){
				//if player gets over 21 and busts
				c.sleep(500);
				c.println("You busted! Your sum is over 21.");
			}else{
				c.sleep(500);
				//calculating sum of dealer's hand
				intDealerSum = CPTMethods.handValue(intDealer, intDealerCount);
				//loop checking and allowing dealer to draw until sum is over 17
				while(intDealerSum < 17){
					//load data for dealer's third card
					intDealer[intDealerCount][0] = intDeck[intDeckNum][0];
					intDealer[intDealerCount][1] = intDeck[intDeckNum][1];
					intDeckNum++;
					intDealerCount++;
					intDealerSum = CPTMethods.handValue(intDealer, intDealerCount);
				}
				
				//boolean for hiding dealer's hand
				boolean blnHideDealerHand = false;
				
				//two cases where dealer doesn't show hand
				//if player immediately stands and dealer then wins
				if(blnPlayerHit == false && intPlayerSum < intDealerSum && intDealerSum <= 21){
					blnHideDealerHand = true;
				}
				
				//if dealer gets 5 card win
				if(intDealerCount == 5 && intDealerSum <= 21 && intDealerSum > intPlayerSum){
					blnHideDealerHand = true;
				}
				
				//comparing player's sum and dealer's sum
				if(intDealerSum > 21 || intPlayerSum > intDealerSum){
					//player wins
					c.println("You win!");
					intMoney = intMoney + intBet * 2;
				}else if(intPlayerSum == intDealerSum){
					//player draws
					c.println("A tie. Your bet is returned.");
					intMoney = intMoney + intBet;
				}else{
					//player loses
					c.println("You lose.");
				}
				System.out.println("money: "+intMoney);
				
				if(blnHideDealerHand == false){
					//revealing dealer's hand
					c.println();
					c.println("Dealer's hand is: ");
					for(int intCount = 0; intCount < intDealerCount; intCount++){
						strDealerTemp = CPTMethods.cardText(intDealer[intCount][0], intDealer[intCount][1]);
						c.println(strDealerTemp);
					}
					c.println();
					c.println("The dealer's sum is: "+intDealerSum);
					c.sleep(1000);
					c.println();
				}
			}
			
			//asking if user wants to play again
			c.println();
			c.println("Play Again? Y/N");
			char charPlayAgain = c.getChar();
			charPlayAgain = Character.toUpperCase(charPlayAgain);
			if(charPlayAgain == 'Y'){
				//continue while loop
				blnPlay = true;
				if(intMoney <= 0){
					//if player has no money left
					c.println("Not enough money left to play again.");
					c.println("Press any key to return to menu.");
					c.getChar();
				}
			}else if(charPlayAgain == 'N'){
				//breaks the while loop by setting boolean to false
				blnPlay = false;
			}
			
		}
		
		//Outputting username + money to leaderboard file
		TextOutputFile leaderboard = new TextOutputFile("leaderboard.txt", true);
		leaderboard.println(strName);
		leaderboard.println(intMoney);
		leaderboard.close();
		
		return;
		
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
		
		leaderboard.close();
		
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
		String strSpaces = "";
		
		c.println("Player Name             Score");
		c.println("-----------------------------");
		
		//for loop to print name and score + calculate the spaces needed in between to keep format
		for(int intCount = 0; intCount < intPlayers; intCount++){
			String strName = strLeaderboard[intCount][0];
			String strScore = strLeaderboard[intCount][1];
			strSpaces = "";
			for(int intCount2 = strName.length(); intCount2 < 24; intCount2++){
				strSpaces = strSpaces + " ";
			}
			c.println(strName + strSpaces + strScore);
		}
		
		//prompting user to return to main menu
		c.println();
		c.println("Press any key to return to main menu.");
		c.getChar();
		return;
		
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
			c.println();
			c.println("Press any key to return to main menu.");
			c.getChar();
			return;
		}else if(charHelp == '3'){
			//displays betting rules
			c.println("BETTING:");
			c.println("Win: 2x your bet");
			c.println("Tie: Get your bet back");
			c.println("Lose/Bust: Lose your bet");
			c.println();
			c.println("Press any key to return to main menu.");
			c.getChar();
			return;
		}else if(charHelp == '4'){
			//displays special rules
			c.println("SPECIAL RULES:");
			c.println("Blackjack: If your first two cards equal 21, you win 3x your bet.");
			c.println("Double Down: If your first two cards total 9, 10 or 11, you can double your bet and draw one more card.");
			c.println("5 Cards: If you draw 5 cards without busting, you automatically win (2x).");
			c.println();
			c.println("Press any key to return to main menu.");
			c.getChar();
			return;
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
			c.println("Press any key to return to main menu.");
			c.getChar();
			return;
		}else if(charHelp == 'q' || charHelp == 'Q'){
			//returns to main menu
			c.clear();
			return;
		}else{
			c.clear();
			c.println("Invalid input.");
			helpMenu(c);
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
		c.println("Press any key to return to main menu.");
		c.getChar();
		return;
		
	}
	
	
}
	
