package commandline;

import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Please Enter Your Name: ");
			String name = scanner.nextLine();
			int numOpponents = 0;
			String mode = "";
			
			while (!(mode.equals("G") || mode.equals("S"))) {
				System.out.print("Do you want to view statistics or play a game? \nG = play game / S = statisitcs : ");
				mode = scanner.nextLine();
			
			}
				if (mode.equals("S")) {
					DatabaseInteraction printDB = new DatabaseInteraction();
					printDB.viewData();
					printDB.printString();
				}
				else if (mode.equals("G")) {


					while (numOpponents < 1 || numOpponents > 4) 
					{
						System.out.print("Welcome to Top trumps, How many opponents are you playing against?\n"
								+ "Please enter a value between 1 and 4: ");
						try
						{
							numOpponents = Integer.parseInt(scanner.nextLine());
						}

						catch (NumberFormatException e)
						{
							System.out.println("Invalid input, please try again.");
						}
					}
					HumanPlayer player = new HumanPlayer(name, false);
					GameRunner gamerunner = new GameRunner(numOpponents, player, writeGameLogsToFile);
					gamerunner.createGame();
					
					while(!gamerunner.isWinner())
					{
						gamerunner.startRound();
						if(gamerunner.getActivePlayer() == 0)
						{
							player.setSelection(0);
						}
						gamerunner.playRound();
						gamerunner.finishRound();
					}
					
					gamerunner.printStats();
				}
				

				System.out.println("Enter 'Q' if you would like to quit, \nor anything else to continue: ");
				if(scanner.nextLine().equals("Q"))
				{
					userWantsToQuit=true;
				}
		}

	}



}