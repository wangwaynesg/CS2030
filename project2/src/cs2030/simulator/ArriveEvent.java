package cs2030.simulator;

import java.util.Optional;

/**
 * Represents an arrival of a customer.
 */
public class ArriveEvent extends Event {
    public ArriveEvent(Customer customer) {
        super(customer, customer.getArrivalTime(), 1, shop -> {
            Optional<Server> temp = shop.find(Server::isAvailable);
            if (temp.isPresent()) {
                Server server = temp.get();
                server = server.setIsAvailable(false);
                server = server.setNextAvailableTime(Math.max(customer.getArrivalTime(), server.getNextAvailableTime()));
                return Pair.of(shop, new ServeEvent(customer, server.getIdentifier()));
            }
            temp = shop.find(x -> !x.getHasWaitingCustomer());
            if (temp.isPresent()) {
                Server server = temp.get();
                server = server.setHasWaitingCustomer(true);
                return Pair.of(shop, new WaitEvent(customer, server.getIdentifier()));
            }
            return Pair.of(shop, new LeaveEvent(customer));
        });
    }

    public ArriveEvent(Customer customer, RNG rng) {
        super(customer, customer.getArrivalTime(), 1, rng, shop -> {
            Optional<Server> temp = shop.find(Server::isAvailable);
            if (temp.isPresent()) {
                Server server = temp.get();
                server = server.setIsAvailable(false);
                server = server.setNextAvailableTime(Math.max(customer.getArrivalTime(), server.getNextAvailableTime()));
                return Pair.of(shop.replace(server),
                        new ServeEvent(customer, server.getIdentifier(), server.getNextAvailableTime(), rng));
            }
            temp = shop.find(Server::isWaitable);
            if (temp.isPresent()) {
                Server server = temp.get();
                server = server.addToQueue(1);
                if (server.getCurrentQueue() > 0) {
                    server = server.setHasWaitingCustomer(true);
                }
                return Pair.of(shop.replace(server),
                        new WaitEvent(customer, server.getIdentifier(), rng));
            }
            return Pair.of(shop, new LeaveEvent(customer));
        });
    }

    @Override
    public String toString() {
        return super.toString() + " arrives";
    }
}
