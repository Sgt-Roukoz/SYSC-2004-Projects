
/**
 * The ClockDisplay12 class implements a digital clock display for a
 * analog-style 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00a.m. (midnight) to 11:59p.m. (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling, David J. Barnes and Marwan Zeid
 * @version 2022.01.29
 */
public class ClockDisplay12
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private String currentHour; // Used to correctly display 12 hour clock
    private String currPeriod; // Used to store current Period (AM or PM)
    
    private static String AM = "a.m.";
    private static String PM = "p.m.";
    
    /**
     * Constructor for ClockDisplay12 objects. This constructor 
     * creates a new clock set at 12:00a.m..
     */
    public ClockDisplay12()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        currPeriod = AM;
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay12 objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     * 
     * @param hour The hour at which the clock is set to. Valid hours are integers from
     * 1 to 12, otherwise it defaults to 12.
     * @param minute The minute at which the clock is set to. Valid minutes are integers from
     * 0 to 59, otherwise it defaults to 0.
     * @param period The period at which the clock is set to. Valid periods are "a.m." or "p.m.,"
     * otherwise it defaults to "a.m."
     */
    public ClockDisplay12(int hour, int minute, String period)
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        if (period.equals(AM) || period.equals(PM)) 
        {
            currPeriod = period;
        }
        else
        {
            currPeriod = AM;
        }
        setTime(hour, minute, currPeriod);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            if (hours.getValue() == 11 && currPeriod.equals(AM)) {
                currPeriod = PM;
            }
            else {
                currPeriod = AM;
            }
            
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour,
     * minute, and period
     * 
     * @param hour The hour at which the clock is set to. Valid hours are integers from
     * 1 to 12, otherwise it doesn't change.
     * @param minute The minute at which the clock is set to. Valid minutes are integers from
     * 0 to 59, otherwise it doesn't change.
     * @param period The period at which the clock is set to. Valid periods are "a.m." or "p.m.,"
     * otherwise it defaults to "a.m."
     */
    public void setTime(int hour, int minute, String period)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        if (period.equals(AM) || period.equals(PM)) 
        {
            currPeriod = period;
        }
        else
        {
            currPeriod = AM;
        }
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     * 
     * @return Returns a string of the form HH:MMperiod, ex: "12:00a.m."
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        if (hours.getValue() == 0) {
            currentHour = "12";
        } 
        else {
            currentHour = String.valueOf(hours.getValue());
        }
        
        displayString = currentHour + ":" + 
                        minutes.getDisplayValue() + currPeriod;
    }
}
