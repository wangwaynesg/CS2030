import cs2030.simulator.Server;
import cs2030.simulator.Shop;

import java.util.List;

public class Main {
    public static void main(String args[]) {
        System.out.println(new Shop(2));

        Shop shops = new Shop(List.of(new Server(1, true, false, 0), new Server(2, false, false, 1.0)));
        System.out.println(shops);
    }
}
