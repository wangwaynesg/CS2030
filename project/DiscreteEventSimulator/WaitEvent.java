import java.util.List;

public class WaitEvent extends Event {
    public WaitEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        // TO BE CHANGED?
        this.startTime = customer.getArrivalTime();
    }

    public Event execute() {

        return new ArriveEvent(this.customer, this.servers);
    }

    @Override
    public String toString() {
        return super.toString() + " waits to be served by <INSERT EDIT HERE>";
    }
}
