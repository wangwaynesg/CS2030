import java.util.List;

public class WaitEvent extends Event {
    public WaitEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = customer.getArrivalTime();
    }

    public Event execute() {
        Server temp = servers.get(0);
        temp = temp.setIsAvailable(false);
        temp = temp.setHasWaitingCustomer(false);
        temp = temp.setNextAvailableTime(Math.max(this.customer.getArrivalTime(), temp.getNextAvailableTime()));

        return new ServeEvent(this.customer, this.servers);
    }

    @Override
    public String toString() {
        return super.toString() + " waits to be served by " + servers.get(0).getServerID();
    }
}
