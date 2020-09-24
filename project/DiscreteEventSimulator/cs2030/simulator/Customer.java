package cs2030.simulator;

/**
 * Customer represents each arriving customer that is tagged with a
 * <code>customerID</code> and an <code>arrivalTime</code>.
 */
public class Customer {
    private final int customerID;
    private final double arrivalTime;

    public Customer(int customerID, double arrivalTime) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    @Override
    public String toString() {
        return this.customerID + " arrives at " + this.arrivalTime;
    }
}