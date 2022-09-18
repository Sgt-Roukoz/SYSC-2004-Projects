
/**
 * This class represents an beamer, a special item 
 * which stores the current room when charged, and returns
 * the player to the stored room when fired.
 *
 * @author Marwan Zeid 101185876
 * @version 2022.03.19
 */
public class Beamer extends Item
{
    //Stores whether the beamer is charged or not
    private boolean charged;
    
    //Stores the currently "memorized" room
    private Room storedRoom;

    /**
     * Constructor for objects of class Beamer
     */
    public Beamer()
    {
        super("beamer", "a wonderful beamer", 1.0);
        storedRoom = null;
    }
    
    /**
     * Charges beamer and stores current room
     * 
     * @param currRoom the Room to be stored
     */
    public void charge(Room currRoom){
        charged = true;
        storedRoom = currRoom;
    }
    
    /**
     * Checks if beamer is charged
     * 
     * @return Returns true if beamer is charged, false otherwise
     */
    public boolean isCharged()
    {
        return charged;
    }
    
    /**
     * Fires beamer and send you to stored room
     * 
     * @return Returns the room currently stored.
     */
    public Room fire()
    {
        charged = false;
        return storedRoom;
    }
}
