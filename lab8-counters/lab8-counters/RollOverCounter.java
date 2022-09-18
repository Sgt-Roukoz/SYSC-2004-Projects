/** 
 * Class RollOverCounter is a representatin of a counter that resets or "rolls over"
 * when the the counter has reached past it's maximum bound.
 * 
 * Utilizes the Counter superclass
 * 
 * @author Marwan Zeid 101185876
 * @version 03.15.2022
 */
public class RollOverCounter extends Counter
{

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to DEFAULT_MINIMUM, and which counts between
     * DEFAULT_MINIMUM and DEFAULT_MAXIMUM, inclusive.
     */
    public RollOverCounter()
    {
        super();
    }

    /**
     * Constructs a new RollOverCounter whose current count is
     * initialized to minCount, and which counts between
     * minCount and maxCount, inclusive.
     * 
     * @param minCount Integer representing the minimum number the counter can represent
     * @param maxCount Integer representing the maximum number the counter can represent
     */
    public RollOverCounter(int minCount, int maxCount)
    {
        super(minCount, maxCount);
    }

    /**
     * Increment this counter by 1.
     */
    public void countUp()
    {
        // If we've reached the maximum count, invoking this
        // method rolls the counter over to its minimum value.
        if (!isAtMaximum()) {
            incrementCount();
        } else {
            reset();
        }
    }
}
