import java.util.List;
import java.util.Arrays;

public class ArriveEvent extends Event {
    public ArriveEvent(Customer customer, List<Server> servers) {
        super(customer, servers);
        this.startTime = customer.getArrivalTime();
    }

    public Event execute() {
        
        /*
        Server[] temp = new Server[servers.size()];
        servers.toArray(temp);

        for (int i = 0; i < temp.length; i++) {
            if (temp[i].getIsAvailable()) {
                temp[i] = temp[i].setIsAvailable(false);
                servers = Arrays.toList(temp);
            }


        */
        for (int i = 0; i < servers.size(); i++) {
            System.out.println(servers.get(i));
            if (servers.get(i).getIsAvailable()) {
                Server temp = servers.get(i);
                temp = temp.setIsAvailable(false);
                temp = temp.setNextAvailableTime(Math.max(this.customer.getArrivalTime(), temp.getNextAvailableTime()));
                return new ServeEvent(this.customer, Arrays.asList(temp));
            }
        }
        for (int i = 0; i < servers.size(); i++) {
            if (!servers.get(i).getHasWaitingCustomer()) {
                return new WaitEvent(this.customer, Arrays.asList(servers.get(i).setHasWaitingCustomer(true)));
            }
        }
        return new LeaveEvent(this.customer, this.servers);
    }

    @Override
    public String toString() {
        return super.toString() + " arrives";
    }
}
