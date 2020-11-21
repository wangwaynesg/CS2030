package cs2030.simulator;

public class DoneEvent extends Event {
    private final int index;

    public DoneEvent(Customer customer, int index, double startTime) {
        super(customer, startTime, 4, shop -> {
            Server server = shop.find(x -> index == x.getIdentifier()).get();
            if (!server.getHasWaitingCustomer()) {
                server = server.setIsAvailable(true);
            }
            return Pair.of(shop.replace(server), null);
        });
        this.index = index;
    }

    @Override
    public String toString() {
        return super.toString() + " done serving by server " + index;
    }
}