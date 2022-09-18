import java.util.ArrayList;

/**
 * The test class TrainTest.
 *
 * @author  Lynn Marshall
 * @version May 2015
 * 
 * @author  Marwan Zeid 101185876
 * @version March 8, 2022
 */
public class TrainTest extends junit.framework.TestCase
{
    Train aTrain;
    Car car1, car2, car3, car4;
    /**
     * Default constructor for test class TrainTest
     * 
     * Creates a train and 4 train cars, 2 business class and 2 economy class
     */
    public TrainTest()
    {
        aTrain = new Train();
        
        car1 = new Car(1250, true);
        aTrain.addCar(car1);
        
        car2 = new Car(1300, false);
        aTrain.addCar(car2);
        
        car3 = new Car(1740, false);
        aTrain.addCar(car3);
        
        car4 = new Car(1980, true);
        aTrain.addCar(car4);
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }
 
    /**
     * Test creating an empty train
     * 
     * Creates a train with no cars and checks if the underlting array is empty.
     */
    public void testCreateEmptyTrain()
    {
        Train emptyTrain = new Train();
        
        /* Verify that a new train has no cars. */
        assertEquals(0, emptyTrain.cars().size());
    }
    
    /**
     * Test adding cars to the train
     * 
     * Tests if the car added is in the correct order, and their settings are the same.
     */
    public void testAddCar()
    {
        ArrayList<Car> cars = aTrain.cars();
        assertEquals(4, cars.size());
        
        
        /* Verify that each car added to the train was placed at
         * the end of the list.
         */      
        /* Important - assertSame() does not compare the Car objects 
         * referred to by car1 and aCar to detemine if they are equal
         * (have the same state). It verifies that car1 an aCar refer to
         * the same object; i.e., that the Car (reference) retrieved by get(0)
         * is the first first that was added to the ArrayList.
         */
        assertSame(car1, cars.get(0));

        assertSame(car2, cars.get(1));
        
        assertSame(car3, cars.get(2));
        
        assertSame(car4, cars.get(3));  
    }
        
    /**
     * Test issuing tickets
     * 
     * Books all seats in the business/economy class car and checks if they are actually 
     * booked using the isBooked() method. Checks that you can't book more bussiness/economy 
     * class seats if all of them are already booked.
     */
    public void testIssueTicket()
    {
        /* Book all the seats in the business-class cars. */
        for (int i = 0; i < 2 * Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Attempt to book one more business-class seat, even
         * though they are all booked.
         */
        assertFalse(aTrain.issueTicket(true));        
 
        ArrayList<Car> cars = aTrain.cars();
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }
        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        cars = aTrain.cars();
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }  
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }
        
        /* Book all the seats in the second economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        /* check that all seats are now booked */
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(2).seats()[i].isBooked());
        }  
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }
        
        /* Try to book another business class seat (fails)*/
        assertFalse(aTrain.issueTicket(true));
        /* Try to book another economy class seat (fails)*/
        assertFalse(aTrain.issueTicket(false));
    }
    
    /**
     * Test cancelling tickets
     * 
     * Books all seats in the first business class car and half the seats in the 
     * second one and all seats in the first economy class car. Then tests cancelling
     * seats in non-existant cars and non-existant seats in existing cars. 
     * Also tests cancelling booked seats and non-booked seats.
     */
    public void testCancelTicket()
    {
        /* Book all the seats in the first business-class car, and half the seats in the second business-class car. */
        for (int i = 0; i < 1.5 * Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        ArrayList<Car> cars = aTrain.cars();
        
        assertTrue(aTrain.cancelTicket(1300, 4));
        assertFalse(cars.get(1).seats()[3].isBooked());        
        
        /* Cancel ticket in a non-existent car. */
        assertFalse(aTrain.cancelTicket(1500, 7));
        
        /* Cancel ticket in a non-existent seat. */
        assertFalse(aTrain.cancelTicket(1300, 54));
        
        /* Cancel ticket for a seat that hasn't been booked. */
        assertFalse(aTrain.cancelTicket(1740, 21));
        assertFalse(cars.get(2).seats()[20].isBooked());        
        
        /* Attempt to cancel the same ticket twice. */
        assertTrue(aTrain.cancelTicket(1250, 11));
        assertFalse(cars.get(0).seats()[10].isBooked());
        
        assertFalse(aTrain.cancelTicket(1250, 11));   
        assertFalse(cars.get(0).seats()[10].isBooked());
        
        /*Cancel seat in the first half of half-booked business class car, nad try to cancel in the second half*/
        assertTrue(aTrain.cancelTicket(1980, 14));
        assertFalse(cars.get(3).seats()[13].isBooked());
        
        assertFalse(aTrain.cancelTicket(1980, 23));
        assertFalse(cars.get(3).seats()[22].isBooked());
        
    }
    
    /**
     * Test both booking and cancelling tickets
     * 
     * Books all seats in the first business class car and half the seats in the 
     * second one and all seats in the first economy class car. Does similar tests to both testIssueTicket()
     * and testCancelTicket(), cancelling three random seats that were booked, and then booking 4 times,
     * checking if the previously cancelled seats got booked along with the next previously unbooked seat.
     */
    public void testBookCancelTicket()
    {
        /* Book all the seats in the first business-class car, and half the seats in the second business-class car. */
        for (int i = 0; i < 1.5 * Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        ArrayList<Car> cars = aTrain.cars();
        
        /* Cancel three seats in economy car*/
        assertTrue(aTrain.cancelTicket(1300, 4));
        assertFalse(cars.get(1).seats()[3].isBooked()); 
        
        assertTrue(aTrain.cancelTicket(1300, 9));
        assertFalse(cars.get(1).seats()[8].isBooked());
        
        assertTrue(aTrain.cancelTicket(1300, 26));
        assertFalse(cars.get(1).seats()[25].isBooked());
        
        /* Book four economy seats and check if correct cars were booked*/
        assertTrue(aTrain.issueTicket(false));
        assertTrue(cars.get(1).seats()[3].isBooked());
        
        assertTrue(aTrain.issueTicket(false));
        assertTrue(cars.get(1).seats()[8].isBooked()); 
        
        assertTrue(aTrain.issueTicket(false));
        assertTrue(cars.get(1).seats()[25].isBooked()); 
        
        assertTrue(aTrain.issueTicket(false));
        assertTrue(cars.get(2).seats()[0].isBooked());
        
        /* Cancel one seats in first business-class car, and two seats in second business-class car*/
        assertTrue(aTrain.cancelTicket(1250, 4));
        assertFalse(cars.get(0).seats()[3].isBooked()); 
        
        assertTrue(aTrain.cancelTicket(1980, 9));
        assertFalse(cars.get(3).seats()[8].isBooked());
        
        assertTrue(aTrain.cancelTicket(1980, 15));
        assertFalse(cars.get(3).seats()[14].isBooked());
        
        /* Book four business-class seats and check if correct seats were booked*/
        assertTrue(aTrain.issueTicket(true));
        assertTrue(cars.get(0).seats()[3].isBooked()); 
        
        assertTrue(aTrain.issueTicket(true));
        assertTrue(cars.get(3).seats()[8].isBooked()); 
        
        assertTrue(aTrain.issueTicket(true));
        assertTrue(cars.get(3).seats()[14].isBooked()); 
        
        assertTrue(aTrain.issueTicket(true));
        assertTrue(cars.get(3).seats()[15].isBooked()); 
    }
}
