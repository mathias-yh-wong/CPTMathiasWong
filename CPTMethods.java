import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class CPTMethods{
	public static void main(String[] args){
		Console c = new Console();
		
	}
	
	public static void consoleSetup(Console c){
		
		//method to set up graphics on console
		
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
		
		//method to create deck of cards + randomize
		
		//creating deck array
		int intDeck[][] = new int[52][3];
		int intCards = 0;
		int intValue = 0;
		int intSuit = 0;
		
		//loading data into array
		//because 12 max, 0 value is ace
		for(intSuit = 0; intSuit <= 3; intSuit++){
			for(intValue = 0; intValue <= 12; intValue++){
				intDeck[intCards][0] = intValue;
				intDeck[intCards][1] = intSuit;
				intDeck[intCards][2] = (int) (Math.random() * 100 + 1);
				intCards++;
			}
		}
		
		//variables to temporarily store data in bubble sort
		int intVTemp;
		int intSTemp;
		int intRTemp;
		
		//bubble sorting array
		for(int intCount2 = 0; intCount2 < intCards-1; intCount2++){
			for(int intCount = 0; intCount < intCards-1; intCount++){
				if(intDeck[intCount][2] < intDeck[intCount+1][2]){
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
		
		//printing to System to see card sort order
		for(int intCount = 0; intCount < intCards; intCount++){
			System.out.println(intDeck[intCount][0] + " - " + intDeck[intCount][1] + " - " + intDeck[intCount][2]);
		}
		
		return intDeck;
		
	}
	
	public static String cardText(int intValue, int intSuit){
		
		//method to replace value + suit integers with corresponding text
		
		String[] strValues = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
		String[] strSuits = {"diamonds", "clubs", "hearts", "spades"};
		return strValues[intValue] + " of " + strSuits[intSuit];
	}
	
	public static int handValue(int[][] intHand, int intCardCount){
		
		//method to count the sum of player's hand
		
		int intSum = 0;
		int intAce = 0;
		
		//calculating value of hand + adjusting for special rules
		for(int intCount = 0; intCount < intCardCount; intCount++){
			int intCardValue = intHand[intCount][0];
			//adjusting jack, king and queen values to 10
			if(intCardValue == 0){
				//adjusting ace value to 11
				intSum = intSum + 11;
				//counting number of aces in player hand
				intAce++;
			}else if(intCardValue >= 10){
				//adjusting jack, king and queen values to 10
				intSum = intSum + 10;
			}else{
				//+1 because java starts counting from 0, not 1
				intSum = intSum + (intCardValue + 1);
			}
		}
		
		//scenario if player has an ace but sum over 21
		//while loop because player could have two aces
		while(intAce > 0 && intSum > 21){
			//lowers ace count
			intAce--;
			//converts 11 to 1
			intSum = intSum - 10;
		}
		
		//returns variable
		return intSum;
	}
	
}
