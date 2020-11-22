package cs2030.simulator;

public class ServerRestEvent extends Event {
    public ServerRestEvent(Customer customer, double startTime, Server server, double restingTime) {
        super(customer, startTime, 0, shop -> {
            Server newServer = shop.find(x -> x.getIdentifier() == server.getIdentifier()).get();
            newServer = new Server(newServer.getIdentifier(),
                    false,
                    newServer.hasQueue(),
                    startTime + restingTime,
                    newServer.getMaxQueue(),
                    newServer.getQueue(),
                    newServer.isHuman());
            return Pair.of(shop.replace(newServer),
                    new ServerBackEvent(customer, newServer.getNextAvailableTime(), newServer));
        });
    }
}
