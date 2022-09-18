import java.util.ArrayList;
import java.util.Random;

/**
 * Deck models a deck of 52 Anglo-American playing cards.
 * 
 * @author Lynn Marshall
 * @author Marwan Zeid 101185876
 * @version 1.2 Mar. 01, 2022
 */
public class Deck
{
    /** 
     * The cards are stored in a linked-list implementation of the
     * List collection.
     */
    private ArrayList<Card> cards;
    
    /** Lowest ranking card (the ace). */
    private static final int ACE = 1;
    
    /** Highest ranking card (the king). */
    private static final int KING = 13;
    
    /** 
     * Total number cards in the deck (4 suits, each with 13 cards of 
     * different ranks).
     */ 
    private static final int TOTAL_CARDS = 52;
    
    /** 
     * Some constants that define the Strings for the various suits.
     */ 
    private static final String HEARTS = "hearts";
    private static final String DIAMONDS = "diamonds";
    private static final String CLUBS = "clubs";
    private static final String SPADES = "spades";

    /**
     * Constructs a new, unshuffled deck containing 52 playing cards.
     */
    public Deck()
    {
        cards = new ArrayList<Card>(52);
        for (int i = ACE; i <= KING; i++)
        {
            cards.add(new Card(HEARTS, i));
            cards.add(new Card(DIAMONDS, i));
            cards.add(new Card(CLUBS, i));
            cards.add(new Card(SPADES, i));
        }
    }
    
    /**
     * Creates the 13 cards for the specified suit, and adds them
     * to this deck.
     * 
     * @param suit String representing the card suit being added
     */
    private void buildSuit(String suit)
    {
        for (int i = ACE; i <= KING; i++)
        {
            cards.add(new Card(suit, i));
        }
    }
 
    /**
     * Shuffles this deck of cards.
     */
    public void shuffle()
    {
        int first, second;
        Card firstCard, secondCard;
        Random r = new Random();
        for (int i = 0; i < 10000; i++)
        {
            first = r.nextInt(52);
            second = r.nextInt(52);
            
            firstCard = cards.get(first);
            secondCard = cards.get(second);
            
            cards.set(first, secondCard);
            cards.set(second, firstCard);
        }
    }
 
    /**
     * Removes a card from this deck.
     * 
     * @return Returns the card being dealt as a Card object
     */
    public Card dealCard()
    {
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
 
    /**
     * Determines if this deck is empty.
     * 
     * @return Returns a boolean, true if the deck is empty, otherwise false
     */
    public boolean isEmpty()
    {
        return cards.isEmpty();
    }
  
    /**
     * Returns the number of cards that are currently in the deck.
     * 
     * @return Returns an integer representing the size of the deck
     */
    public int size()
    {
        return cards.size();
    }
}
