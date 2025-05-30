import arc.*;

public class CPTMathiasWong{
	public static void main(String[] args){
		
		//Setting up user interface in console
		Console c = new Console(/*int intWidth, int intHeight*/);
		
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
			viewLeaderboard(c);
		}else if(Character.toUpperCase(charMain) == 'Q'){
			c.closeConsole();
		}else if(Character.toUpperCase(charMain) == 'H'){
			helpMenu(c);
		}else{
			c.println("Not a valid input.");
		}
		
	}
	
	public static void playGame(Console c){
		
	}
	
	public static void viewLeaderboard(Console c){
		
	}
	
	public static void helpMenu(Console c){
		
	}
	
	
}
