package commandline;

/**
 * Card class is the class which contains information on a single top trumps card.
 * This class is constructed from information provided on a card from the {@link Deck}
 * class, storing information on:
 * <ul>
 *  <li>The card's name</li>
 *  <li>The cards attribute values <strong><em>not the attribute descriptions</em></strong></li>
 *  <li>The number of attributes (as a constant)</li>
 * </ul>
 */
public class Card
{
    private String name;                    // Name of card
    private int[] attributeNums;            // Card attribute array
    protected final int numAttributes = 5;    // Number of attributes on card

    /**
     * Constructor for Card class. Creates card object from 2 strings
     * containing: the name of the card, and a string containing all
     * attributes (separated by whitespace)
     *
     * @param n Name of card
     * @param atts Attributes string (format: " 1 2 3 4 5")
     */
    public Card(String n, String atts)
    {
        name = n;

        // Split attributes from string to string array (trim to remove preceding space char)
        String[] attributes = atts.trim().split(" ");
        attributeNums = new int[numAttributes];

        // Parse temp attribute string array into attribute int array field
        for (int i = 0; i < numAttributes; i++)
        {
            //System.out.println(attributes[i]);
            attributeNums[i] = Integer.parseInt(attributes[i]);
        }
    }

    /**
     * Getter method for <code>name</code> field.
     *
     * @return The name of the card
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter method for <code>attibuteNums</code> array field.
     *
     * @return The array containing all attributes
     */
    public int[] getAttributes()
    {
        return attributeNums;
    }

    /**
     * Getter method for single value in <code>attibuteNums</code> array field.
     *
     * @param Index of value to return
     * @return The value stored at index <code>a</code> of <code>attributeNums</code>
     */
    public int getAttribute(int a)
    {
        return attributeNums[a];
    }
    
    /**
     * Method to return a string containing all information on the card in a standard format.
     * Can be called by logging methods to create standard card displays in the log file.
     * @return A string containing: the card name, attributes 1 - 5
     */
    public String printCardDetails() {
    	return String.format("%13s %9d %9d %9d %9d %9d", 
    			name, attributeNums[0], attributeNums[1], attributeNums[2], attributeNums[3], attributeNums[4]);
    }
}
