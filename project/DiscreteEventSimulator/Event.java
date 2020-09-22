import java.util.List;

public abstract class Event {
    protected final Customer customer;
    protected final List<Server> servers;
    protected double startTime;

    public Event(Customer customer, List<Server> servers) {
        this.customer = customer;
        this.servers = servers;
    }

    public abstract Event execute();

    @Override
    public String toString() {
        return String.format("%.3f", this.startTime) + " " + this.customer.getCustomerID();
    }
}
