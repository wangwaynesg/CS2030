package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Shop {
    private final List<Server> servers;

    public Shop(int n) {
        servers = new ArrayList<>();
        IntStream.rangeClosed(1, n)
                .forEach(x -> servers.add(new Server(x, 1)));
    }

    public Shop(int n, int maxQueue) {
        servers = new ArrayList<>();
        IntStream.rangeClosed(1, n)
                .forEach(x -> servers.add(new Server(x, maxQueue)));
    }

    public Shop(List<Server> servers) {
        this.servers = servers;
    }

    public Optional<Server> find(Predicate<Server> predicate) {
        return servers.stream()
                .filter(predicate)
                .findFirst();
    }

    public Shop replace(Server server) {
        List<Server> temp = new ArrayList<>(servers);

        temp = temp.stream()
                .map(x -> server.getIdentifier() == (x.getIdentifier()) ? server : x)
                .collect(Collectors.toList());

        return new Shop(temp);
    }

    @Override
    public String toString() {
        return this.servers.toString();
    }
}
