
/**
 * The Alarm clock consists of an Analog-style 12 hour clock and an alarm with a set time.
 * The alarm is set by the user in the same format as the clock, and the clock display receives 
 * "ticks" (via the clockTick method) every minute and reacts by incrementing the display.
 * 
 * If the clock time matches the alarm time set by the user, and the alarm is turned on, the 
 * clock will "ring".
 *
 * @author Marwan Zeid
 * @version 2022.01.29
 */
public class AlarmClock
{
    
    private ClockDisplay12 clock; // Object to handle the clock itself.
    private Alarm alarm; //Object holding the alarm time and alarm settings.
    
    /**
     * Constructor for AlarmClock object, which sets the current time and alarm time to 12 AM
     * while also turning the alarm off.
     */
    public AlarmClock()
    {
        alarm = new Alarm();
        clock = new ClockDisplay12();
    }
    
    /**
     * Constructor for AlarmClock object, which sets the current time and alarm time based on parameters
     * while also setting the alarm to on or off based on parameters
     * 
     * @param hour The hour at which the clock is set to. Valid hours are integers from
     * 1 to 12, otherwise it doesn't change.
     * @param minute The minute at which the clock is set to. Valid minutes are integers from
     * 0 to 59, otherwise it doesn't change.
     * @param period The period at which the clock is set to. Valid periods are "a.m." or "p.m.,"
     * otherwise it defaults to "a.m."
     * @param alarmHour The hour at which the alarm is set to. Valid hours are integers from
     * 1 to 12, otherwise it doesn't change.
     * @param alarmMinute The minute at which the alarm is set to. Valid minutes are integers from
     * 0 to 59, otherwise it doesn't change.
     * @param alarmPeriod The period at which the alarm is set to. Valid periods are "a.m." or "p.m.,"
     * otherwise it defaults to "a.m."
     * @param set Indicates whether the alarm is turned on or not.
     */
    public AlarmClock(int hour, int minutes, String period, int alarmHour, int alarmMinute, String alarmPeriod, boolean set)
    {
        clock = new ClockDisplay12(hour, minutes, period);
        alarm = new Alarm(alarmHour, alarmMinute, alarmPeriod, set);
    }
    
    /**
     * Sets the time of the clock.
     * 
     * @param hour The hour at which the clock is set to. Valid hours are integers from
     * 1 to 12, otherwise it doesn't change.
     * @param minute The minute at which the clock is set to. Valid minutes are integers from
     * 0 to 59, otherwise it doesn't change.
     * @param period The period at which the clock is set to. Valid periods are "a.m." or "p.m.,"
     * otherwise it defaults to "a.m."
     */
    public void setTime(int hour, int minutes, String period)
    {
        clock.setTime(hour, minutes, period);
    }
    
    /**
     * Sets the time at which the alarm should go off
     * 
     * @param hour The hour at which the alarm is set to. Valid hours are integers from
     * 1 to 12, otherwise it doesn't change.
     * @param minute The minute at which the alarm is set to. Valid minutes are integers from
     * 0 to 59, otherwise it doesn't change.
     * @param period The period at which the alarm is set to. Valid periods are "a.m." or "p.m.,"
     * otherwise it defaults to "a.m."
     */
    public void setAlarmTime(int hour, int minutes, String period)
    {
        alarm.setTime(hour, minutes, period);
    }
    
    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     * 
     * If the clock time matches the alarm time and the alarm is set, the alarm rings and is
     * automatically turned off (will have to be re-set).
     */
    public void clockTick()
    {
        clock.timeTick();
        
        if (clock.getTime().equals(alarm.getTime()) && alarm.isSet()) { //Rings if alarm is set and time matches 
            System.out.println("RING RING RING");
            alarm.turnOff();
        }
    }
    
    /**
     * Turns on Alarm
     */
    public void setAlarm()
    {
        alarm.turnOn();
    }
    
    /**
     * Turns off Alarm
     */
    public void unsetAlarm()
    {
        alarm.turnOff();
    }
    
    /**
     * Return the current time of the clock in the format HH:MMperiod.
     * 
     * @return Returns a string of the form HH:MMperiod, ex: "12:00a.m."
     */
    public String getTime()
    {
        return clock.getTime();
    }
    
    /**
     * Returns the time the alarm is set to go off in the format HH:MMperiod
     * 
     * @return Returns a string of the form HH:MMperiod, ex: "12:00a.m."
     */
    public String getAlarmTime()
    {
        return alarm.getTime();
    }
    
    /**
     * Return whether or not the alarm is set
     * 
     * @return Returns a boolean value representing whether or not the alarm is set.
     */
    public boolean isAlarmSet()
    {
        return alarm.isSet();
    }
}
