package commandline;

import java.util.Scanner;

public class Tester {

	public static void test() {
		
		/*Deck starCitizen = new Deck("StarCitizenDeck.txt");
		
		System.out.println(starCitizen.getName());
		
		starCitizen.shuffle();
		
		for (int i = 0; i < 40; i++)
		{
			System.out.println(starCitizen.getCard(i).getName() + " " + starCitizen.getCard(i).getAttribute(0));
		}
		
		AIPlayer aiPlayer = new AIPlayer("AI1");
		for (int i =0; i < 5; i++)
		{
		aiPlayer.addCardtoHand(starCitizen.getCard(0));
		}
		System.out.println("Hand size: " + aiPlayer.getHandSize());*/
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Please Enter Your Name: ");
		String name = scanner.nextLine();
		int numOpponents = 0;
		while (numOpponents < 1 || numOpponents > 4) 
		{
			System.out.print("Welcome to Top trumps, How many opponents are you playing against?\n"
					+ "Please enter a value between 1 and 4:");
			numOpponents = scanner.nextInt();
		}
		
		HumanPlayer testPlayer = new HumanPlayer(name, false);
	}

	public static void logTest() {
		OutputLogger.createLogFile();
		OutputLogger.addToLog("This is a test log entry");
		OutputLogger.addToLog("This is a second entry");
	}
}
