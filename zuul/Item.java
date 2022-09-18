
/**
 * This class represents an item which may be put
 * in a room in the game of Zuul.
 * 
 * @author Lynn Marshall 
 * @version A3 Solution
 * 
 * @author Marwan Zeid 101185876
 * @version 2022.03.19
 */
public class Item
{
    // description of the item
    private String description;
    
    // name of the item
    private String name;
    
    // weight of the item in kilgrams 
    private double weight;

    /**
     * Constructor for objects of class Item.
     * 
     * @param name The name of the item
     * @param description The description of the item
     * @param weight The weight of the item
     */
    public Item(String name, String description, double weight)
    {
        this.description = description;
        this.name = name;
        this.weight = weight;
    }

    /**
     * Returns a description of the item, including its
     * description and weight.
     * 
     * @return  A description of the item
     */
    public String getDescription()
    {
        return name + ": " + description + " that weighs " + weight + "kg.";
    }
    
    /**
     * Returns the name of the item.
     * 
     * @return The name of the item
     */
    public String getName()
    {
        return name;
    }
}
