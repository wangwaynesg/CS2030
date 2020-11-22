package cs2030.simulator;

public class DoneEvent extends Event {
    private final Server eventServer;

    public DoneEvent(Customer customer, double startTime, Server server) {
        super(customer, startTime, 5, server, shop -> {
            Server newServer = shop.find(x -> x.getIdentifier() == server.getIdentifier()).get();
            Customer nextCustomer = newServer.peek();

            if (newServer.hasQueue()) {
                newServer = new Server(newServer.getIdentifier(),
                        false,
                        newServer.hasWaitingCustomer(),
                        newServer.getNextAvailableTime(),
                        newServer.getMaxQueue(),
                        newServer.getQueue(),
                        newServer.isHuman());
                return Pair.of(shop.replace(newServer),
                        new DoneEvent(nextCustomer, newServer.getNextAvailableTime(), newServer));
            } else {
                newServer = new Server(newServer.getIdentifier(),
                        true,
                        false,
                        newServer.getNextAvailableTime(),
                        newServer.getMaxQueue(),
                        newServer.getQueue(),
                        newServer.isHuman());
                return Pair.of(shop.replace(newServer),
                        new DoneEvent(customer, newServer.getNextAvailableTime(), newServer));
            }
        });
        this.eventServer = server;
    }

    @Override
    public String toString() {
        return super.toString() + " done serving by " +
                (eventServer.isHuman() ? "server " : "self-check ") + eventServer.getIdentifier();
    }
}
