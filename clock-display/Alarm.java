
/**
 * The Alarm class uses a digital clock display for a
 * Analog-style 12 hour clock. The clock shows hours, minutes and the period. The 
 * range of the clock is 12:00a.m. (midnight) to 11:59p.m. (one minute before 
 * midnight).
 * 
 * The Alarm stores the time set by the user, and stores whether the alarm is set or not.
 * 
 * @author Marwan Zeid
 * @version 2022.01.29
 */
public class Alarm
{

    private ClockDisplay12 alarmTime; // Used to store and display the alarm time.
    private boolean set; // Value indicating whether alarm is set or not

    /**
     * Constructor for Alarm objects. This constructor 
     * creates a new Alarm with the alarm set off and an alarm
     * time of 12:00 a.m.
     */
    public Alarm()
    {
        alarmTime = new ClockDisplay12(12, 0, "a.m.");
        set = false;
    }
    
    /**
     * Constructor for Alarm objects. This constructor 
     * creates a new Alarm with the alarm set off and an alarm
     * time set by parameters
     * 
     * @param hour The hour at which the alarm is set to. Valid hours are integers from
     * 1 to 12, otherwise it defaults to 12.
     * @param minute The minute at which the alarm is set to. Valid minutes are integers from
     * 0 to 59, otherwise it defaults to 0 minutes.
     * @param period The period at which the alarm is set to. Valid periods are "a.m." or "p.m.,"
     * otherwise it defaults to "a.m.".
     */
    public Alarm(int hours, int minutes, String period, boolean setting)
    {
        alarmTime = new ClockDisplay12(hours, minutes, period);
        set = setting;
    }
    
    /**
     * Set the time at which the alarm goes off
     * 
     * @param hour The hour at which the alarm is set to. Valid hours are integers from
     * 1 to 12, otherwise it doesn't change.
     * @param minute The minute at which the alarm is set to. Valid minutes are integers from
     * 0 to 59, otherwise it doesn't change.
     * @param period The period at which the alarm is set to. Valid periods are "a.m." or "p.m.,"
     * otherwise it defaults to "a.m."
     */
    public void setTime(int hours, int minutes, String period)
    {
        alarmTime.setTime(hours, minutes, period);
    }
    
    /**
     * Turn on Alarm
     */
    public void turnOn()
    {
        set = true;
    }
    
    /**
     * Turn off Alarm
     */
    public void turnOff()
    {
        set = false;
    }
    
    /**
     * Return the currently set alarm time.
     * 
     * @return Returns a string in the form HH:MMperiod representing the time the alarm is set to
     */
    public String getTime()
    {
        return alarmTime.getTime();
    }
    
    /**
     * Return whether or not the alarm is set
     * 
     * @return Returns a boolean value representing whether or not the alarm is set.
     */
    public boolean isSet()
    {
        return set;
    }
}
