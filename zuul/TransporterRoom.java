import java.util.Random;
import java.util.ArrayList;

/**
 * Class TransporterRoom
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Transporter Room" is a special type of room that, when exited, sends
 * you to a random room in the game.
 * 
 * 
 * @author Marwan Zeid 101185876
 * @version 2022.03.19
 */
public class TransporterRoom extends Room
{
    //Used to generate random integers
    private Random rand;

    /**
     * Constructor for objects of class TransporterRoom
     */
    public TransporterRoom()
    {
        super("in a transporter room...");
        rand = new Random();
    }

    /**
     * Returns a random room, independent of the direction parameter.
     * 
     * @param direction Ignored
     * @return A randomly selected room
     */
    public Room getExit(String direction) 
    {
        return findRandomRoom();
    }
    
    /**
     * Choose a random room.
     * 
     * @return The room we end up in upon leaving this one.
     */
    private Room findRandomRoom(){
        ArrayList<Room> rooms = Room.getRooms();
        int randomIndex = rand.nextInt(rooms.size());
        
        return rooms.get(randomIndex);
    }
}
