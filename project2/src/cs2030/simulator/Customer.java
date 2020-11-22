package cs2030.simulator;

import java.util.function.Supplier;

/**
 * Customer represents each arriving customer that is tagged with a customerID an arrivalTime.
 */
public class Customer {
    private final int customerID;
    private final double arrivalTime;
    private final Supplier<Double> serviceTime;
    private final boolean isGreedy;

    /**
     * Constructor for a Customer with default serviceTime.
     * @param customerID ID of the customer
     * @param arrivalTime Time of arrival of the customer
     */
    public Customer(int customerID, double arrivalTime) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = () -> 1.0;
        this.isGreedy = false;
    }

    /**
     * Constructor for a Customer with generated serviceTime.
     * @param customerID ID of the customer
     * @param arrivalTime Time of arrival of the customer
     * @param serviceTime Supplier for serviceTime of the customer
     */
    public Customer(int customerID, double arrivalTime, Supplier<Double> serviceTime) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.isGreedy = false;
    }

    /**
     * Constructor for a Customer with generated serviceTime and isGreedy.
     * @param customerID ID of the customer
     * @param arrivalTime Time of arrival of the customer
     * @param serviceTime Supplier for the serviceTime of the customer
     * @param isGreedy Whether the customer is greedy or not
     */
    public Customer(int customerID, double arrivalTime, Supplier<Double> serviceTime, boolean isGreedy) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.isGreedy = isGreedy;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }


    public double getServiceTime() {
        return this.serviceTime.get();
    }

    public boolean isGreedy() {
        return this.isGreedy;
    }

    @Override
    public String toString() {
        return this.customerID + " arrives at " + String.format("%.3f", this.arrivalTime);
    }
}
