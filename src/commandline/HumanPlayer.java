package commandline;
import java.util.*;

/**
 * HumanPlayer class is a sub-class of the player class. This class inherits the
 * instance variables and methods of the Player class and implements the
 * selectAttribute() method. This class also performs the following roles:
 * <ul>
 * 	<li>selecting the number of AI players</li>
 * 	<li>instantiating a new top trumps game</li>
 * </ul>
 */
public class HumanPlayer extends Player
{
	private int selection;
	private boolean isOnline;
	Scanner scanner2 = new Scanner(System.in);  // scanner required for reading from console
	
	/**
	 * Constructor to allow instantiation of HumanPlayer object
	 * @param name player name string
	 * @param online boolean value representing game mode
	 */
	public HumanPlayer(String name, boolean online)
	{
		isOnline = online;
		playerName = name;
		System.out.println("Creating a new game now!");
	}
	
	/**
	 * Method for instantiating the human
	 * player's hand (deck object)
	 * @param deckAttributes attribute descriptions for deck in use
	 */
	public void createDeck(String[] deckAttributes)
	{
		playerHand = new Deck(deckAttributes);
	}
	
	/**
	 * Method for getting user input from the console
	 * to select the attribute with which they wish to
	 * play
	 * @return integer index of selected attribute
	 */
	public int selectAttribute()
	{
		return selection;
	}
	
	/** 
	 * Method for manually setting the chosen attribute.
	 * Used in online mode to pass in the chosen attribute and
	 * end the input selection loop.
	 * @param choice
	 */
	public void setSelection(int choice)
	{
		selection = -1;
		if(!isOnline)  // Was trying to avoid adding a boolean for the type of game, but needed to avoid the call to Scanner object as this halts the program
		{
			Card cardToPlay = playerHand.showCard(); //  print name of card to console
			System.out.print(String.format(
					"The Card you are playing this round is the: "+cardToPlay.getName()+"%n"  //  print details of card to console
					+ "Please Select the attribute you wish to play this round:%n"
					+ "    1.) %-11s %d%n"
					+ "    2.) %-11s %d%n"
					+ "    3.) %-11s %d%n"
					+ "    4.) %-11s %d%n"
					+ "    5.) %-11s %d%n"
					+ "Please select the attribute using the numbers 1 to 5:%n",
					playerHand.attDescripts[0],cardToPlay.getAttribute(0),
					playerHand.attDescripts[1],cardToPlay.getAttribute(1),
					playerHand.attDescripts[2],cardToPlay.getAttribute(2),
					playerHand.attDescripts[3],cardToPlay.getAttribute(3),
					playerHand.attDescripts[4],cardToPlay.getAttribute(4)));
			while(selection < 0 || selection > 4)
			{
				try 
				{
					selection = Integer.parseInt(scanner2.nextLine()) - 1;
					if(selection < 0 || selection > 4)
					{
						System.out.println("Invalid choice, please try again.");
					}
				}
				catch (NumberFormatException e)
				{
					System.out.println("Invalid choice, please try again.");
				}
			}
		}
		else 
		{
			selection = choice - 1;
		}
	}
}
