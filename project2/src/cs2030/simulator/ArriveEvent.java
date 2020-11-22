package cs2030.simulator;

import java.util.Optional;

public class ArriveEvent extends Event {
    public ArriveEvent(Customer customer) {
        super(customer, customer.getArrivalTime(), 4, shop -> {
            Optional<Server> availableServer = shop.find(Server::isAvailable);
            if (availableServer.isPresent()) {
                Server server = availableServer.get();
                return Pair.of(shop, new ServeEvent(customer, customer.getArrivalTime(), server));
            }

            if (customer.isGreedy()) {
                Server greedyServer = shop.getGreedyServer();
                if (greedyServer.getQueue().size() < greedyServer.getMaxQueue()) {
                    return Pair.of(shop, new WaitEvent(customer, customer.getArrivalTime(), greedyServer));
                }
            }

            Optional<Server> queueableServer = shop.find(Server::queueable);
            if (queueableServer.isPresent()) {
                Server server = queueableServer.get();
                return Pair.of(shop, new WaitEvent(customer, customer.getArrivalTime(), server));
            }

            Optional<Server> noWaitingServer = shop.find(x -> !x.hasWaitingCustomer());
            if (noWaitingServer.isPresent()) {
                Server server = noWaitingServer.get();
                return Pair.of(shop, new WaitEvent(customer, customer.getArrivalTime(), server));
            }

            return Pair.of(shop, new LeaveEvent(customer, customer.getArrivalTime()));
        });
    }

    @Override
    public String toString() {
        return super.toString() + " arrives";
    }
}
