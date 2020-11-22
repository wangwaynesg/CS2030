import cs2030.simulator.ArriveEvent;
import cs2030.simulator.Customer;
import cs2030.simulator.DoneEvent;
import cs2030.simulator.Event;
import cs2030.simulator.LeaveEvent;
import cs2030.simulator.Pair;
import cs2030.simulator.RNG;
import cs2030.simulator.ServeEvent;
import cs2030.simulator.Server;
import cs2030.simulator.ServerBackEvent;
import cs2030.simulator.ServerRestEvent;
import cs2030.simulator.Shop;
import cs2030.simulator.WaitEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Main {
    private static void printStatistics(int customersServed, int customersLeft, double totalWaitingTime) {
        if (customersServed == 0) {
            System.out.println("[0.000 " + customersServed + " " + customersLeft + "]");
        } else {
            double averageWaitingTime = totalWaitingTime / customersServed;
            System.out.println("[" + String.format("%.3f", averageWaitingTime) + " " + customersServed + " " + customersLeft + "]");
        }
    }

    private static List<Customer> getCustomers(List<Double> arrivalTimes, double probabilityGreedy, RNG rng) {
        ArrayList<Customer> customers = new ArrayList<>();
        IntStream.rangeClosed(1, arrivalTimes.size())
                .forEach(x -> {
                    customers.add(new Customer(x,
                            arrivalTimes.get(x - 1),
                            () -> rng.genServiceTime(),
                            rng.genCustomerType() < probabilityGreedy));
                });
        return customers;
    }

    private static List<Double> getArrivalTimes(int numOfCustomers, RNG rng) {
        ArrayList<Double> arrivalTimes = new ArrayList<>();
        arrivalTimes.add(0.0);
        IntStream.rangeClosed(2, numOfCustomers)
                .forEach(x -> {
                    arrivalTimes.add(arrivalTimes.get(arrivalTimes.size() - 1) + rng.genInterArrivalTime());
                });
        return arrivalTimes;
    }

    public static void main(String[] args) {
        // Statistics
        int customersServed = 0;
        int customersLeft = 0;
        double totalWaitingTime = 0;

        int seed = Integer.parseInt(args[0]);
        int numOfServers = Integer.parseInt(args[1]);
        int numOfSelfCheckout = 0;
        int maxQueue = 1;
        int numOfCustomers;

        double arrivalRate;
        double serviceRate;
        double restingRate = 0;
        double probabilityRest = 0;
        double probabilityGreedy = 0;

        // Parse input
        switch (args.length) {
        case 5:
            numOfCustomers = Integer.parseInt(args[2]);
            arrivalRate = Double.parseDouble(args[3]);
            serviceRate = Double.parseDouble(args[4]);
            break;
        case 6:
            maxQueue = Integer.parseInt(args[2]);
            numOfCustomers = Integer.parseInt(args[3]);
            arrivalRate = Double.parseDouble(args[4]);
            serviceRate = Double.parseDouble(args[5]);
            break;
        case 8:
            maxQueue = Integer.parseInt(args[2]);
            numOfCustomers = Integer.parseInt(args[3]);
            arrivalRate = Double.parseDouble(args[4]);
            serviceRate = Double.parseDouble(args[5]);
            restingRate = Double.parseDouble(args[6]);
            probabilityRest = Double.parseDouble(args[7]);
            break;
        case 9:
            numOfSelfCheckout = Integer.parseInt(args[2]);
            maxQueue = Integer.parseInt(args[3]);
            numOfCustomers = Integer.parseInt(args[4]);
            arrivalRate = Double.parseDouble(args[5]);
            serviceRate = Double.parseDouble(args[6]);
            restingRate = Double.parseDouble(args[7]);
            probabilityRest = Double.parseDouble(args[8]);
            break;
        default:
            numOfSelfCheckout = Integer.parseInt(args[2]);
            maxQueue = Integer.parseInt(args[3]);
            numOfCustomers = Integer.parseInt(args[4]);
            arrivalRate = Double.parseDouble(args[5]);
            serviceRate = Double.parseDouble(args[6]);
            restingRate = Double.parseDouble(args[7]);
            probabilityRest = Double.parseDouble(args[8]);
            probabilityGreedy = Double.parseDouble(args[9]);
            break;
        }

        RNG rng = new RNG(seed, arrivalRate, serviceRate, restingRate);
        List<Double> arrivalTimes = getArrivalTimes(numOfCustomers, rng);
        List<Customer> customerList = getCustomers(arrivalTimes, probabilityGreedy, rng);

        PriorityQueue<Event> pq = new PriorityQueue<>();
        PriorityQueue<Event> printable = new PriorityQueue<>();

        for (Customer customer : customerList) {
            pq.offer(new ArriveEvent(customer));
        }

        Shop shop = new Shop(numOfServers, maxQueue, numOfSelfCheckout);


        while (pq.peek() != null) {
            Event event = pq.poll();
            printable.offer(event);

            if (event instanceof ServeEvent) {
                customersServed++;
                totalWaitingTime += event.getStartTime() - event.getCustomer().getArrivalTime();

                Pair<Shop, Event> pair = event.execute(shop);
                shop = pair.first();
                pq.offer(pair.second());
            } else if (event instanceof ArriveEvent) {
                Pair<Shop, Event> pair = event.execute(shop);
                shop = pair.first();
                pq.offer(pair.second());
            } else if (event instanceof WaitEvent) {
                Pair<Shop, Event> pair = event.execute(shop);
                shop = pair.first();
            } else if (event instanceof DoneEvent) {
                Pair<Shop, Event> pair = event.execute(shop);
                shop = pair.first();

                DoneEvent nextEvent = (DoneEvent) pair.second();
                Server newServer = shop.find(x -> x.getIdentifier() == nextEvent.getEventServer().getIdentifier()).get();

                if (nextEvent.getEventServer().isHuman()) {
                    if (rng.genRandomRest() < probabilityRest) {
                        pq.offer(new ServerRestEvent(nextEvent.getCustomer(),
                                nextEvent.getStartTime(),
                                nextEvent.getEventServer(),
                                rng.genRestPeriod()));
                    } else if (newServer.hasQueue()) {
                        pq.offer(new ServeEvent(nextEvent.getCustomer(),
                                nextEvent.getStartTime(),
                                nextEvent.getEventServer()));
                    }
                } else if (newServer.hasQueue()) {
                    pq.offer(new ServeEvent(nextEvent.getCustomer(),
                            nextEvent.getStartTime(),
                            nextEvent.getEventServer()));
                }
            } else if (event instanceof ServerRestEvent) {
                printable.remove(event);
                Pair<Shop, Event> pair = event.execute(shop);
                shop = pair.first();
                pq.offer(pair.second());
            } else if (event instanceof ServerBackEvent) {
                printable.remove(event);
                Pair<Shop, Event> pair = event.execute(shop);
                shop = pair.first();

                ServerBackEvent nextEvent = (ServerBackEvent) pair.second();
                Server newServer = shop.find(x -> x.getIdentifier() == nextEvent.getEventServer().getIdentifier()).get();

                if (newServer.hasQueue()) {
                    pq.offer(new ServeEvent(nextEvent.getCustomer(),
                            nextEvent.getStartTime(),
                            nextEvent.getEventServer()));
                }
            } else if (event instanceof LeaveEvent) {
                customersLeft++;
            }
        }

        for (Event e : printable) {
            System.out.println(e);
        }

        printStatistics(customersServed, customersLeft, totalWaitingTime);
    }
}
