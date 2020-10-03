package cs2030.simulator;

import java.util.List;

/**
 * Represents the serving of a customer.
 */
public class ServeEvent extends Event{
    private final double startTime;
    private final int eventType;
    private final int index;

    public ServeEvent(Customer customer, List<Server> servers, int index) {
        super(customer, servers);
        this.startTime = servers.get(index).getNextAvailableTime();
        this.eventType = 2;
        this.index = index;
    }

    /**
     * When the customer is served, set <code>hasWaitingCustomer</code> to <code>false</code>,
     * and add 1 to the <code>nextAvailableTime</code>.
     * @return <code>DoneEvent</code>
     */
    @Override
    public Event execute() {
        Server temp = this.getServers().get(index);
        temp = temp.setHasWaitingCustomer(false);
        temp = temp.setNextAvailableTime(this.startTime + 1);
        this.getServers().set(index, temp);
        return new DoneEvent(this.getCustomer(), this.getServers(), index);
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
        return super.toString() + " served by " + this.getServers().get(index).getServerID();
    }
}
