/**
 * The Counter class represents a basic counter which can count up and reset.
 * It stores a maximum value and minimum value representing the counters upper and lower bounds.
 * 
 * @author Marwan Zeid 101185876
 * @version 03.15.2022
 * 
 * @author  Marwan Zeid 101185876
 * @version 03.22.2022
 */
public abstract class Counter
{
    /** The current value of this counter. */
    private int count;

    /** The minimum value of this counter. */
    private int minimumCount;

    /** The maximum value of this counter. */
    private int maximumCount;

    /** The default minimum value of this counter. */
    private static final int DEFAULT_MINIMUM = 0;

    /** The default maximum value of this counter. */
    private static final int DEFAULT_MAXIMUM = 999;

    /**
     * Constructs a new Counter whose current count is
     * initialized to DEFAULT_MINIMUM, and which counts between
     * DEFAULT_MINIMUM and DEFAULT_MAXIMUM, inclusive.
     */
    public Counter()
    {
        minimumCount = DEFAULT_MINIMUM;
        maximumCount = DEFAULT_MAXIMUM;
        count = minimumCount;
    }

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to minCount, and which counts between
     * minCount and maxCount, inclusive.
     * 
     * @param minCount Integer representing the minimum number the counter can represent
     * @param maxCount Integer representing the maximum number the counter can represent
     */
    public Counter(int minCount, int maxCount)
    {
        minimumCount = minCount;
        maximumCount = maxCount;
        count = minimumCount;
    }

    /**
     * Returns the maximum value of this counter.
     * 
     * @return Returns an integer representing the counter's maximum bound
     */
    public int maximumCount()
    {
        return maximumCount;
    }

    /**
     * Returns the minimum value of this counter.
     * 
     * @return Returns an integer representing the counter's minimum bound
     */
    public int minimumCount()
    {
        return minimumCount;
    }

    /**
     * Returns this counter's current count.
     * 
     * @return Returns an integer representing the counters current count
     */
    public int count()
    {
        return count;
    }

    /**
     * Returns true if this counter is at its minimum value.
     * 
     * @return Returns a boolean, true if counter is at its minimum value, false otherwise
     */
    public boolean isAtMinimum()
    {
         return (count == minimumCount);
    }

    /**
     * Returns true if this counter is at its maximum value.
     * 
     * @return Returns a boolean, true if counter is at its maximum value, false otherwise
     */
    public boolean isAtMaximum()
    {
        return (count == maximumCount);
    }

    /**
     * Resets this counter to its minimum value.
     */
    public void reset()
    {
        count = minimumCount;
    }

    /**
     * Increment this counter by 1.
     */
    public void countUp()
    {
        count++;
    }
    
    /**
    * Decrement this counter by 1.
    */
    public void decrementCount()
    {
        count--;
    }
    
    /**
     * Sets the counter to its maximum value.
     */
    public void setToMaximum()
    {
        count = maximumCount;
    }
    
    /**
     * Abstract class for countDown methods
     */
    public abstract void countDown();
}
