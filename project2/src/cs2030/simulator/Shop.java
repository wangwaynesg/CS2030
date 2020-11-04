package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Shop {
    private final List<Server> servers;

    public Shop(int n) {
        servers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            servers.add(new Server(i));
        }
    }

    public Shop(List<Server> servers) {
        this.servers = servers;
    }

    @Override
    public String toString() {
        return this.servers.toString();
    }
}
