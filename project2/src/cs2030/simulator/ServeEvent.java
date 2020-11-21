package cs2030.simulator;

public class ServeEvent extends Event {
    private final int index;

    public ServeEvent(Customer customer, int index) {
        super(customer, customer.getArrivalTime(), 2, shop -> {
            Server server = shop.find(x -> index == x.getIdentifier()).get();
            server = server.setHasWaitingCustomer(false);
            server = server.setNextAvailableTime(server.getNextAvailableTime() + 1);
            return Pair.of(shop, new DoneEvent(customer, index, server.getNextAvailableTime()));
        });
        this.index = index;
    }

    public ServeEvent(Customer customer, int index, double startTime, RNG rng) {
        super(customer, startTime, 2, rng, shop -> {
            Server server = shop.find(x -> index == x.getIdentifier()).get();
            if (server.getCurrentQueue() > 0) {
                server = server.addToQueue(-1);
            }
            if (server.getCurrentQueue() == 0) {
                server = server.setHasWaitingCustomer(false);
            }
            server = server.setNextAvailableTime(server.getNextAvailableTime() + rng.genServiceTime());
            return Pair.of(shop.replace(server), new DoneEvent(customer, index, server.getNextAvailableTime()));
        });
        this.index = index;
    }

    @Override
    public String toString() {
        return super.toString() + " served by server " + index;
    }
}
