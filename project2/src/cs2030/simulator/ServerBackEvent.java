package cs2030.simulator;

public class ServerBackEvent extends Event {
    public ServerBackEvent(Customer customer, double startTime, Server server) {
        super(customer, startTime, 0, server, shop -> {
            Server newServer = shop.find(x -> x.getIdentifier() == server.getIdentifier()).get();
            newServer = new Server(newServer.getIdentifier(),
                    true,
                    newServer.hasQueue(),
                    newServer.getNextAvailableTime(),
                    newServer.getMaxQueue(),
                    newServer.getQueue(),
                    newServer.isHuman());
            return Pair.of(shop.replace(newServer),
                    new ServerBackEvent(newServer.peek(), newServer.getNextAvailableTime(), newServer));
        });
    }
}
