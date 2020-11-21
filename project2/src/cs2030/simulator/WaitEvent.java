package cs2030.simulator;

public class WaitEvent extends Event {
    private final int index;

    public WaitEvent(Customer customer, int index) {
        super(customer, customer.getArrivalTime(), 0, shop -> {
            Server server = shop.find(x -> index == x.getIdentifier()).get();
            server = server.setNextAvailableTime(Math.max(customer.getArrivalTime(), server.getNextAvailableTime()));
            return Pair.of(shop, new ServeEvent(customer, index));
        });
        this.index = index;
    }

    public WaitEvent(Customer customer, int index, RNG rng) {
        super(customer, customer.getArrivalTime(), 0, rng, shop -> {
            Server server = shop.find(x -> index == x.getIdentifier()).get();
            server = server.setNextAvailableTime(Math.max(customer.getArrivalTime(), server.getNextAvailableTime()));
            return Pair.of(shop.replace(server),
                    new ServeEvent(customer, index, server.getNextAvailableTime(), rng));
        });
        this.index = index;
    }

    @Override
    public String toString() {
        return super.toString() + " waits to be served by server " + index;
    }
}
