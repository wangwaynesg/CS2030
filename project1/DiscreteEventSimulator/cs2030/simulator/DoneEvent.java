package cs2030.simulator;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the event that the customer is done being served.
 */
public class DoneEvent extends Event {
    private final double startTime;
    private final int eventType;
    private final int index;

    public DoneEvent(Customer customer, List<Server> servers, int index) {
        super(customer, servers);
        this.startTime = servers.get(index).getNextAvailableTime();
        this.eventType = 4;
        this.index = index;
    }

    /**
     * Set the server's <code>nextAvailableTime</code> to this event's <code>startTime</code>.
     * If there is no waiting customer, set <code>isAvailable</code> to <code>true</code>.
     * @return <code>LeaveEvent</code>
     */
    @Override
    public Event execute() {
        Server temp = this.getServers().get(index);
        if (!temp.getHasWaitingCustomer()) {
            temp = temp.setIsAvailable(true);
        }
        temp = temp.setNextAvailableTime(this.startTime);
        this.getServers().set(index, temp);
        return new LeaveEvent(this.getCustomer(), this.getServers());
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
        return super.toString() + " done serving by " + this.getServers().get(index).getServerID();
    }
}
