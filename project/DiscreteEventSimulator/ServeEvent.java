import java.util.List;
import java.util.Arrays;

public class ServeEvent extends Event {
    public ServeEvent(Customer customer, List<Server> servers) {
        super(customer, servers); 
        this.startTime = servers.get(0).getNextAvailableTime();
    }

    public Event execute() {     
        return new DoneEvent(this.customer, Arrays.asList(this.servers.get(0).setNextAvailableTime(this.startTime + 1)));
    }

    @Override 
    public String toString() {
        return super.toString() + " served by " + servers.get(0).getServerID();
    }
}
