/**
 * Creates Event object for the Priority Queue
 *
 * @author Seth Markarian
 */
public class Event implements Comparable<Event>
{
    public static final int CUSTOMER_ARRIVED = 0;
    public static final int CUSTOMER_SERVING = 1;
    public static final int CUSTOMER_DEPARTING = 2;
    private double time;
    private int type;
    private Object target;
    
    /**
     * Creates new event
     * 
     * @param t Time of event
     * @param ty Type of event
     * @param tar Object the event is tied to
    */
    public Event(double t, int ty, Object tar) {
        time = t;
        type = ty;
        target = tar;
    }
    
    /**
     * Returns time of event
     * 
     * @return time of event
     */
    public double getTime() {
        return time;
    }
    
    /**
     * Returns type of event
     * 
     * @return type of event
     */
    public int getTypeOfEvent() {
        return type;
    }
    
    /**
     * Returns the object that is tied to the event
     * 
     * @return object that is tied to the event
     */
    public Object getTarget() {
        return target;
    }
    
    /**
     * Sets time for the event
     * 
     * @param time for the event
     */
    public void setTime(double t) {
        time = t;
    }
  
    /**
     * Compares two events for priority queue
     * 
     * @param other event that is getting compared to this object
     * @return compareTo int
     */
    public int compareTo(Event e) {
        return (int) (this.time - e.time);
    }
}
