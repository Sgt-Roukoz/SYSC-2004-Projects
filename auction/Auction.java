import java.util.*;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kolling.
 * @version 2006.03.30
 *
 * @author (of AuctionSkeleton) Lynn Marshall
 * @version 2.0
 * 
 * @author Marwan Zeid
 * @version 2022.02.12
 * 
 */
public class Auction
{
    /** The list of Lots in this auction. */
    private ArrayList<Lot> lots;

    /** 
     * The number that will be given to the next lot entered
     * into this auction.  Every lot gets a new number, even if some lots have
     * been removed.  (For example, if lot number 3 has been deleted, we don't
     * reuse it.)
     */
    private int nextLotNumber;

    /** Sets whether the auction is open or not */
    private boolean auctionOpen;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
        auctionOpen = true;

    }
    
    /**
     * Create a new auction using previous auction lot
     * 
     * @param oldAuction Old auction object
     */
    public Auction(Auction oldAuction)
    {
        if (oldAuction.auctionOpen != false) {
            lots = new ArrayList<Lot>();
            nextLotNumber = 1;
            auctionOpen = true;
        }
        else
        {
            lots = oldAuction.lots;
            nextLotNumber = oldAuction.nextLotNumber;
            auctionOpen = true;
        }
    }


    /**
     * Enter a new lot into the auction.  Returns false if the
     * auction is not open or if the description is null.
     *
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     *
     * Indicates whether or not there are no lots
     */
    public void showLots()
    {
        System.out.println("");
        if (!lots.isEmpty())
        {
            for(Lot lot : lots) {
                System.out.println(lot.toString());
            }
        }
        else
        {
            System.out.println("There are no lots");
        }
    }
    
    /**
     * Bid for a lot.
     * Prints a message indicating whether the bid is successful or not.
     *     
     * Prints whether or not the bid is successful.
     * If the bid is successful, also print the
     * lot number, high bidder's name, and the bid value.
     * If the bid is not successful, also print the lot number 
     * and high bid (but not the high bidder's name).
     * 
     * Returns false if the auction is closed, the lot doesn't
     * exist, the bidder is null, or the bid was not positive
     * and true otherwise (even if the bid was not high enough).
     *
     * @param number The lot number being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     * 
     * @return Returns true if bid was valid, otherwise returns false
     */
    public boolean bidFor(int lotNumber, Person bidder, long value)
    {
          Lot selectedLot = getLot(lotNumber);
          boolean success = false;
          
          System.out.println("");
          
          if (bidder == null)
          {
              System.out.println("Bidder does not exist");
              success = false;
          }
          else if(selectedLot != null && auctionOpen && value > 0) 
          {
              Bid bid = new Bid(bidder, value);
              boolean successful = selectedLot.bidFor(bid);
              if (successful) 
              {
                  System.out.println("The bid for lot number " +
                                   lotNumber + " by " + bidder.getName() + " for $" + value + " was successful");
              }
              else
              {
                  System.out.println("The bid for lot number " +
                                   lotNumber + " by " + bidder.getName() + " for $" + value + 
                                   " was not successful, highest bid: " + selectedLot.getHighestBid().getValue());
              }
              success = true;
          }
          else if(!auctionOpen)
          {
              System.out.println("Auction Closed");
              success = false;
          }
          else if(value < 0)
          {
              System.out.println("Bid value negative");
              success = false;
          }
          else if (selectedLot == null)
          {
              System.out.println("Lot does not exist");
              success = false;
          }
          
          return success;
    }


    /**
     * Return the lot with the given number.  
     *   
     * Returns null if the lot does not exist.
     *
     * @param lotNumber The number of the lot to return.
     *
     * @return the Lot with the given number
     */
    public Lot getLot(int lotNumber)
    {
        for(Lot lot : lots) {
            if (lot.getNumber() == lotNumber)
            {
                return lot;
            }
        }
        System.out.println("Lot does not exist");
        return null;
    }
    
    /**
     * Closes the auction and prints information on the lots.
     * First print a blank line.  Then for each lot,
     * its number and description are printed.
     * If it did sell, the high bidder and bid value are also printed.  
     * If it didn't sell, print that it didn't sell.
     *
     * Returns false if the auction is already closed, true otherwise.
     * 
     * @return Returns true if the auction was open, otherwise returns false
     */
    public boolean close()
    {
        if (!auctionOpen) {
            System.out.println("Auction already closed");
            return false;
        }
        else
        {
            auctionOpen = false;
            Iterator<Lot> it = lots.iterator();
            String highestBidderName;
            long highestBidderValue;
            while (it.hasNext())
            {
                Lot currLot = it.next();
                String details = currLot.getNumber() + ": " + currLot.getDescription() + " ";
                if (currLot.getHighestBid() == null) {
                    System.out.println(details + "was not sold");
                }
                else
                {
                    highestBidderName = currLot.getHighestBid().getBidder().getName();
                    highestBidderValue = currLot.getHighestBid().getValue();
                    System.out.println(details + "was sold to " + highestBidderName + " for $" + highestBidderValue);
                    it.remove(); // remove sold item from lot
                }
            }
            return true;
        }
    }
    
    /**
     * Returns an ArrayList containing all the items that have no bids so far.
     * (or have not sold if the auction has ended).
     * 
     * @return an ArrayList of the Lots which currently have no bids
     */
    public ArrayList<Lot> getNoBids()
    {
       ArrayList<Lot> noBidLots = new ArrayList<Lot>();
       for(Lot lot : lots) {
            if (lot.getHighestBid() == null)
            {
                noBidLots.add(lot);
            }
        }
       return noBidLots;
    }
    
    /**
     * Remove the lot with the given lot number, as long as the lot has
     * no bids, and the auction is open.  
     *
     * Returns true if successful, false otherwise (auction closed,
     * lot does not exist, or lot has a bid).
     *
     * @param number The number of the lot to be removed.
     * @return Returns true if removal is successful, false otherwise
     */
    public boolean removeLot(int number)
    {
        if (auctionOpen)
        {
            Iterator<Lot> it = lots.iterator();
            while (it.hasNext())
            {
                Lot currLot = it.next();
                if (currLot.getNumber() == number && currLot.getHighestBid() == null) {
                    it.remove();
                    return true;
                }
            }
        }
        
        return false;
    }   
}
