package cs2030.simulator.event;

import cs2030.simulator.customer.Customer;
import cs2030.simulator.server.Server;

import java.util.List;

/**
 * Represents the event that the customer is done being served.
 */
public class DoneEvent extends Event {
    public DoneEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = servers.get(0).getNextAvailableTime();
        this.eventType = EVENT_DONE;
    }

    /**
     * Set the server's <code>nextAvailableTime</code> to this event's <code>startTime</code>.
     * If there is no waiting customer, set <code>isAvailable</code> to <code>true</code>.
     * @return <code>LeaveEvent</code>
     */
    public Event execute() {
        if (!servers.get(0).getHasWaitingCustomer()) {
            servers.get(0).setIsAvailable(true);
        }
        servers.get(0).setNextAvailableTime(this.startTime);
        return new LeaveEvent (this.customer, this.servers);
    }

    @Override
    public String toString() {
        return super.toString() + " done serving by " + servers.get(0).getServerID();
    }
}
