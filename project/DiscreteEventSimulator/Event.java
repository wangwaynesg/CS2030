import java.util.List;
import java.util.Comparator;
import java.lang.Comparable;

public abstract class Event implements Comparable<Event> {
    protected int eventType;
    protected final Customer customer;
    protected List<Server> servers;
    protected double startTime;

    public static final int EVENT_WAIT = 0;
    public static final int EVENT_ARRIVE = 1;
    public static final int EVENT_SERVE = 2;
    public static final int EVENT_LEAVE = 3;
    public static final int EVENT_DONE = 4;

    public Event(Customer customer, List<Server> servers) {
        this.customer = customer;
        this.servers = servers;
    }

    public abstract Event execute();

    public double getStartTime() {
        return this.startTime;
    }

    public double getCustomerArrivalTime() {
        return this.customer.getArrivalTime();
    }

    @Override
    public int compareTo(Event e) {
        if (this.startTime < e.getStartTime()) {
            return -1;
        }
        if (this.startTime > e.getStartTime()) {
            return 1;
        }
        if (this.eventType == e.eventType) {
            return this.customer.getCustomerID() - e.customer.getCustomerID();
        } else {
            return e.eventType - this.eventType;
        }
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.startTime) + " " + this.customer.getCustomerID();
    }
}
