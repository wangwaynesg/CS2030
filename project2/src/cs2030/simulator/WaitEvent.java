package cs2030.simulator;

public class WaitEvent extends Event {
    private final Server eventServer;

    public WaitEvent(Customer customer, double startTime, Server server) {
        super(customer, startTime, 0, shop -> {
            Server newServer = shop.find(x -> x.getIdentifier() == server.getIdentifier()).get();
            newServer = newServer.addToQueue(customer);
            newServer = new Server(newServer.getIdentifier(),
                    newServer.isAvailable(),
                    true,
                    newServer.getNextAvailableTime(),
                    newServer.getMaxQueue(),
                    newServer.getQueue(),
                    newServer.isHuman());
            return Pair.of(shop.replace(newServer), new ServeEvent(customer, newServer.getNextAvailableTime(), newServer));
        });
        this.eventServer = server;
    }

    @Override
    public String toString() {
        return super.toString() + " waits to be served by "
                + (eventServer.isHuman() ? "server " : "self-check ") + eventServer.getIdentifier();
    }
}
