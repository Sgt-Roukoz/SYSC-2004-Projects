import java.util.Stack;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *  
 * 
 * 
 * @author  Michael Kolling and David J. Barnes 
 * @version 2006.03.30
 * 
 * @author Lynn Marshall
 * @version A3 Solution
 * 
 * @author Marwan Zeid 101185876
 * @version 2022.03.19
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;
    private Stack<Room> previousRoomStack;
    private Item currentItem;
    private int itemPickUps;
        
    /**
     * Create the game and initialise its internal map, as well
     * as the previous room (none) and previous room stack (empty).
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        previousRoom = null;
        previousRoomStack = new Stack<Room>();
        currentItem = null;
        itemPickUps = 0;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office, transporter;
        Item cookie1, cookie2, cookie3, chair1, chair2, chair3, chair4, bar, computer1, computer2, computer3, tree1, tree2;
        Beamer beamer1, beamer2;
        
        // create some items
        chair1 = new Item("chair", "a wooden chair",5);
        chair2 = new Item("chair", "a wooden chair",5);
        chair3 = new Item("chair", "a wooden chair",5);
        chair4 = new Item("chair", "a wooden chair",5);
        bar = new Item("Bar", "a long bar with stools",95.67);
        computer1 = new Item("PC", "a PC",10);
        computer2 = new Item("Mac", "a Mac",5);
        computer3 = new Item("PC", "a PC",10);
        tree1 = new Item("tree", "a fir tree",500.5);
        tree2 = new Item("tree", "a fir tree",500.5);
        cookie1 = new Item("cookie", "a cookie",0.2);
        cookie2 = new Item("cookie", "a cookie",0.2);
        cookie3 = new Item("cookie", "a cookie",0.2);
        beamer1 = new Beamer();
        beamer2 = new Beamer();
       
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        transporter = new TransporterRoom();
        
        // put items in the rooms
        outside.addItem(tree1);
        outside.addItem(tree2);
        outside.addItem(cookie2);
        theatre.addItem(cookie1);
        theatre.addItem(beamer1);
        pub.addItem(bar);
        pub.addItem(cookie3);
        lab.addItem(chair2);
        lab.addItem(computer1);
        lab.addItem(chair3);
        lab.addItem(computer2);
        office.addItem(chair4);
        office.addItem(computer3);
        office.addItem(beamer2);
        
        // initialise room exits
        outside.setExit("east", theatre); 
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        office.setExit("north", transporter);
        
        transporter.setExit("anywhere", office); // the "office" destination doesn't matter

        currentRoom = outside;  // start game outside
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed
     * @return true If the command ends the game, false otherwise
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look(command);
        }
        else if (commandWord.equals("eat")) {
            eat(command);
        }
        else if (commandWord.equals("back")) {
            back(command);
        }
        else if (commandWord.equals("stackBack")) {
            stackBack(command);
        } 
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("charge")) {
            charge(command);
        }
        else if (commandWord.equals("fire")) {
            fire(command);
        }
        // else command not recognised.
        return wantToQuit;
    }
    
    /**
     * Print out room description and currently held items
     */
    private void printRoomAndItem(Room thisRoom)
    {
        System.out.println(thisRoom.getLongDescription());
        if (currentItem != null) {
            System.out.println("You are currently carrying:" + "\n    " + currentItem.getDescription());
        }
        else
        {
            System.out.println("You are not carrying anything.");
        }
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print a cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommands());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * If we go to a new room, update previous room and previous room stack.
     * 
     * @param command The command to be processed
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            previousRoom = currentRoom; // store the previous room
            previousRoomStack.push(currentRoom); // and add to previous room stack
            currentRoom = nextRoom;
            printRoomAndItem(currentRoom);
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param command The command to be processed
     * @return true, if this command quits the game, false otherwise
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /** 
     * "Look" was entered. Check the rest of the command to see
     * whether we really want to look.
     * 
     * @param command The command to be processed
     */
    private void look(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Look what?");
        }
        else {
            // output the long description of this room
            printRoomAndItem(currentRoom);
        }
    }
    
    /** 
     * "Eat" was entered. Check the rest of the command to see
     * whether we really want to eat.
     * 
     * @param command The command to be processed
     */
    private void eat(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Eat what?");
            return;
        }
        
        if (itemPickUps == 5) {
            System.out.println("You are not hungry.");
        }
        else
        {
            if (currentItem.getName().equals("cookie"))
            {
                currentItem = null;
                itemPickUps = 5;
                System.out.println("You have eaten and are no longer hungry.");
            }
            else
            {
                System.out.println("You do not have food.");
            }
        }
    }
    
    /** 
     * "Back" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param command The command to be processed
     */
    private void back(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Back what?");
        }
        else {
            // go back to the previous room, if possible
            if (previousRoom==null) {
                System.out.println("No room to go back to.");
            } else {
                // go back and swap previous and current rooms,
                // and put current room on previous room stack
                Room temp = currentRoom;
                currentRoom = previousRoom;
                previousRoom = temp;
                previousRoomStack.push(temp);
                // and print description
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }
    
    /** 
     * "StackBack" was entered. Check the rest of the command to see
     * whether we really want to stackBack.
     * 
     * @param command The command to be processed
     */
    private void stackBack(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("StackBack what?");
        }
        else {
            // step back one room in our stack of rooms history, if possible
            if (previousRoomStack.isEmpty()) {
                System.out.println("No room to go stack back to.");
            } else {
                // current room becomes previous room, and
                // current room is taken from the top of the stack
                previousRoom = currentRoom;
                currentRoom = previousRoomStack.pop();
                // and print description
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }
    
    /** 
     * "Take" was entered. Check the rest of the command to see
     * whether we really want to take an item.
     * 
     * @param command The command to be processed
     */
    private void take(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        
        String itemName = command.getSecondWord();
        Item toTake = null;
        
        if (itemPickUps == 0) {
            if (!itemName.equals("cookie")) {
                System.out.println("You are too hungry to pick up anything, maybe look for a cookie");
                return;
            }
        }
        
        if (currentItem == null) {
            toTake = currentRoom.removeItem(itemName);                       
            if (toTake != null) {
                currentItem = toTake;
                System.out.println("You have picked up " + itemName);
                itemPickUps--;
            }
            else
            {
                System.out.println("That item is not in the room.");
            }
        }
        else
        {
            System.out.println("You are already holding something.");
            return;
        }
    }
    
    /** 
     * "Drop" was entered. Check the rest of the command to see
     * whether we really want to drop an item.
     * 
     * @param command The command to be processed
     */
    private void drop(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Drop what?");
            return;
        }        
                    
        if (currentItem != null) {
            currentRoom.addItem(currentItem);
            System.out.println("You have dropped " + currentItem.getName() + "\n");
            currentItem = null;
        } 
        else
        {
            System.out.println("You have nothing to drop.");
        }
    }
    
    /** 
     * Checks if a beamer is currently equipped
     * 
     * @return Returns true if beamer is equipped, otherwise false
     */
    private boolean checkBeamerEquipped() {
        if (currentItem == null || !currentItem.getName().equals("beamer"))
        {
            System.out.println("No beamer equipped.");
            return false;
        }
        
        return true;
    }
    
    /** 
     * "Charge" was entered. Check the rest of the command to see
     * whether we really want to charge the beamer.
     * 
     * @param command The command to be processed
     */
    private void charge(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Charge what?");
            return;
        }
        
        if (!checkBeamerEquipped()) { return; }
        
        if (((Beamer) currentItem).isCharged())
        {
            System.out.println("Beamer already charged");
        }
        else
        {
            System.out.println("Room memorized");
            ((Beamer) currentItem).charge(currentRoom);
        }
    }
    
    /** 
     * "Fire" was entered. Check the rest of the command to see
     * whether we really want to fire the beamer.
     * 
     * @param command The command to be processed
     */
    private void fire(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Fire what?");
            return;
        }
        
        if (!checkBeamerEquipped()) { return; }
        
        if (((Beamer) currentItem).isCharged())
        {
            previousRoom = currentRoom; // store the previous room
            previousRoomStack.push(currentRoom); // and add to previous room stack
            System.out.println("Teleporting to stored room...");
            currentRoom = ((Beamer) currentItem).fire();
            printRoomAndItem(currentRoom);
        }
        else
        {
            System.out.println("Beamer is not charged.");
        }
    }
}
