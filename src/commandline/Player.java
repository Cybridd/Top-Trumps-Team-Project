package commandline;

/**
 * Player class is an abstract class which forms the basis of players in the game. This class
 * has two sub-classes of player type and is never constructed, but contains information belonging
 * to both sub-classes, storing information on:
 * <ul>
 *  <li>The player's name</li>
 *  <li>The player's game-playing statistics </li>
 *  <li>The player's hand represented as a deck object </li>
 * </ul>
 */
public abstract class Player 
{
	protected String playerName;  // name of player
	protected int numCards, roundsWon;  // number of cards in player's hand, number of rounds won
	protected Deck playerHand;  // deck object (card array) representing player's hand
	
	/**
	 * Default constructor
	 */
	public Player () {
		
	}
	
	/**
	 * Constructor to allow instantiation of 
	 * HumanPlayer and AIPlayer sub-classes
	 * @param name
	 * @param deckAttrNames
	 */
	public Player(String name, String[] deckAttrNames) {
		playerName = name;
		playerHand = new Deck(deckAttrNames);
	}
	/**
	 * Method for adding a single card to a 
	 * player's hand
	 * @param newCard card to be added
	 */
	public void addCardtoHand(Card newCard)
	{
		playerHand.addCard(newCard);
	}
	
	/**
	 * Method for adding an array of cards to
	 * a player's hand
	 * @param cardArray array of card objects to be added
	 */
	public void addCardsToHand(Card[] cardArray)
	{
		playerHand.addCards(cardArray);
	}
	
	/**
	 * Method for removing a card from a 
	 * player's hand
	 * @return card object removed
	 */
	public Card removeCardFromHand()
	{
		return playerHand.getCard();
	}
	
	/**
	 * Method for incrementing the number of
	 * rounds won statistic
	 */
	public void wonRound()
	{
		roundsWon++;
	}
	
	/**
	 * Method for getting the current size of
	 * a player's hand
	 * @return size of hand (card arraylist)
	 */
	public int getHandSize()
	{ 
		return playerHand.getLength();
	}
	
	/**
	 * Method for getting the player's name
	 * @return name string
	 */
	public String getName()
	{
		return playerName;
	}
	
	/**
	 * Abstract method to be implemented by the
	 * different types of players
	 * @return attribute selected
	 */
	abstract int selectAttribute();
	
	/**
	 * Method to print the contents of the player's deck to the log file.
	 * Only to be called when the "-t" flag is used.
	 */
	public void logDeckInformation() {
		playerHand.printDeck2Log();
	}
	
	public Deck getHand()
	{
		return playerHand;
	}
}
