import java.util.List;
import java.util.Arrays;

public class ArriveEvent extends Event {
    public ArriveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = customer.getArrivalTime();
    }

    public Event execute() {
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getIsAvailable()) {
                servers.get(i).setNextAvailableTime(Math.max(this.customer.getArrivalTime(), servers.get(i).getNextAvailableTime()));
                return new ServeEvent(this.customer, Arrays.asList(servers.get(i)));
            }
        }
        for (int i = 0; i < servers.size(); i++) {
            if (!servers.get(i).getHasWaitingCustomer()) {
                servers.get(i).setHasWaitingCustomer(true);
                return new WaitEvent(this.customer, Arrays.asList(servers.get(i)));
            }
        }
        return new LeaveEvent(this.customer, this.servers);
    }

    @Override
    public String toString() {
        return super.toString() + " arrives";
    }
}
