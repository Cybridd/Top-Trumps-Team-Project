package commandline;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * MainDeck class is the only sub-class of the Deck class. It inherits all the instance
 * variables and attributes of the superclass. This class is constructed from information 
 * provided by a text file, storing information on:
 * <ul>
 *  <li>The deck's name</li>
 *  <li>The cards in the deck (or hand) </li>
 *  <li>The descriptors of the attributes </li>
 * </ul>
 */
public class MainDeck extends Deck {
	
	private final int numAtts = 5; // constant representing number of attributes on a card
	protected final int numCards = 40;  // constant representing number of cards in the deck
		
	/**
	 * Constructor to allow instantiation of a MainDeck object. Information
	 * is read in from a text file containing deck name, attribute names and
	 * card information.
	 * @param deckFile name of text file containing deck information
	 */
	public MainDeck(String deckFile)
	{
		attDescripts = new String[numAtts];  // instantiate string array for attribute descriptions
		deckName = deckFile.substring(0, deckFile.length()-4);  // store name of deck
		try
		{
			File fileIn = new File(deckFile);
    		Scanner deckReader = new Scanner(fileIn);
    		deckReader.next();  // skip over the word 'Description' in the text file
			while(deckReader.hasNext())
			{
				
				for (int i = 0; i < numAtts; i++)
				{
					attDescripts[i] = deckReader.next();  // store the attribute descriptions in the string array
				}
				
				for (int i = 0; i < numCards; i++)
				{
					String cardName = deckReader.next();  // store the card name (first token on line)
					String attributes = deckReader.nextLine();  // store the attribute numbers as a string (rest of line)
					
					this.deckOfCards.add(new Card(cardName, attributes));  // instantiate a new card object using stored information
				}															// and add it to the card array
				
			}
			
			deckReader.close();  // close scanner
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}
	}

	/**
	 * Method for shuffling the card arraylist. Moves through the arraylist temporarily
	 * storing the card at each index, replacing it with a card from another random 
	 * index and replacing that card with the temporary one.
	 */
	public void shuffle()
	{
		Random rand = new Random();
		
		for (int i = 0; i < numCards; i++)
		{
			int val = rand.nextInt(numCards);
			Card tempCard = deckOfCards.get(i);
			deckOfCards.set(i, deckOfCards.get(val));
			deckOfCards.set(val, tempCard);
		}
	}
	
}
