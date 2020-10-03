package cs2030.simulator;

import java.util.List;

public class ArriveEvent extends Event {
    private final double startTime;
    private final int eventType;

    /**
     * Represents the arrival of a customer.
     */
    public ArriveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = customer.getArrivalTime();
        this.eventType = 1;
    }

    /**
     * For the list of servers, if it is available,
     * serve the customer and set <code>isAvailable</code> to <code>false</code>.<br/>
     * Else, if there is no waiting customer,
     * put the customer on wait and set <code>hasWaitingCustomer</code> to <code>true</code>.<br/>
     * Else, the customer leaves.
     * @return <code>Event</code> depending on what happens during execution.
     */
    @Override
    public Event execute() {
        for (int i = 0; i < this.getServers().size(); i++) {
            if(this.getServers().get(i).getIsAvailable()) {
                Server temp = this.getServers().get(i);
                temp = temp.setIsAvailable(false);
                temp = temp.setNextAvailableTime(Math.max(this.getCustomer().getArrivalTime(), temp.getNextAvailableTime()));
                this.getServers().set(i, temp);
                return new ServeEvent(this.getCustomer(), this.getServers(), i);
            }
        }

        for (int j = 0; j < this.getServers().size(); j++) {
            if (!this.getServers().get(j).getHasWaitingCustomer()) {
                Server temp = this.getServers().get(j);
                temp = temp.setHasWaitingCustomer(true);
                this.getServers().set(j, temp);
                return new WaitEvent(this.getCustomer(), this.getServers(), j);
            }
        }

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
        return super.toString() + " arrives";
    }
}
