package commandline;

/**
 * Game class is the class which contains the methods that represent steps in playing
 * the Top Trumps game. This class is constructed from information provided from the
 * GameRunner class consisting of:
 * <ul>
 *  <li>the number of AI opponents</li>
 *  <li>the human player itself </li>
 * </ul>
 */
import java.util.ArrayList;
import java.util.Random;

public class Game 
{
	private int numPlayers, activePlayer, attributeChoice, numRounds, numDraws, eliminations;  // number of players, index of active player, player statistics
	private boolean winner;  // variable for testing if game has been won
	private Player[] currentPlayers;  // array of player objects representing current players
	private ArrayList<Card> communalPile;  // array of card objects representing cards in communal pile
	private Card[] currentCards;
	private MainDeck currentDeck;  //  MainDeck object
	private boolean logData; // set to true if "-t" flag is passed in command line mode
	private String roundResult;
	
	private int [] roundsWon = {0,0,0,0,0};  // int array for easy exporting of round wins per player
	private String gameWinner;  // name of player who won
	
	
	/** Constructor for the Game class. Creates a MainDeck object
	 * from the text file, shuffles the new object, generates the
	 * appropriate number of AIPlayer objects, deals the deck
	 * out into player's hands and picks a random starting active
	 * player.
	 * @param AIplayers number of desired opponents
	 * @param creator HumanPlayer object created from input information
	 * @param printTestLogs boolean for whether to print to test log
	 */
	protected Game(int AIplayers, HumanPlayer creator, boolean printTestLogs)

	{
		// If log data flag is set, set field to true and create new / clear log file
		logData = printTestLogs;
		if (logData) {
			OutputLogger.createLogFile();
	}

		numPlayers = AIplayers + 1; // Need an extra place for HumanPlayer
		numDraws = 0;
		numRounds = 0;
		eliminations = 0;
		communalPile = new ArrayList<Card>();  // communal pile is an arrayList because it will need to change size
		currentPlayers = new Player[numPlayers];
		gameWinner = "";
		
		currentDeck = new MainDeck("StarCitizenDeck.txt");  // import the Star Citizen deck
		
		// If data logging is on: print unshuffled deck to log
		if (logData) {
			OutputLogger.addToLog("Unshuffled Deck:");
			currentDeck.printDeck2Log();
			OutputLogger.addLogSectionSplit();
		}

		currentDeck.shuffle();  // shuffle the deck before play
		
		// If data logging is on: print shuffled deck to log
		if (logData) {
			OutputLogger.addToLog("Shuffled Deck:");
			currentDeck.printDeck2Log();
			OutputLogger.addLogSectionSplit();
		}
		
		creator.createDeck(currentDeck.attDescripts);  // create the player hand (instantiating Deck object)
		
		currentPlayers[0] = creator;  // set the human player to player 0
		for (int i = 1; i < numPlayers; i++)
		{
			currentPlayers[i]  = new AIPlayer("AI" + i, currentDeck.attDescripts);  // generate selected number of AI players 
		}
		
		dealHands();  // deal out the shuffled deck to player hands
		
		// If data logging is on: print each player's deck information (num cards, and card details)
		if (logData) {
			for (Player player : currentPlayers) {
				OutputLogger.addToLog("Player: "+player.playerName+"'s Deck  -  Num cards = "+player.getHandSize());
				player.logDeckInformation();
				OutputLogger.addLogSectionSplit();
			}
			// Last Log of the game set up is complete. Indicate that game starts in log file for reference.
			OutputLogger.addToLog("\n    GAME START\n");
			OutputLogger.addLogSectionSplit();
		}

		activePlayer = new Random().nextInt(numPlayers); // randomly select the first active player
	}
	
	/**
	 * Method for printing game stats 
	 * to command line and returning them
	 * to the web page
	 */
	protected String gameStats()
	{		
		DatabaseInteraction db = new DatabaseInteraction();
		db.viewData();
		int gameNo = Integer.parseInt(db.getTotalGameNumber()) + 1;
		gameWinner = currentPlayers[activePlayer].getName();

		db.enterData(gameNo, numDraws, gameWinner, numRounds, roundsWon[0], roundsWon[1], roundsWon[2], roundsWon[3], roundsWon[4]);
		
		System.out.println("\nGame Stats:");
		
		System.out.println(String.format(
					"Winner: %s%n"
				+  	"Number of Rounds: %d%n"
				+ 	"Number of Draws: %d%n"
				,gameWinner, numRounds, numDraws));
		return String.format(
				"Winner: %s%n"
			+  	"Number of Rounds: %d%n"
			+ 	"Number of Draws: %d%n"
			,gameWinner, numRounds, numDraws);
	}
	
	/**
	 * Method for dealing out the shuffled deck
	 * cards to the hands of each player
	 */
	protected void dealHands()
	{
		for (int i = 0; i < currentDeck.numCards; i++)
		{
			currentPlayers[i % numPlayers].addCardtoHand(currentDeck.getCard());
		}
	}
	
	/**
	 * Method for starting a round of top trumps. Creates new 
	 * currentCards object, and asks players for their attribute
	 * selection.
	 */
	protected void startRound()
	{
		// If data logging is on: Print round information (round num and active player) for reference.
		if (logData) {
			OutputLogger.addToLog("Round "+(numRounds+1)+" Information\n");
			OutputLogger.addToLog("Current active player = "+currentPlayers[activePlayer].getName());
		}

		// Array to store cards from each player to compare during the round
		currentCards = new Card[numPlayers];
		
		// If data logging is on: print table headers for card information in currentCards array
		if (logData) {
			OutputLogger.addToLog(String.format("Cards in play this round:\n"
					+ "%11s %13s %9s %9s %9s %9s %9s",
					"Player Name", "Ship Name",
					currentDeck.getDescript(0),
					currentDeck.getDescript(1),
					currentDeck.getDescript(2),
					currentDeck.getDescript(3),
					currentDeck.getDescript(4)));
		}
	}
	
	protected int playRound()
	{
		// Get the active player to choose the attribute to compare
		attributeChoice = currentPlayers[activePlayer].selectAttribute();
		return attributeChoice;
	}
	
	/**
	 * Method for finishing a round of top trumps. Moves each card
	 * in play to the currentCards array, calculates the winner and
	 * calls the appropriate round win condition method. Also checks
	 * for players with 0 cards and eliminates them, and declares a
	 * a winner if all but one players have been eliminated.
	 */
	protected void finishRound()
	{
		// Get each player to hand in the top card on their deck
		for (int i = 0 ; i < numPlayers ; i++)
		{
			if (currentPlayers[i] != null) {
				currentCards[i] = currentPlayers[i].removeCardFromHand();
			} else {
				currentCards[i] = null;
			}
			
			/*
			 * If data logging is on: print card information on cards in currentCards array
			 * including player that played it.
			 */
			if (logData) {
				if (currentPlayers[i] != null) {
					OutputLogger.addToLog(String.format(
					"%11s %13s %9s %9s %9s %9s %9s",
					currentPlayers[i].getName(),
					currentCards[i].getName(),
					currentCards[i].getAttribute(0),
					currentCards[i].getAttribute(1),
					currentCards[i].getAttribute(2),
					currentCards[i].getAttribute(3),
					currentCards[i].getAttribute(4)
					));
				}
			}
		}
		
		
		// Find maximum value of all players cards
		int max = 0;
		int maxIndex = 0;
		boolean draw = false;
		for (int i = 0 ; i < numPlayers ; i++)
		{
			if (currentCards[i] != null) {
				if (currentCards[i].getAttribute(attributeChoice) > max) {
					// If the current player's card is greater than the max, set this to max and set draw to false
					// Cannot be a draw if this is higher than previous max
					max = currentCards[i].getAttribute(attributeChoice);
					maxIndex = i;
					draw = false;
				} else if (currentCards[i].getAttribute(attributeChoice) == max) {
					// if player's card is the same as max, we have a draw until it is overwritten by a new max
					draw = true;
				}
			}
		}
		
		/*
		 * If data logging is on: print information on:
		 *    - the attribute selected by the active player
		 *    - the result of the round (draw / win + who won)
		 */
		if (logData) {
			OutputLogger.addToLog("Active Player ("+currentPlayers[activePlayer].getName()+") has selected attribute: "
					+ currentDeck.getDescript(attributeChoice));
			if (draw) {
				OutputLogger.addToLog("This round resulted in a draw");
			} else {
				OutputLogger.addToLog("This round was won by: "+currentPlayers[maxIndex].getName());
			}
		}
		
		numRounds++;  // increment the number of rounds played
		
		// Call different methods depending on draw or win condition
		if (draw) {
			processDraw(currentCards);
		} else {
			processWin(maxIndex, currentCards);
		}
		
		// If data logging is on: print out the non-eliminated players' deck information now round is over
		if (logData) {
			for (Player player : currentPlayers) {
				if (player != null) {
					OutputLogger.addToLog("\nPlayer: "+player.playerName+"'s Deck  -  Num cards = "+player.getHandSize());
					player.logDeckInformation();
				}
			}
			OutputLogger.addLogSectionSplit();
		}
		
		for(int i = 0; i < numPlayers; i++)
		{
			if (currentPlayers[i] != null) {
				// Eliminate players with 0 cards
				System.out.println("" + currentPlayers[i].getName() + "'s cards remaining: " + currentPlayers[i].getHandSize());
				if(currentPlayers[i].getHandSize() == 0)
				{
					System.out.println(currentPlayers[i].getName() + " has been eliminated.");
					currentPlayers[i] = null;
					eliminations++;
				}
			}
		}
		
		for(int i = 0; i < numPlayers; i++) {
				// declare overall winner if any
				if(numPlayers - eliminations == 1 && currentPlayers[i] != null /* && currentPlayers[i].getHandSize() == 40*/)
				{
					System.out.println("The winner of the game is " + currentPlayers[i].getName() + "!");
					
					// If game is over, print winner to log file
					if (logData) {
						OutputLogger.addToLog("The winner of the game is: "+currentPlayers[i].getName());
					}
					winner = true;
				}
		}
	}
	
	/**
	 * Method for performing actions if round is a draw
	 * @param currentCards cards currently in play in this round
	 */
	private void processDraw(Card[] currentCards)
	{
		roundResult = "It was a draw!";
		System.out.println(roundResult);

		for (Card card: currentCards) {
			if (card != null) {
				communalPile.add(card);  // add the current cards to the communal pile
			}
		}
		
		/* 
		 * If data logging is on: print out the cards in the communalPile array.
		 * (draw results in cards being added to pile)
		 */
		if (logData) {
			OutputLogger.addToLog("\nCards in communal pile:");
			for (Card card : communalPile) {
				OutputLogger.addToLog(card.getName());
			}
		}
		numDraws++;  // increment the number of draws in this game
	}
	
	/**
	 * Method for performing actions if someone wins the round
	 * @param winningPlayer player that one the round
	 * @param currentCards cards currently in play in this round
	 */
	private void processWin(int winningPlayer, Card[] currentCards)
	{
		roundResult = "Player: "+currentPlayers[winningPlayer].getName()+" is the winner of the round!";
		System.out.println(roundResult);

		for (int i = 0; i < numPlayers; i++)
		{
			if(currentCards[i] != null)  // If the index does not refer to an eliminated player...
			{
				currentPlayers[winningPlayer].addCardtoHand(currentCards[i]);  // add the current cards to winner's hand
			}
	  }

		roundsWon[winningPlayer] += 1; // increment rounds won value to input into the database 

		if (communalPile.size() > 0) {
			Card[] tempCardArray = new Card[communalPile.size()];
			communalPile.toArray(tempCardArray); 
			communalPile.clear();
			currentPlayers[winningPlayer].addCardsToHand(tempCardArray);  //  add communal pile cards (if any) to winner's hand
			
			/* 
			 * If data logging is on: print out the cards in the communalPile array.
			 * This is mainly to test that all cards are removed when a player wins after a draw(s)
			 */
			if (logData) {
				OutputLogger.addToLog("\nCards in communal pile:");
				for (Card card : communalPile) {
					OutputLogger.addToLog(card.getName());
				}
			}
		}
		activePlayer = winningPlayer;  // set winner to active player for next round
	}
	
	/** 
	 * 	Getter methods for variables in Game class. 
	 * 	Used primarily by GameRunner class to pass
	 * 	information to RESTAPI
	 * @return various Game variables
	 */
	protected Player[] getPlayers()
	{
		return currentPlayers;
	}
	
	protected Player getPlayer(int player)
	{
		return currentPlayers[player];
	}
	
	protected Deck getDeck()
	{
		return currentDeck;
	}
	
	protected Card[] getCurrentCards()
	{
		return currentCards;
	}
	
	protected ArrayList<Card> getCommunalPile()
	{
		return communalPile;
	}
	/** 
	 * 	Get whether someone has won the game. Used by 
	 *  program logic in CLI mode and finishRound 
	 *  Javascript method in web page.
	 * @return winner boolean game win condition
	 */
	protected boolean isWinner()
	{
		return winner;
	}
	
	protected int getActivePlayer()
	{
		return activePlayer;
	}
	
	protected String getRoundResult()
	{
		return roundResult;
	}
}
