package cs2030.simulator.event;

import cs2030.simulator.customer.Customer;
import cs2030.simulator.server.Server;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the serving of a customer.
 */
public class ServeEvent extends Event {
    public ServeEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = servers.get(0).getNextAvailableTime();
        this.eventType = EVENT_SERVE;
    }

    /**
     * When the customer is served, set <code>hasWaitingCustomer</code> to <code>false</code>,
     * and add 1 to the <code>nextAvailableTime</code>.
     * @return <code>DoneEvent</code>
     */
    public Event execute() {
        this.servers.get(0).setHasWaitingCustomer(false);
        this.servers.get(0).setNextAvailableTime(this.startTime + 1);
        return new DoneEvent(this.customer, Arrays.asList(this.servers.get(0)));
    }

    @Override
    public String toString() {
        return super.toString() + " served by " + servers.get(0).getServerID();
    }
}
