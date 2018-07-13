package commandline;

/**
 * AIPlayer class is a sub-class of the player class. This class inherits the
 * instance variables and methods of the Player class and implements the
 * selectAttribute() method. This class does NOT have the responsibilities of
 * instantiating a new game and selecting a number of opponents.
 */
public class AIPlayer extends Player
{
	/**
	 * Constructor to allow instantiation of a new AIPlayer
	 * object. Uses constructor of superclass Player
	 * @param name
	 * @param deckAttrNames
	 */
	public AIPlayer(String name, String[] deckAttrNames)
	{
		super(name, deckAttrNames);
	}
	
	/**
	 * Method selecting the attribute with which the
	 * AI player will play. The attribute with the highest
	 * number is automatically selected.
	 * @return integer index of selected attribute
	 */
	public int selectAttribute()
	{
		int choice = -1;
		int highest = -1;
		int[] attributeVals = playerHand.getTopCardAttributes();
		for(int i = 0; i < attributeVals.length; i++)
		{
			if(attributeVals[i] > highest)
			{
				highest = attributeVals[i];
				choice = i;
			}
		}
		return choice;
	}
}
