/**
 * The test class CarTest.
 *
 * @author  Lynn Marshall, SCE
 * @version 1.2 May 1st, 2015
 * 
 * @author  Marwan Zeid 101185876
 * @version March 8, 2022
 */
public class CarTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class CarTest
     */
    public CarTest()
    {
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
     * Test creating a businesss-class car
     * 
     * Create a business-class car and check that it has the right number of seats.
     * Also check if the correct number is applied to each, and that the seat price is correct.
     */
    public void testCreateBusinessCar()
    {
        Car aCar = new Car(1385, true);
        Seat[] seats = aCar.seats();
        
        /* Verify that the car has the right number of seats. */
        assertEquals(Car.BUSINESS_SEATS, seats.length);
        
        /* Verify that each seat has the correct number and price. */
        for (int i = 0; i < seats.length; i++) {
            assertEquals(i+1, seats[i].number());
            assertEquals(Car.BUSINESS_SEAT_COST, seats[i].price());
        }
    }
    
    /**
     * Test creating an economy-class car
     * 
     * Create a economy-class car and check that it has the right number of seats.
     * Also check if the correct number is applied to each seat, and that the seat price is correct.
     */
    public void testCreateEconomyCar()
    {
        Car aCar = new Car(1400, false);
        Seat[] seats = aCar.seats();
        
        /* Verify that the car has the right number of seats. */        
        assertEquals(Car.ECONOMY_SEATS, seats.length);
        
        /* Verify that each seat has the correct number and price. */       
        for (int i = 0; i < seats.length; i++) {
            assertEquals(i+1, seats[i].number());
            assertEquals(Car.ECONOMY_SEAT_COST, seats[i].price());
        }
    }    
    
    /**
     * Test creating a car with a specific ID
     * 
     * Check if the correct ID is assigned to the car after creation.
     */
    public void testID()
    {
         Car aCar;
         aCar= new Car(1385, true);
         assertEquals(1385, aCar.id());
         aCar = new Car(1400, false);
         assertEquals(1400, aCar.id());
    }
    
    /**
     * Test checking if a car is business-class
     * 
     * Runs the "isBusinessClass" method, and checks if output is whats expected.
     */
    public void testIsBusinessClass()
    {
         Car aCar;
         aCar = new Car(1385, true);
         assertTrue(aCar.isBusinessClass());
         aCar = new Car(1400, false);
         assertFalse(aCar.isBusinessClass()); 
    }
    
    /**
     * Test booking seats in a car
     * 
     * Create both an economy and business class car and test the booking methods for each. Checks state before
     * booking, and checks state after booking while also checking the state of the next seat.
     */
    public void testBookNextSeat()
    {
        Car aCar, bCar;
        aCar = new Car(1234, true);
        bCar = new Car(5678, false);
        
        Seat[] aSeats = aCar.seats();
        Seat[] bSeats = bCar.seats();
        
        /* Verify that no seats are booked. */
        for (int i = 0; i < aSeats.length; i++) {
            assertFalse(aSeats[i].isBooked());
        }        
        for (int i = 0; i < bSeats.length; i++) {
            assertFalse(bSeats[i].isBooked());
        }  
        /* Verify that the seats are booked consecutively,
         * starting with Seat #1.
         */
        for (int i = 0; i < aSeats.length; i++) {
            aSeats = aCar.seats();
            assertFalse(aSeats[i].isBooked()); // not booked
            assertTrue(aCar.bookNextSeat()); // book it
            assertTrue(aSeats[i].isBooked()); // now booked
            if (i!=aSeats.length-1) {
                assertFalse(aSeats[i+1].isBooked()); // but next isn't
            }
        }
        
        for (int i = 0; i < bSeats.length; i++) {
            bSeats = bCar.seats();
            assertFalse(bSeats[i].isBooked()); // not booked
            assertTrue(bCar.bookNextSeat()); // book it
            assertTrue(bSeats[i].isBooked()); // now booked
            if (i!=bSeats.length-1) {
                assertFalse(bSeats[i+1].isBooked()); // but next isn't
            }
        }
        /* Try to book a seat now that all the seats have been booked. */
        assertFalse(aCar.bookNextSeat());
        assertFalse(bCar.bookNextSeat());
    }
    
    /**
     * Test cancelling seats in a car
     * 
     * Creates cars of both business and economy class and tests cancelling not-booked seats, booked seats
     * and non-existant seats.
     */
    public void testCancelSeat()
    {
        Car aCar,cCar;
        aCar = new Car(1234, true);
        cCar = new Car(5678, false);
        Seat[] aSeats = aCar.seats();
        Seat[] cSeats = cCar.seats();
        
        /* Cancel seat 0. cancelSeat() should return false, as there
         * is no seat 0.
         */
        assertFalse(aCar.cancelSeat(0));
        assertFalse(cCar.cancelSeat(0));
        
        /* Try cancelling a seat whose number is one higher than 
         * the highest valid seat number (seats.length - 1). 
         * cancelSeat() should return false.
         */
        assertFalse(aCar.cancelSeat(aSeats.length));
        assertFalse(cCar.cancelSeat(cSeats.length));
        
        /* Try cancelling all the seats in the car, even though 
         * they haven't been booked. cancelSeat() should 
         * return false.
         */
        for (int i = 0; i < aSeats.length; i++) {
            assertFalse(aCar.cancelSeat(i+1));
        }
        for (int i = 0; i < cSeats.length; i++) {
            assertFalse(cCar.cancelSeat(i+1));
        }
        
        /* Book all the seats */
        for (int i = 0; i < aSeats.length; i++) {
            aCar.bookNextSeat();
        }  
        for (int i = 0; i < cSeats.length; i++) {
            cCar.bookNextSeat();
        }  
        
        /* Try cancelling all the seats in the car.
         */
        for (int i = 0; i < aSeats.length; i++) {
            assertTrue(aCar.cancelSeat(i+1));
        } 
        for (int i = 0; i < cSeats.length; i++) {
            assertTrue(cCar.cancelSeat(i+1));
        }
        
        /* In case seat numbers are off, try some more tests.
         */
        Car bCar;
        bCar = new Car (4321,false);
        // book 2 seats
        assertTrue(bCar.bookNextSeat());
        assertTrue(bCar.bookNextSeat());
        // try to cancel the 3rd (not booked)
        assertFalse(bCar.cancelSeat(3));
        // cancel the 1st and 2nd (were both booked)
        assertTrue(bCar.cancelSeat(1));
        assertTrue(bCar.cancelSeat(2));
        
        /* Try cancelling a booking of a non-existant seat
         */
        assertFalse(aCar.cancelSeat(50));
        assertFalse(cCar.cancelSeat(50));
       
    }      
}
