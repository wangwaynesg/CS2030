import cs2030.simulator.Pair;
import cs2030.simulator.Server;
import cs2030.simulator.Shop;

import java.util.List;

public class Main {
    public static void main(String args[]) {
        new Shop(2);
        Shop shops = new Shop(List.of(new Server(1, true, false, 0), new Server(2, false, false, 1.0)));
        System.out.println(shops);
        System.out.println(shops.find(x -> x.isAvailable()));
        System.out.println(new Shop(2).find(x -> x.isAvailable()));
        shops.find(x -> x.isAvailable()).ifPresent(System.out::println);
        Server s = new Server(1, false, false, 2.0);
        System.out.println(shops.replace(s));
        System.out.println(shops.replace(s).find(x -> x.isAvailable()));
        System.out.println(shops);

        Pair<Integer,String> pair1 = Pair.of(1, "one");
        System.out.println(pair1.first());
        System.out.println(pair1.second());
        Pair<Long,Long> pair2 = Pair.of(0L, 100L);
        System.out.println(pair2.first());
        System.out.println(pair2.second());
    }
}
