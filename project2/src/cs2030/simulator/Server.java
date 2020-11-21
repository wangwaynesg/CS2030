package cs2030.simulator;

/**
 * Server represents each server that has a <code>identifier</code>, <code>isAvailable</code> status,
 * <code>hasWaitingCustomer</code> status and a <code>nextAvailableTime</code>.
 */
public class Server {
    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;
    private final int maxQueue;
    private final int currentQueue;

    /**
     * Constructor for creating a new Server with all the respective 6 attributes.
     * @param identifier server ID of the server
     * @param isAvailable status of whether the server is available to accept a new customer
     * @param hasWaitingCustomer status of whether there is a customer waiting in line for this server
     * @param nextAvailableTime the next time at which the server is available
     * @param maxQueue maximum queue length
     * @param currentQueue current queue length
     */
    public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime, int maxQueue, int currentQueue) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.maxQueue = maxQueue;
        this.currentQueue = currentQueue;
    }

    /**
     * Constructor for instantiating a new server for the start base.
     * @param identifier server ID of the server
     */
    public Server(int identifier) {
        this.identifier = identifier;
        this.isAvailable = true;
        this.hasWaitingCustomer = false;
        this.nextAvailableTime = 0;
        this.maxQueue = 1;
        this.currentQueue = 0;
    }

    /**
     * Constructor for instantiating a new server with maximum queues for the start base.
     * @param identifier server ID of the server
     * @param maxQueue maximum queue length
     */
    public Server(int identifier, int maxQueue) {
        this.identifier = identifier;
        this.isAvailable = true;
        this.hasWaitingCustomer = false;
        this.nextAvailableTime = 0;
        this.maxQueue = maxQueue;
        this.currentQueue = 0;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public boolean getHasWaitingCustomer() {
        return this.hasWaitingCustomer;
    }

    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    public int getCurrentQueue() {
        return this.currentQueue;
    }

    public Server setIsAvailable(boolean bool) {
        return new Server(this.identifier, bool, this.hasWaitingCustomer, this.nextAvailableTime, this.maxQueue, this.currentQueue);
    }

    public Server setHasWaitingCustomer(boolean bool) {
        return new Server(this.identifier, this.isAvailable, bool, this.nextAvailableTime, this.maxQueue, this.currentQueue);
    }

    public Server setNextAvailableTime(double time) {
        return new Server(this.identifier, this.isAvailable, this.hasWaitingCustomer, time, this.maxQueue, this.currentQueue);
    }

    public Server addToQueue(int number) {
        return new Server(this.identifier, this.isAvailable, this.hasWaitingCustomer, this.nextAvailableTime, this.maxQueue, this.currentQueue + number);
    }

    public boolean isWaitable() {
        return (maxQueue - currentQueue) > 0;
    }

    @Override
    public String toString() {
        if (this.isAvailable) {
            return this.identifier + " is available";
        } else {
            return this.identifier + " is busy;" +
                    (this.hasWaitingCustomer ? " waiting customer to be served at " : " available at ") +
                    String.format("%.3f", this.nextAvailableTime);
        }
    }
}
