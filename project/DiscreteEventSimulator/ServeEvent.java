import java.util.List;

public class ServeEvent extends Event {
    public ServeEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = 0;
    }

    public Event execute() {
        return this;
    }

    @Override 
    public String toString() {
        return super.toString() + " served by " + servers.get(0).getServerID();
    }
}
