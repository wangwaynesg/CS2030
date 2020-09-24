package cs2030.simulator.event;

import cs2030.simulator.customer.Customer;
import cs2030.simulator.server.Server;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the arrival of a customer.
 */
public class ArriveEvent extends Event {
    public ArriveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = customer.getArrivalTime();
        this.eventType = EVENT_ARRIVE;
    }

    /**
     * For the list of servers, if it is available,
     * serve the customer and set <code>isAvailable</code> to <code>false</code>.<br/>
     * Else, if there is no waiting customer,
     * put the customer on wait and set <code>hasWaitingCustomer</code> to <code>true</code>.<br/>
     * Else, the customer leaves.
     * @return <code>Event</code> depending on what happens during execution.
     */
    public Event execute() {
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getIsAvailable()) {
                servers.get(i).setIsAvailable(false);
                servers.get(i).setNextAvailableTime(Math.max(this.customer.getArrivalTime(), servers.get(i).getNextAvailableTime()));
                return new ServeEvent(this.customer, Arrays.asList(servers.get(i)));
            }
        }
        for (int i = 0; i < servers.size(); i++) {
            if (!servers.get(i).getHasWaitingCustomer()) {
                servers.get(i).setHasWaitingCustomer(true);
                return new WaitEvent(this.customer, Arrays.asList(servers.get(i)));
            }
        }
        return new LeaveEvent(this.customer, this.servers);
    }

    @Override
    public String toString() {
        return super.toString() + " arrives";
    }
}
