/**
 * A Card is a playing card from an Anglo-American deck of cards.
 * 
 * @author Lynn Marshall
 * @author Marwan Zeid 101185876
 * @version 1.2 Mar. 01, 2022
 */
public class Card
{
    /** The card's suit: "hearts", "diamonds", "clubs", "spades". */
    private String suit;
    
    /** 
     * The card's rank, between 1 and 13 (1 represents the ace, 11 represents
     * the jack, 12 represents the queen, and 13 represents the king.)
     */
    private int rank;

    /**
     * Constructs a new card with the specified suit and rank.
     * 
     * @param suit The String representation of the card suit.
     * @param rank Integer representing the rank of the card, between 1 and 13
     */
    public Card(String suit, int rank)
    {
        this.suit = suit;
        if (rank < 1 || rank > 13)
        {
            this.rank = 1;
        }
        else
        {
            this.rank = rank;
        }
    }
    
    /**
     * Returns this card's suit.
     * 
     * @return Returns a string representation of the card's suit
     */
    public String suit()
    {
        return suit;
    }
    
    /**
     * Returns this card's rank.
     * 
     * @return Returns an integer representation of the card's rank
     */
    public int rank()
    {
        return rank;
    }
    
    /**
     * Determines if this card has the same rank as the specified card.
     * 
     * @param aCard Card object to be compared with
     * 
     * @retun Returns true if card has the same rank as aCard, otherwise returns false
     */
    public boolean hasSameRank(Card aCard)
    {
        return rank == aCard.rank();
    }
    
    /**
     * Determines if this card is equal to the specified card.
     * 
     * @param aCard Card object to be compared to
     * 
     * @return Returns true if aCard and the card are equivalent in rank and suit, otherwise false
     */
    public boolean isEqualTo(Card aCard)
    {
        return hasSameRank(aCard) && suit.equals(aCard.suit());
    }
}
