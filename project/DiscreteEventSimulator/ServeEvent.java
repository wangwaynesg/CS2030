import java.util.List;

public class ServeEvent extends Event {
    public ServeEvent(Customer customer, List<Server> servers) {
        super(customer, servers); 
        for (Server server : servers) {
            if (server.getIsAvailable()) {
                this.startTime = Math.max(server.getNextAvailableTime(), customer.getArrivalTime());
                break;
            }
        }
    }

    public Event execute() {
        Server[] temp = new Server[servers.size()];
        servers.toArray(temp);

        for (int i = 0; i < temp.length; i++) {
            if (temp[i].getIsAvailable()) {
                
            }
        }
        
        return this;
    }

    @Override 
    public String toString() {
        return super.toString() + " served by " + servers.get(0).getServerID();
    }
}
