package cs2030.simulator;

public class LeaveEvent extends Event {
    public LeaveEvent(Customer customer) {
        super(customer, customer.getArrivalTime(), 3, shop -> Pair.of(shop, null));
    }

    @Override
    public String toString() {
        return super.toString() + " leaves";
    }
}