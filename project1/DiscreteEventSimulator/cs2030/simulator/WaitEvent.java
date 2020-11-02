package cs2030.simulator;

import java.util.List;

/**
 * Represents the waiting of a customer.
 */
public class WaitEvent extends Event{
    private final double startTime;
    private final int eventType;
    private final int index;

    public WaitEvent(Customer customer, List<Server> servers, int index) {
        super(customer, servers);
        this.startTime = customer.getArrivalTime();
        this.eventType = 0;
        this.index = index;
    }

    /**
     * set the <code>nextAvailableTime</code> to the max of the customer's
     * <code>arrivalTime</code> and the server's <code>nextAvailableTime</code>.
     * @return <code>ServeEvent</code>
     */
    @Override
    public Event execute() {
        Server temp = this.getServers().get(index);
        temp = temp.setHasWaitingCustomer(true);
        temp = temp.setNextAvailableTime(Math.max(this.getCustomer().getArrivalTime(), temp.getNextAvailableTime()));
        this.getServers().set(index, temp);
        return new ServeEvent(this.getCustomer(), this.getServers(), index);
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
        return super.toString() + " waits to be served by " + this.getServers().get(index).getServerID();
    }
}
