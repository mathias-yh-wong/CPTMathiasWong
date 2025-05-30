import arc.*;
import java.awt.Color;

public class CPTMathiasWong{
	public static void main(String[] args){
		
		//Setting up user interface in console
		Console c = new Console("Blackjack", 1280, 720);
		c.setDrawColor(Color.GRAY);
		
		//Main menu
		

		
		//Prompting user for input on what they want to do
		c.println("Options - Enter:"); 
		c.println("P for play");
		c.println("L for leaderboard");
		c.println("H for help");
		c.println("Q for quit");
		char charMain = c.getChar();
		
		//Directing to specific method
		if(Character.toUpperCase(charMain) == 'P'){
			playGame(c);
		}else if(Character.toUpperCase(charMain) == 'L'){
			//viewLeaderboard(c);
		}else if(Character.toUpperCase(charMain) == 'Q'){
			c.closeConsole();
		}else if(Character.toUpperCase(charMain) == 'H'){
			helpMenu(c);
		}else{
			c.println("Not a valid input.");
		}
		
	}
	
	public static void playGame(Console c){
		
		c.println("Enter your username");
		String strName = c.readLine();
		int intMoney = 1000;
		
		viewLeaderboard(c, strName, intMoney);
		
	}
	
	public static void viewLeaderboard(Console c, String name, int money){
		
	}
	
	public static void helpMenu(Console c){
		
	}
	
	
}
