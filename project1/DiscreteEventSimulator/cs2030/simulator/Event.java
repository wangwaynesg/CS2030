package cs2030.simulator;

import java.util.List;

/**
 * Represents a single event with a <code>Customer</code> and a List of <code>Server</code>.
 */
public abstract class Event implements Comparable<Event> {
    private final Customer customer;
    private final List<Server> servers;

    public Event(Customer customer, List<Server> servers) {
        this.customer = customer;
        this.servers = servers;
    }

    public abstract Event execute();

    public abstract double getStartTime();

    public abstract int getEventType();

    public Customer getCustomer() {
        return this.customer;
    }

    public List<Server> getServers() {
        return this.servers;
    }


    @Override
    public int compareTo(Event e) {
        if (this.getStartTime() < e.getStartTime()) {
            return -1;
        }
        if (this.getStartTime() > e.getStartTime()) {
            return 1;
        }
        if (this.getEventType() == e.getEventType()) {
            return this.getCustomer().getCustomerID() - e.getCustomer().getCustomerID();
        } else {
            return e.getEventType() - this.getEventType();
        }
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getStartTime()) + " " + this.getCustomer().getCustomerID();
    }
}
