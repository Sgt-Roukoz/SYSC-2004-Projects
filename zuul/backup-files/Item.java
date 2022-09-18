
/**
 * Class Item - an item in an adventure game. 
 * 
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds the information about an item stored in a room.
 *
 * @author Marwan Zeid 101185876
 * @version March 5, 2022
 */
public class Item
{
    private double weight;
    private String description;

    /**
     * Create an item described "description" with weight "weight" in lbs.
     * Description is something like "a large book" or "a pink pen".
     * 
     * Weight is inputted as a double. 
     * 
     * @param description String representing the description of the item
     * @param weight Double representing the weight of the item in pounds (lbs).
     */
    public Item(String description, double weight)
    {
        // initialise instance variables
        this.description = description;
        this.weight = weight;
    }

    /**
     * Returns a string containing the description of an item along with its weight
     * 
     * @return Returns a string representation of the item's information
     */
    public String getDescription()
    {
        String fullDesc = description + " Weight: " + weight + " lbs";
        return fullDesc;
    }
}
