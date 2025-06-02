import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class CPTMathiasWong{
	public static void main(String[] args){
		
		//Setting up console
		Console c = new Console("Blackjack", 1280, 720);
		
		//Background colour
		c.setBackgroundColor(Color.WHITE);
		c.fillRect(0, 0, 1280, 720);
		
		//Logo
		BufferedImage logo = c.loadImage("logo.jpg");
		c.drawImage(logo, 50, 100);
		
		//Text + font setup
		Font font = c.loadFont("font1.otf", 120);
		c.setDrawFont(font);
		c.setDrawColor(Color.BLACK);
		c.drawString("BLACKJACK", 75, -10);
		
		//Main menu options + text/font setup
		Font smallFont = c.loadFont("font1.otf", 60);
		c.setDrawFont(smallFont);
		c.drawString(" (P) Play", 700, 300);
		c.drawString(" (L) Leaderboard", 700, 350);
		c.drawString(" (H) Help", 700, 400);
		c.drawString(" (Q) Quit", 700, 450);
		c.repaint();
		
		//Getting character from user keyboard
		char charMain = c.getChar();
		System.out.println(charMain);
		
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
		
		//repainting console
		c.setBackgroundColor(Color.WHITE);
		c.fillRect(0, 0, 1280, 720);
		c.repaint();
		
		//prompting user
		c.setTextColor(Color.BLACK);
		c.println("Enter your username");
		String strName = c.readLine();
		int intMoney = 1000;
		
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
		
	}
	
	public static void helpMenu(Console c){
		
	}
	
	
}
