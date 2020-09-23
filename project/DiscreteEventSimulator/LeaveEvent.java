import java.util.List;

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
