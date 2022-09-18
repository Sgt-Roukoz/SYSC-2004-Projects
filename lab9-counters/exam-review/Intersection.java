/**
* This class models a street intersection. An intersection has two traffic signals: an
* east-west signal and a north-south signal. Note: When one signal is green or yellow,
* the other signal must be red. (This class uses Question 7’s TrafficSignal class.)
*/
public class Intersection
{
private TrafficSignal eastWest;
private TrafficSignal northSouth;
/**
* Constructs a new Intersection in which the east-west traffic signal is
* initially green and the north-south traffic signal is initially red.
*/
public Intersection() {
    eastWest = new TrafficSignal(TrafficSignal.GREEN);
    northSouth = new TrafficSignal(TrafficSignal.RED);
}

/**
 * 
* Advance one or both of this intersection's traffic signals.
*/
public void change() {
    if (eastWest.isGreenLight())
    {
        eastWest.advance();
    }
    else if (northSouth.isGreenLight())
    {
        northSouth.advance();
    }
    else
    {
        northSouth.advance();
        eastWest.advance();
    }
}
/**
* Returns the current Intersection status.
* @return a string representation of this Intersection that describes
* the current colours of the intersection's two lights; for example:
* "east-west: green light
* north-south: red light"
*/
public String toString() {
    // do nothing
    return "";
}