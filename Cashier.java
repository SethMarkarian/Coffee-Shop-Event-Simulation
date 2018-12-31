/**
 * Holds cashier methods and variables
 *
 * @author Seth Markarian
 */
public class Cashier
{
    boolean isAvailable;
    int customers = 0;
    double servingTime = 0;
    Customer customerHelping; 

    //hi

    //bye
     
    /**
     * Constructor for a new cashier
     */
    public Cashier() {
        isAvailable = true;
        servingTime = ImportTxtFile.getAverageTimeForServing();
    }
    
    //Assigns customer to cashier that is helping
    /**
     * Assigns customer to cashier that is helping
     * 
     * @param c customer getting assigned to the cashier
     */
    public void assignCustomer(Customer c) {
        customerHelping = c;
        customers++;
    }
    
    /**
     * Returns availability for a new customer
     * 
     * @return availability for a new customer
     */
    public boolean isAvailable() {
        return isAvailable;
    }
    
    /**
     * Counts how many customers each cashier helped
     */
    public void helpCustomer() {
        customers++;
    }
    
    /**
     * Sets state of availability
     * 
     * @param state state of availablilty
     */
    public void setAvailability(boolean state) {
        isAvailable = state;
    }
    
    /**
     * Get customer that cashier is helping
     * 
     * @return customer that the cashier is helping
     */
    public Customer getCustomer() {
        return customerHelping;
    }
    
    /**
     * Creates new departure event when customer is done
     * 
     * @param currentTime assign departure event with the time
     * @return departure event
     */
    public Event setDepartureEvent(double currentTime) {
        Event event = new Event(currentTime + servingTime, 2, this);
        return event;
    }
    
    //returns total profit of one cashier for the day
    /**
     * Returns total profit of one cashier for the day
     * 
     * @return total profit of one cashier for the day
     */
    public double totalProfit() {
        return customers * ImportTxtFile.getProfitFromEachCustomer();
    }
}
