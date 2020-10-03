package cs2030.simulator;

import java.util.List;

/**
 * Represents the event where a customer leaves.
 */
public class LeaveEvent extends Event {
    private final double startTime;
    private final int eventType;

    public LeaveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = customer.getArrivalTime();
        this.eventType = 3;
    }

    @Override
    public Event execute() {
        return this;
    }

    @Override
    public double getStartTime() {
        return this.startTime;
    }

    @Override
    public int getEventType() {
        return this.eventType;
    }

    @Override
    public String toString() {
        return super.toString() + " leaves";
    }
}
