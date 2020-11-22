package cs2030.simulator;

public class ServeEvent extends Event {
    private final Server eventServer;

    public ServeEvent(Customer customer, double startTime, Server server) {
        super(customer, startTime, 2, shop -> {
            Server newServer = shop.find(x -> x.getIdentifier() == server.getIdentifier()).get();
            double serviceTime = customer.getServiceTime();
            double nextAvailableTime = startTime + serviceTime;

            if (newServer.hasQueue()) {
                newServer.poll();
            }

            newServer = new Server(newServer.getIdentifier(),
                    false,
                    newServer.hasQueue(),
                    startTime + serviceTime,
                    newServer.getMaxQueue(),
                    newServer.getQueue(),
                    newServer.isHuman());

            return Pair.of(shop.replace(newServer), new DoneEvent(customer, nextAvailableTime, newServer));
        });
        this.eventServer = server;
    }

    @Override
    public String toString() {
        return super.toString() + " served by " +
                (eventServer.isHuman() ? "server " : "self-check ") + eventServer.getIdentifier();
    }
}
