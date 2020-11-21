import cs2030.simulator.ArriveEvent;
import cs2030.simulator.Customer;
import cs2030.simulator.DoneEvent;
import cs2030.simulator.Event;
import cs2030.simulator.LeaveEvent;
import cs2030.simulator.Pair;
import cs2030.simulator.RNG;
import cs2030.simulator.ServeEvent;
import cs2030.simulator.Shop;

import java.util.PriorityQueue;

public class Main {
    private static void printStatistics(int customersServed, int customersLeft, double totalWaitingTime) {
        if (customersServed == 0) {
            System.out.println("[0.000 " + customersServed + " " + customersLeft + "]");
        } else {
            double averageWaitingTime = totalWaitingTime / customersServed;
            System.out.println("[" + String.format("%.3f", averageWaitingTime) + " " + customersServed + " " + customersLeft + "]");
        }
    }

    public static void main(String args[]) {
        int seed;
        int numOfServers;
        int numOfCustomers;
        int maxQueue;
        double arrivalRate;
        double serviceRate;

        // Statistics
        int customersServed = 0;
        int customersLeft = 0;
        double totalWaitingTime = 0;

        // Parse input
        switch (args.length) {
        case 6:
            seed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            maxQueue = Integer.parseInt(args[2]);
            numOfCustomers = Integer.parseInt(args[3]);
            arrivalRate = Double.parseDouble(args[4]);
            serviceRate = Double.parseDouble(args[5]);
            break;
        default:
            seed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            maxQueue = 1;
            numOfCustomers = Integer.parseInt(args[2]);
            arrivalRate = Double.parseDouble(args[3]);
            serviceRate = Double.parseDouble(args[4]);
        }

        RNG rng = new RNG(seed, arrivalRate, serviceRate, 1.0);
        PriorityQueue<Event> pq = new PriorityQueue<>();

        double arrivalTime = 0;
        for (int i = 1; i <= numOfCustomers; i++) {
            pq.add(new ArriveEvent(new Customer(i, arrivalTime), rng));
            arrivalTime += rng.genInterArrivalTime();
        }

        Shop shop = new Shop(numOfServers, maxQueue);

        while(pq.size() > 0) {
            Event event = pq.poll();
            System.out.println(event);

            if (event instanceof DoneEvent) {
                customersServed++;
            } else if (event instanceof LeaveEvent) {
                customersLeft++;
            } else if (event instanceof ServeEvent) {
                totalWaitingTime += event.getStartTime() - event.getCustomer().getArrivalTime();
            }

            Pair<Shop, Event> pair = event.execute(shop);
            if (pair.second() != null) {
                pq.add(pair.second());
            }
            shop = pair.first();
        }
        printStatistics(customersServed, customersLeft, totalWaitingTime);
    }
}
