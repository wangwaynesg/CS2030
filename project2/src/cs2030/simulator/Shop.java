package cs2030.simulator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a Shop with servers.
 */
public class Shop {
    private final List<Server> servers;

    /**
     * Constructor for basic human servers.
     * @param numOfServers Number of servers in the shop
     */
    public Shop(int numOfServers) {
        this.servers = new ArrayList<>();
        IntStream.rangeClosed(1, numOfServers)
                .forEach(identifier -> servers.add(new Server(identifier, true, false, 0)));
    }

    /**
     * Constructor for different human servers.
     * @param numOfServers Number of servers in the shop
     * @param maxQueue Maximum queue per server
     * @param numOfSelfCheckout Number of self-checkout servers in the shop
     */
    public Shop(int numOfServers, int maxQueue, int numOfSelfCheckout) {
        this.servers = new ArrayList<>();
        IntStream.rangeClosed(1, numOfServers)
                .forEach(identifier -> {
                    servers.add(new Server(identifier,
                            true,
                            false,
                            0,
                            maxQueue,
                            new LinkedList<Customer>(),
                            true));
                });
        if (numOfSelfCheckout > 0) {
            IntStream.rangeClosed(1, numOfSelfCheckout)
                    .forEach(identifier -> {
                        servers.add(new Server(numOfServers + identifier,
                                true,
                                false,
                                0,
                                maxQueue,
                                new LinkedList<Customer>(),
                                false));
                    });
        }
    }

    public Shop(List<Server> servers) {
        this.servers = servers;
    }

    /**
     * Search for the first match of the server based on the given predicate.
     * @param predicate Predicate to filter for match of the server
     * @return Optional of first server found
     */
    public Optional<Server> find(Predicate<Server> predicate) {
        return servers.stream()
                .filter(predicate)
                .findFirst();
    }

    /**
     * Returns a new Shop with the replaced server.
     * @param server Server to be replaced
     * @return Shop with the replaced server
     */
    public Shop replace(Server server) {
        List<Server> temp = new ArrayList<>(servers);
        temp = temp.stream()
                .map(x -> server.getIdentifier() == (x.getIdentifier()) ? server : x)
                .collect(Collectors.toList());
        return new Shop(temp);
    }

    /**
     * Returns the first server for the greedy customer.
     * @return The first server found with the smallest queue size
     */
    public Server getGreedyServer() {
        return this.servers.stream()
                .min(Comparator.comparingInt(x -> x.getQueue().size()))
                .get();
    }

    @Override
    public String toString() {
        return this.servers.toString();
    }
}
