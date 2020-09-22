/**
 * Server has 4 attributes: <code>int serverID</code>, <code>boolean isAvailable</code>,
 * <code>boolean hasWaitingCustomer</code> and <code>double nextAvailableTime</code>.
 */
public class Server {
    private final int serverID;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;

    public Server(int serverID, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime) {
        this.serverID = serverID;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
    }

    public int getServerID() {
        return this.serverID;
    }

    @Override
    public String toString() {
        if (this.isAvailable) {
            return this.serverID + " is available";
        } else {
            return this.serverID + " is busy;" + 
                    (this.hasWaitingCustomer ? " waiting customer to be served at " : " available at ") +
                    String.format("%.3f", this.nextAvailableTime);
        }
    }
}
