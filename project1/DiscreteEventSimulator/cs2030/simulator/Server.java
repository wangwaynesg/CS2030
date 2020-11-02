package cs2030.simulator;

/**
 * Server represents each server that has a <code>serverID</code>, <code>isAvailable</code> status,
 * <code>hasWaitingCustomer</code> status and a <code>nextAvailableTime</code>.
 */
public class Server {
    private final int serverID;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;

    /**
     * Constructor for creating a new Server with all the respective 4 attributes.
     * @param serverID server ID of the server
     * @param isAvailable status of whether the server is available to accept a new customer
     * @param hasWaitingCustomer status of whether there is a customer waiting in line for this server
     * @param nextAvailableTime the next time at which the server is available
     */
    public Server(int serverID, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime) {
        this.serverID = serverID;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
    }

    /**
     * Constructor for instantiating a new server for the start base.
     * @param serverID server ID of the server
     */
    public Server(int serverID) {
        this.serverID = serverID;
        this.isAvailable = true;
        this.hasWaitingCustomer = false;
        this.nextAvailableTime = 0;
    }

    public int getServerID() {
        return this.serverID;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public boolean getHasWaitingCustomer() {
        return this.hasWaitingCustomer;
    }

    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    public Server setIsAvailable(boolean bool) {
        return new Server(this.serverID, bool, this.hasWaitingCustomer, this.nextAvailableTime);
    }

    public Server setHasWaitingCustomer(boolean bool) {
        return new Server(this.serverID, this.isAvailable, bool, this.nextAvailableTime);
    }

    public Server setNextAvailableTime(double time) {
        return new Server(this.serverID, this.isAvailable, this.hasWaitingCustomer, time);
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
