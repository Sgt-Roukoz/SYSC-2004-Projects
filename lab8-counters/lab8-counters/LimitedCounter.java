/** 
 * Class Limited is a representatin of a counter that stops counting up
 * when the the counter has reached it's maximum bound.
 * 
 * Utilizes the Counter superclass
 * 
 * @author Marwan Zeid 101185876
 * @version 03.15.2022
 */
public class LimitedCounter extends Counter
{
    /**
     * Constructs a new LimitedCounter whose current count is
     * initialized to superclass Counter's DEFAULT_MINIMUM, and which counts between
     * DEFAULT_MINIMUM and DEFAULT_MAXIMUM, inclusive.
     */
    public LimitedCounter()
    {
        super();
    }

    /**
     * Constructs a new LimitedCounter whose current count is
     * initialized to minCount, and which counts between
     * minCount and maxCount, inclusive.
     * 
     * @param minCount Integer representing the minimum number the counter can represent
     * @param maxCount Integer representing the maximum number the counter can represent
     */
    public LimitedCounter(int minCount, int maxCount)
    {
        super(minCount, maxCount);
    }

    /**
     * Increment this counter by 1.
     */
    public void countUp()
    {
        // If we've reached the maximum count, invoking this
        // method doesn't change the state of the counter.
        if (!isAtMaximum()){
            incrementCount();
        }
    }
}
