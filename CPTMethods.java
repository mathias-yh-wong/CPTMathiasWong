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
		int intCards = 0;
		int intValue = 0;
		int intSuit = 0;
		
		for(intSuit = 0; intSuit <= 3; intSuit++){
			for(intValue = 0; intValue <= 12; intValue++){
				intDeck[intCards][0] = intValue;
				intDeck[intCards][1] = intSuit;
				intDeck[intCards][2] = (int) (Math.random() * 100 + 1);
				intCards++;
			}
		}
		
		int intVTemp;
		int intSTemp;
		int intRTemp;
		
		for(int intCount2 = 0; intCount2 < intCards-1; intCount2++){
			for(int intCount = 0; intCount < intCards-1; intCount++){
				if(intDeck[intCount][2] > intDeck[intCount+1][2]){
					// Swapping card value
					intVTemp = intDeck[intCount][0];
					intDeck[intCount][0] = intDeck[intCount+1][0];
					intDeck[intCount+1][0] = intVTemp;
					// Swapping suit value
					intSTemp = intDeck[intCount][1];
					intDeck[intCount][1] = intDeck[intCount+1][1];
					intDeck[intCount+1][1] = intSTemp;
					// Swapping random integer value
					intRTemp = intDeck[intCount][2];
					intDeck[intCount][2] = intDeck[intCount+1][2];
					intDeck[intCount+1][2] = intRTemp;
				}
			}
		}
		
		for(int intCount = 0; intCount < intCards; intCount++){
			System.out.println(intDeck[intCount][0] + " - " + intDeck[intCount][1] + " - " + intDeck[intCount][2]);
		}
		
		return intDeck;
		
	}
	
	public static String cardText(int intValue, int intSuit){
		String[] strValues = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
		String[] strSuits = {"diamonds", "clubs", "hearts", "spades"};
		return strValues[intValue] + strSuits[intSuit];
	}
	
}
