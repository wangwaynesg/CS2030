import java.util.List;
import java.util.Arrays;

public class DoneEvent extends Event {
    public DoneEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = servers.get(0).getNextAvailableTime();
    }

    public Event execute() {
        return new LeaveEvent (this.customer, this.servers);
    }

    @Override
    public String toString() {
        return super.toString() + " done serving by " + servers.get(0).getServerID();
    }
}
