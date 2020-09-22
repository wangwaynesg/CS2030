import java.util.List;
import java.util.Arrays;

public class ArriveEvent extends Event {
    public ArriveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = customer.getArrivalTime();
    }

    public Event execute() {
        Server[] temp = new Server[servers.size()];
        servers.toArray(temp);

        for (int i = 0; i < temp.length; i++) {
            // If server is available, return ServeEvent
            if (temp[i].getIsAvailable()) {
                //temp[i] = temp[i].setIsAvailable(true);
                return new ServeEvent(this.customer, Arrays.asList(temp));
                // change server status 
                // return new ServeEvent
                // Maintain immutability 
            }
            if (temp[i].getHasWaitingCustomer()) {
                return new LeaveEvent(this.customer, this.servers);
            } else {
                temp[i] = temp[i].setHasWaitingCustomer(true);
                return new WaitEvent(this.customer, Arrays.asList(temp));
            }
            // If server is unavailable, return WaitEvent
            // If server is unavailable and something waiting, return LeaveEvent
        }

        return this;
    }

    @Override
    public String toString() {
        return super.toString() + " arrives";
    }
}
