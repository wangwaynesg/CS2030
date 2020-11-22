package cs2030.simulator;

public class LeaveEvent extends Event {
    public LeaveEvent(Customer customer, double startTime) {
        super(customer, startTime, 3, shop -> Pair.of(shop,
                new LeaveEvent(customer, startTime)));
    }

    @Override
    public String toString() {
        return super.toString() + " leaves";
    }
}
