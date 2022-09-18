import java.util.ArrayList;
/**
 * Train models a passenger train with cars and seats.
 *
 * @author Marwan Zeid, 101185876
 * @version 1.10 February 15, 2022
 */

public class Train
{
    /** The cars in this train. */
    private ArrayList<Car> cars;
   
    /** 
     * Constructs an empty train; i.e., one that has no cars.
     */
    public Train()
    {
        cars = new ArrayList<Car>();
    }
   
    /**
     * Adds the specified car to this train.
     * 
     * @param car Car object being added to the train
     */
    public void addCar(Car car)
    {
        if (car != null)
        {
            cars.add(car);
        }
    }
    
    /**
     * Returns this trains's list of cars. This method is intended for 
     * testing purposes only, and should not be called by other objects,
     * as it may be removed from the final version of this class.
     * 
     * @return A list of the cars in the train
     */
    public ArrayList<Car> cars()
    {
        return cars;
    }    
      
    /**
     * Attempts to issue a ticket for a business class seat or an
     * economy class seat, as specified by the method's argument.
     * It will attempt to book a seat in the first car of the appropriate
     * type, but if a seat is not available it will attempt to book a seat
     * in the second car of the appropriate type, and so on. 
     * A request to issue a ticket in a business-class car will never result
     * in a seat being reserved in an economy-class car, and vice-versa. 
     * Returns true if successful, false otherwise.
     * 
     * @param businessClassSeat Value representing whether or not the seat is business class
     * 
     * @return Returns true if seat was successfully booked, false otherwise
     */
    public boolean issueTicket(boolean businessClassSeat)
    {
        for (Car car : cars)
        {
            if (car.isBusinessClass() == businessClassSeat && car.bookNextSeat())
            {
                return true;
            }
        }
        
        return false;
    }
   
    /**
     * Cancels the ticket for the specified seat in the specified car.
     * Returns true if successful, false otherwise.
     * 
     * @param carId Number representing the car Id
     * @param seatNo Number representing the seat number in the car
     * 
     * @return Returns true if cancelling was successful, otherwise false
     */
    public boolean cancelTicket(int carId, int seatNo)
    {
        for (Car car : cars)
        {
            if (car.id() == carId)
            {
                return car.cancelSeat(seatNo);
            }
        }
        
        return false;
    }
}
