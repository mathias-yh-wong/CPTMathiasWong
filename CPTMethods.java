import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class CPTMethods{
	public static void main(String[] args){
		Console c = new Console();
		
	}
	
	public static void consoleSetup(Console c){
		
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
		
	}
	
	public static int[][] deckOfCards(){
		
		int intDeck[][] = new int[52][3];
		intDeck[0][0] = 0;
		return intDeck;
		
	}

	
}
