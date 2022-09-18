import java.util.ArrayList;

/**
 * Hand models a hand of cards held by a player.
 * 
 * @author Lynn Marshall
 * @author Marwan Zeid 101185876
 * @version 1.2 Mar. 01, 2022
 */
public class Hand
{
    /** 
     * The cards are stored in an array-list implementation of the
     * List collection.
     */
    private ArrayList<Card> cards;

    /**
     * Constructs a new, empty hand.
     */
    public Hand()
    {   
        cards = new ArrayList<Card>(0);
    }
    
    /**
     * Adds the specified card to this hand.
     * 
     * @param aCard The card to be added to this hand
     */
    public void addCard(Card aCard)
    {
        cards.add(aCard);
    }
     
   /**
     * Removes a card from this hand. Cards are removed in the order in
     * which they were added to the hand.
     * 
     * @return Returns a Card object representing the card played
     */
    public Card playCard()
    {
        Card returned = cards.get(0);
        cards.remove(0);
        return returned;
    }

    /**
     * Returns the number of cards that are currently in this hand.
     * 
     * @return Returns an integer representation of the size of this hand
     */    
    public int size()
    {
        return cards.size();
    }

    /**
     * Determines if this hand is empty.
     * 
     * @return Returns true if the cards in hand is empty, otherwise false
     */    
    public boolean isEmpty()
    {
        return cards.isEmpty();
    }

    /**
     * Returns a String that lists the ranks (but not the suits) of all the 
     * cards in this hand, starting with the first card and finishing with
     * the last card. For example, if the first card is the two of hearts, 
     * the second card is the five of diamonds, and the last card is the
     * queen of clubs, the String returned by this method will be: "2 5 12".
     * 
     * @return Returns a String representation of all the cards' ranks in this hand.
     */
    public String toString()
    {
        String hand = "";
        if (cards.size() == 0)
        {
            hand = "";
        }
        else if (cards.size() == 1)
        {
            hand += cards.get(cards.size()-1).rank();
        }
        else
        {
            for (int i = 0; i < cards.size()-1; i++ )
            {
                hand += cards.get(i).rank() + " ";
            }
            hand += cards.get(cards.size()-1).rank();
        }
        return hand;
    }
}
