/**
 * Holds customer methods and variables
 *
 * @author Seth Markarian
 */
public class Customer
{
    private double time_arrival, time_service, time_departure;
    private int name;
    
    /**
     * Constructor for a new customer
     * 
     * @param a time of arrival
     * @param n name of customer
     */
    public Customer(double a, int n) {
        time_arrival = a;
        time_service = 0;
        time_departure = 0;
        name = n;
    }
    
    /**
     * Set arrival time for a customer
     * 
     * @param time arrival time
     */
    public void setTimeArrival(double time) {
        time_arrival = time;
    }

    /**
     * Set service time for a customer
     * 
     * @param time service time
     */
    public void setTimeService(double time) {
        time_service = time;
    }

    /**
     * Set departure time for a customer
     * 
     * @param time departure time
     */
    public void setDepartureTime(double time) {
        time_departure = time;
    }
    
    /**
     * Get arrival time for a customer
     * 
     * @return arrival time
     */
    public double getTimeArrival() {
        return time_arrival;
    }
    
    /**
     * Get service time for a customer
     * 
     * @return service time
     */
    public double getTimeServed() {
        return time_service;
    }
    
    /**
     * Get departure time for a customer
     * 
     * @param departure time
     */
    public double getTimeDeparted() {
        return time_departure;
    }
    
    /**
     * Get name of customer
     * 
     * @return name of customer
     */
    public int getName() {
        return name;
    }
}
