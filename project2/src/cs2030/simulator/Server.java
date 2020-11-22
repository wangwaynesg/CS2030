package cs2030.simulator;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Server represents a server which has an identifier, isAvailable status, hasWaitingCustomer status, a nextAvailableTime.
 * Each server can have a maximum queue.
 * There are self-checkout servers which are non-humans.
 */
public class Server {
    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;
    private final int maxQueue;
    private final Queue<Customer> queue;
    private final boolean isHuman;
    private static final Queue<Customer> selfCheckoutQueue = new LinkedList<>();

    /**
     * Constructor for a basic human server for the start base.
     * @param identifier Identifier of the server
     */
    public Server(int identifier,
                  boolean isAvailable,
                  boolean hasWaitingCustomer,
                  double nextAvailableTime) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.maxQueue = 1;
        this.queue = new LinkedList<>();
        this.isHuman = true;
    }

    /**
     * Constructor for a server.
     * @param identifier Identifier of the server
     * @param isAvailable Whether the server is currently available
     * @param hasWaitingCustomer Whether the server currently has a waiting customer
     * @param nextAvailableTime The next available time of the server
     * @param maxQueue Maximum queue size of the server
     * @param isHuman Whether the server is a human or not
     */
    public Server(int identifier,
                  boolean isAvailable,
                  boolean hasWaitingCustomer,
                  double nextAvailableTime,
                  int maxQueue,
                  Queue<Customer> queue,
                  boolean isHuman) {
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.maxQueue = maxQueue;
        this.queue = queue;
        this.isHuman = isHuman;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public boolean hasWaitingCustomer() {
        if (this.isHuman) {
            return this.hasWaitingCustomer;
        } else {
            return Server.selfCheckoutQueue.size() > 0;
        }
    }

    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    public int getMaxQueue() {
        return this.maxQueue;
    }

    public Queue<Customer> getQueue() {
        return this.queue;
    }

    public boolean isHuman() {
        return this.isHuman;
    }

    /**
     * Returns whether the server can be queued at or not.
     * @return boolean whether the server is queueable
     */
    public boolean queueable() {
        return (this.queue.size() > 0 && this.queue.size() < this.maxQueue);
    }

    /**
     * Returns whether the server currently has a queue or not.
     * @return boolean whether the server has a queue
     */
    public boolean hasQueue() {
        if (this.isHuman) {
            return this.queue.size() > 0;
        } else {
            return Server.selfCheckoutQueue.size() > 0;
        }
    }

    /**
     * Adds a customer to the queue of a server and return a new server.
     * @param customer Customer to be added to the queue
     * @return Server after adding the customer
     */
    public Server addToQueue(Customer customer) {
        if (this.isHuman) {
            Queue<Customer> newQueue = new LinkedList<>(this.queue);
            newQueue.offer(customer);
            return new Server(this.identifier,
                    this.isAvailable,
                    this.hasWaitingCustomer,
                    this.nextAvailableTime,
                    this.maxQueue,
                    newQueue,
                    this.isHuman);
        } else {
            Server.selfCheckoutQueue.offer(customer);
            return this;
        }
    }

    /**
     * Peek at the next customer in the queue.
     * @return Customer which is next in queue
     */
    public Customer peek() {
        if (this.isHuman) {
            return this.queue.peek();
        } else {
            return Server.selfCheckoutQueue.peek();
        }
    }

    /**
     * Poll the next customer in queue.
     * @return Customer which is next in queue
     */
    public Customer poll() {
        if (this.isHuman) {
            return this.queue.poll();
        } else {
            return Server.selfCheckoutQueue.poll();
        }
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
