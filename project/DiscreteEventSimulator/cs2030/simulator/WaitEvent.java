package cs2030.simulator;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the waiting of a customer.
 */
public class WaitEvent extends Event{
    public WaitEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = customer.getArrivalTime();
        this.eventType = EVENT_WAIT;
    }

    /**
     * set the <code>nextAvailableTime</code> to the max of the customer's
     * <code>arrivalTime</code> and the server's <code>nextAvailableTime</code>.
     * @return <code>ServeEvent</code>
     */
    public Event execute() {
        servers.get(0).setHasWaitingCustomer(true);
        servers.get(0).setNextAvailableTime(Math.max(this.customer.getArrivalTime(), servers.get(0).getNextAvailableTime()));
        return new ServeEvent(this.customer, Arrays.asList(servers.get(0)));
    }

    @Override
    public String toString() {
        return super.toString() + " waits to be served by " + servers.get(0).getServerID();
    }
}
