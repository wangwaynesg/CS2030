/**
 * Server has 4 attributes: <code>int serverID</code>, <code>boolean isAvailable</code>,
 * <code>boolean hasWaitingCustomer</code> and <code>double nextAvailableTime</code>.
 */
public class Server {
    private final int serverID;
    private boolean isAvailable;
    private boolean hasWaitingCustomer;
    private double nextAvailableTime;

    public Server(int serverID, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime) {
        this.serverID = serverID;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
    }

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

    public void setIsAvailable(boolean bool) {
        this.isAvailable = bool;
    }

    public void setHasWaitingCustomer(boolean bool) {
        this.hasWaitingCustomer = bool;
    }

    public void setNextAvailableTime(double nextAvailableTime) {
        this.nextAvailableTime = nextAvailableTime;
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
