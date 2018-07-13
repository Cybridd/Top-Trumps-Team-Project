package commandline;

/**
 * Deck class is the class which contains an array of top trumps cards, and the descriptions
 * of the attributes for the cards. This class is constructed from information provided by the
 * game class in the case of player hands, or a text file in the case of the MainDeck subclass.
 * <ul>
 *  <li>The deck's name</li>
 *  <li>The cards in the deck (or hand) </li>
 *  <li>The descriptors of the attributes </li>
 * </ul>
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Deck 
{
	protected String deckName;  // name of the deck
	protected ArrayList<Card> deckOfCards = new ArrayList<Card>();  // arraylist of card objects
	protected String[] attDescripts; // string array of attribute descriptions
	
	/**
	 * Default constructor, to allow instantiation of MainDeck class with different
	 * constructor
	 */
	public Deck() {
		
	}
	
	/**
	 * Constructor used for instantiation of player hands
	 * @param attributeNames array of attribute descriptions
	 */
	public Deck(String[] attributeNames) {
		attDescripts = attributeNames;		
	}
	
	/**
	 * Method for adding a single card to the card array
	 * @param c Card object to be added
	 */
	public void addCard(Card c) {
		deckOfCards.add(c);
	}
	
	/**
	 * Method for adding an array of card objects to the 
	 * card array
	 * @param cards card array to be added
	 */
	public void addCards (Card[] cards) {
		deckOfCards.addAll(Arrays.asList(cards));
	}
	
	/**
	 * Method for getting the name of the deck
	 * @return name of deck
	 */
	public String getName()
	{
		return deckName;
	}
	
	/**
	 * Method for getting the attribute descriptions
	 * @return string array of attribute descriptions
	 */
	public String[] getDescripts()
	{
		return attDescripts;
	}
	
	/**
	 * Method for getting a single attribute description
	 * @param a index of attribute
	 * @return attribute description string
	 */
	public String getDescript(int a)
	{
		return attDescripts[a];
	}
	
	/**
	 * Method for getting the attribute numbers of the
	 * next card in the hand
	 * @return array of attribute number integers
	 */
	public int[] getTopCardAttributes()
	{
		return deckOfCards.get(0).getAttributes();
	}
	
	/**
	 * Method for getting the next card object in the
	 * hand or deck
	 * @return card object at index 0
	 */
	public Card showCard()
	{
		return deckOfCards.get(0);
	}
	
	/**
	 * Method for getting and removing the next card
	 * object in the hand or deck
	 * @return card object at index 0
	 */
	public Card getCard()
	{
		return deckOfCards.remove(0);
	}
	
	/**
	 * Method for getting size of card object arraylist
	 * @return size of arraylist
	 */
	public int getLength()
	{
		return deckOfCards.size();
	}

	/**
	 * Method to print a list of cards and their attributes to the command line.
	 * Only to be called when the "-t" command line flag is entered.
	 */
	public void printDeck2Log() {
		// Print table headings to the log file so that it is clear what the attributes are
		OutputLogger.addToLog(String.format("%13s %9s %9s %9s %9s %9s",
				"Ship Name", getDescript(0), getDescript(1),
				getDescript(2), getDescript(3), getDescript(4)));

		// Print details on each card in the deck to the log file.
		for (Card card : deckOfCards) {
			OutputLogger.addToLog(card.printCardDetails());
		}
	}
}
