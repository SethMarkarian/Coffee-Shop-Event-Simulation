import java.util.*;
/**
 * Runs events
 *
 * @author Seth Markarian
 */
public class CoffeeShop
{
    private ArrayList<Cashier> cashiers;
    private PriorityQueue<Event> eventQueue;
    private double currentTime;
    private Queue<Customer> customerQueue;
    private ArrayList<Customer> servedCustomers;
    private ArrayList<Double> avgWait;
    private double overflows;
    
    /**
     * Creates new coffee shop and initializes instance variablies
     */
    public CoffeeShop() {
        cashiers = new ArrayList<Cashier>();
        eventQueue = new PriorityQueue<Event>();
        servedCustomers = new ArrayList<Customer>();
        customerQueue = new LinkedList<Customer>();
        avgWait = new ArrayList<Double>();
        overflows = 0;
        currentTime = 0;
    }

    /**
     * Adds new cashier to cashier arraylist
     * 
     * @param c cashier that's being added
     */
    public void addCashier(Cashier c) {
        cashiers.add(c);
    }

    /**
     * Returns current time
     * 
     * @return current time
     */
    public double getSimulationTime() {
        return currentTime;
    }

    /**
     * Runs simulation
     * 
     * @param a number of cashiers
     */
    public void run(int a) {
        ImportTxtFile.importTimes();
        initialEvent();
        currentTime = 0;
        for(int i = 0; i < a; i++) {
            Cashier cashier = new Cashier();
            cashier.setAvailability(true);
            cashiers.add(cashier);
        }
        while(!eventQueue.isEmpty()) {
            Event event = eventQueue.poll();
            currentTime = event.getTime();
            eventHandler(event);
        }
        
        double sum = 0.0;
        for(double d : avgWait) {
            sum += d;
        }
        
        double totalProfit = 0;
        for(Cashier c : cashiers) {
            totalProfit += c.totalProfit();
        }
        
        double average = sum / avgWait.size();
        System.out.println("Total Profit: " + totalProfit);
        System.out.println("Total Cost: " + (cashiers.size() * ImportTxtFile.getCostOfStaffingOneCashier()));
        System.out.println("Net Profit: " + (totalProfit - (cashiers.size() * ImportTxtFile.getCostOfStaffingOneCashier())));
        System.out.println("Average Wait Time: " + average);
        System.out.println("Maximum Wait Time: " + Collections.max(avgWait));
        System.out.println("Overflow Rate: " + (overflows / (double) ImportTxtFile.list2.size()) * 100 + "%");
    }

    /**
     * Starts the simulation by adding arriving events to the priority queue
     */
    private void initialEvent() {
        int count = 0;
        for(double d : ImportTxtFile.list2) {
            Customer customer = new Customer(d, count);
            Event event = new Event(d, Event.CUSTOMER_ARRIVED, customer);
            eventQueue.add(event);
            count++;
        }
    }
    
    /**
     * Completes event actions based on type of event
     * 
     * @param event event to run action
     */
    private void eventHandler(Event event) {
        if(event.getTypeOfEvent() == Event.CUSTOMER_ARRIVED) {
            if(customerQueue.size() <= (8 * cashiers.size()) && event.getTime() >= 21600 && event.getTime() <= 79200) {
                customerQueue.offer((Customer) event.getTarget());
            }
            else {
                System.out.println("Overflow! Customer: " + ((Customer) event.getTarget()).getName());
                overflows++;
            }
            if(findNextAvailableCashier() != null && customerQueue.size() != 0) {
                eventQueue.add(new Event(currentTime, Event.CUSTOMER_SERVING, customerQueue.peek()));
            }
        }

        else if(event.getTypeOfEvent() == Event.CUSTOMER_SERVING && findNextAvailableCashier() != null && customerQueue.size() != 0) { 
            Customer customer = customerQueue.poll();
            customer.setTimeService(currentTime);
            Cashier cashier = findNextAvailableCashier();
            cashier.assignCustomer(customer);
            cashier.setAvailability(false);
            eventQueue.add(cashier.setDepartureEvent(currentTime));
        }

        else if(event.getTypeOfEvent() == Event.CUSTOMER_SERVING && findNextAvailableCashier() == null) {
            event.setTime(event.getTime() + 1);
            currentTime += 1;
            eventQueue.add(event);
        }
        else if(event.getTypeOfEvent() == Event.CUSTOMER_DEPARTING) {
            Cashier cashier = (Cashier) event.getTarget();
            Customer customer = cashier.getCustomer();
            customer.setDepartureTime(currentTime);
            servedCustomers.add(customer);
            cashier.setAvailability(true);
            if(findNextAvailableCashier() != null && customerQueue.size() != 0) {
                eventQueue.add(new Event(currentTime, Event.CUSTOMER_SERVING, customerQueue.peek()));
            }
            avgWait.add(customer.getTimeServed() - customer.getTimeArrival());
            System.out.println("Customer: " + customer.getName() + " Arrived: " + customer.getTimeArrival() + ". Served: " + customer.getTimeServed() + ". Departed: " + customer.getTimeDeparted() + ". Wait time: " + (customer.getTimeServed() - customer.getTimeArrival()));
        }
    }

    /**
     * Finds next available cashier
     * 
     * @return available cashier
     */
    private Cashier findNextAvailableCashier() {
        Iterator<Cashier> itr = cashiers.iterator();
        while(itr.hasNext()) {
            Cashier cashier = itr.next();
            if(cashier.isAvailable()) {
                return cashier;
            }
        }
        return null;
    }
}