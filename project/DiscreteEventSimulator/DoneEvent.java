import java.util.List;
import java.util.Arrays;

public class DoneEvent extends Event {
    public DoneEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = servers.get(0).getNextAvailableTime();
        this.eventType = EVENT_DONE;
    }

    public Event execute() {
        servers.get(0).setIsAvailable(true);
        servers.get(0).setNextAvailableTime(this.startTime);
        return new LeaveEvent (this.customer, this.servers);
    }

    @Override
    public String toString() {
        return super.toString() + " done serving by " + servers.get(0).getServerID();
    }
}
