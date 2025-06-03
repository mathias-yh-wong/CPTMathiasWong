import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class CPTMathiasWong{
	public static void main(String[] args){
		
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
			}
		
		}
		
	}
	
	public static void playGame(Console c){
		
		//repainting console
		c.setBackgroundColor(Color.WHITE);
		c.fillRect(0, 0, 1280, 720);
		c.repaint();
		
		//prompting user
		c.setTextColor(Color.BLACK);
		c.println("Enter your username");
		String strName = c.readLine();
		int intMoney = 1000;
		
		//"statitan" cheat
		if(strName.equalsIgnoreCase("statitan")){
			intMoney = 1000000;
		}
		
		c.println("Your username is: "+strName);
		c.println("Your money is: "+intMoney);
		
		//creating deck of cards array
		int intDeck[][] = CPTMethods.deckOfCards();
		
		//Outputting username + money to leaderboard file
		TextOutputFile leaderboard = new TextOutputFile("leaderboard.txt", true);
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
		
		for(int intCount = 0; intCount < intPlayers; intCount++){
			c.println(strLeaderboard[intCount][0] + " - " + strLeaderboard[intCount][1]);
		}
		
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
	
