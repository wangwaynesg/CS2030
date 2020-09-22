/**
 * Customer is an immutable class to represent each arriving customer that is tagged with a
 * <code>int customerID</code> and a <code>double arriavlTime</code>.
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
