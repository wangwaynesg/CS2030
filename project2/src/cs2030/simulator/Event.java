package cs2030.simulator;

import java.util.function.Function;

public abstract class Event implements Comparable<Event> {
    private final Customer customer;
    private final double startTime;
    private final int eventType;
    private final Function<Shop, Pair<Shop, Event>> function;
    private final Server eventServer;

    /**
     * Constructor for an event.
     * @param customer Customer of the event
     * @param startTime Starting time of the event
     * @param eventType Event type of the event
     * @param function Executable function of the event
     */
    public Event(Customer customer,
                 double startTime,
                 int eventType,
                 Function<Shop, Pair<Shop, Event>> function) {
        this.customer = customer;
        this.startTime = startTime;
        this.eventType = eventType;
        this.eventServer = null;
        this.function = function;
    }

    /**
     * Constructor of an event.
     * @param customer Customer of the event
     * @param startTime Starting time of the event
     * @param eventType Event type of the event
     * @param eventServer Server of the event
     * @param function Executable function of the event
     */
    public Event(Customer customer,
                 double startTime,
                 int eventType,
                 Server eventServer,
                 Function<Shop, Pair<Shop, Event>> function) {
        this.customer = customer;
        this.startTime = startTime;
        this.eventType = eventType;
        this.eventServer = eventServer;
        this.function = function;
    }

    public Pair<Shop, Event> execute(Shop shop) {
        return function.apply(shop);
    }

    public Server getEventServer() {
        return this.eventServer;
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
        return String.format("%.3f", startTime) + " " +
                customer.getCustomerID() + (customer.isGreedy() ? "(greedy)" : "");
    }
}
