package cs2030.simulator;

import java.util.function.Function;

/**
 * Represents a single event with a Customer, event start time and an execution function.
 */
public abstract class Event implements Comparable<Event> {
    private final Customer customer;
    private final double startTime;
    private final int eventType;
    private final RNG rng;
    private final Function<Shop, Pair<Shop, Event>> function;

    public Event(Customer customer, double startTime, int eventType, Function<Shop, Pair<Shop, Event>> function) {
        this.customer = customer;
        this.startTime = startTime;
        this.eventType = eventType;
        this.rng = null;
        this.function = function;
    }

    public Event(Customer customer, double startTime, int eventType, RNG rng, Function<Shop, Pair<Shop, Event>> function) {
        this.customer = customer;
        this.startTime = startTime;
        this.eventType = eventType;
        this.rng = rng;
        this.function = function;
    }

    public Pair<Shop, Event> execute(Shop shop) {
        return function.apply(shop);
    }

    public double getStartTime() {
        return this.startTime;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    @Override
    public int compareTo(Event e) {
        if (this.startTime < e.startTime) {
            return -1;
        }
        if (this.startTime > e.startTime) {
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
        return String.format("%.3f", startTime) + " " + customer.getCustomerID();
    }
}