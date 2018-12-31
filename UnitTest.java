import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitTest
{
    @Test
    public void testEventTime() {
        Event event = new Event(10.0, Event.CUSTOMER_ARRIVED, new Customer(10.0, 10));
        int theAns = (int) event.getTime();
        int trueAns = 10;
        assertEquals(theAns, trueAns);
    }

    @Test
    public void testEventType() {
        Event event = new Event(10.0, Event.CUSTOMER_ARRIVED, new Customer(10.0, 10));
        int theAns = event.getTypeOfEvent();
        int trueAns = 0;
        assertEquals(theAns, trueAns);
    }

    @Test
    public void testEventTarget() {
        Event event = new Event(10.0, Event.CUSTOMER_ARRIVED, new Customer(10.0, 10));
        int theAns = ((Customer) event.getTarget()).getName();
        int trueAns = 10;
        assertEquals(theAns, trueAns);
    }

    @Test
    public void testEventSetTime() {
        Event event = new Event(10.0, Event.CUSTOMER_ARRIVED, new Customer(10.0, 10));   
        event.setTime(100);
        int theAns = (int) event.getTime();
        int trueAns = 100;
        assertEquals(theAns, trueAns);
    }

    @Test
    public void testEventCompareTo() {
        Event event1 = new Event(10.0, Event.CUSTOMER_ARRIVED, new Customer(10.0, 10));
        Event event2 = new Event(20.0, Event.CUSTOMER_ARRIVED, new Customer(20.0, 20));   
        int theAns = event1.compareTo(event2);
        int trueAns = -10;
        assertEquals(theAns, trueAns);
    }

    @Test
    public void testCustomerArrivalTime() {
        Customer customer = new Customer(10.0, 10);
        customer.setTimeArrival(100);
        int theAns = (int) customer.getTimeArrival();
        int trueAns = 100;
        assertEquals(theAns, trueAns);
    }

    @Test
    public void testCustomerServiceTime() {
        Customer customer = new Customer(10.0, 10);
        customer.setTimeService(100);
        int theAns = (int) customer.getTimeServed();
        int trueAns = 100;
        assertEquals(theAns, trueAns);
    }

    @Test
    public void testCustomerDepartTime() {
        Customer customer = new Customer(10.0, 10);
        customer.setDepartureTime(100);
        int theAns = (int) customer.getTimeDeparted();
        int trueAns = 100;
        assertEquals(theAns, trueAns);
    }

    @Test
    public void testCustomerName() {
        Customer customer = new Customer(10.0, 10);
        int theAns = customer.getName();
        int trueAns = 10;
        assertEquals(theAns, trueAns);
    }

    public void testIsAvailable() {
        Cashier cashier = new Cashier();
        boolean theAns = cashier.isAvailable();
        boolean trueAns = true;
        assertEquals(theAns, trueAns);
    }
    
    public void testSetAvailability() {
        Cashier cashier = new Cashier();
        cashier.setAvailability(false);
        boolean theAns = cashier.isAvailable();
        boolean trueAns = false;
        assertEquals(theAns, trueAns);
    }
}