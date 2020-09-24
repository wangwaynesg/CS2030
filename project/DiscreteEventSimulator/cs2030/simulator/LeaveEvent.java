package cs2030.simulator;

import java.util.List;

/**
 * Represents the event where a customer leaves.
 */
public class LeaveEvent extends Event {
    public LeaveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = customer.getArrivalTime();
        this.eventType = EVENT_LEAVE;
    }

    public Event execute() {
        return this;
    }

    @Override
    public String toString() {
        return super.toString() + " leaves";
    }
}
