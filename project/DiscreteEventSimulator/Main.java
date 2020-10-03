import cs2030.simulator.Customer;
import cs2030.simulator.Server;
import cs2030.simulator.Event;
import cs2030.simulator.ArriveEvent;
import cs2030.simulator.DoneEvent;
import cs2030.simulator.LeaveEvent;
import cs2030.simulator.ServeEvent;
import cs2030.simulator.WaitEvent;

import java.util.List;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Main class for simulating events.
 *
 * @author Wang Wayne
 * @since 24 September 2020
 */
public class Main {
    /**
     * Creates a <code>List</code> of <code>Server</code> based on user input.
     * @param numOfServers number of servers from user input
     * @return <code>List<Server></code>
     */
    private static List<Server> getServers(int numOfServers) {
        Server[] servers = new Server[numOfServers];

        for (int i = 0; i < numOfServers; i++) {
            servers[i] = new Server(i + 1);
        }
        return Arrays.asList(servers);
    }

    /**
     * Prints the statistics of the system.
     * @param customersServed number of customers served
     * @param customersLeft number of customers left
     * @param totalWaitingTime total waiting time for customers
     */
    private static void printStatistics(int customersServed, int customersLeft, double totalWaitingTime) {
        double averageWaitingTime = totalWaitingTime / customersServed;
        System.out.println("[" + String.format("%.3f", averageWaitingTime) + " " + customersServed + " " + customersLeft + "]");
    }

    /**
     * Main execution loop of the program.
     * @param args should be null
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfServers = sc.nextInt();
        List<Server> servers = getServers(numOfServers);

        PriorityQueue<Event> queue = new PriorityQueue<>();

        int customerID = 1;

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            Event event = new ArriveEvent(new Customer(customerID++, arrivalTime), servers);
            queue.add(event);
        }

        int customersServed = 0;
        int customersLeft = 0;
        double totalWaitingTime = 0;

        while (queue.size() != 0) {
            Event event = queue.remove();
            System.out.println(event);
            if (event instanceof DoneEvent) {
                event.execute();
                customersServed++;
            } else if (event instanceof LeaveEvent) {
                event.execute();
                customersLeft++;
            } else {
                if (event instanceof ServeEvent) {
                    totalWaitingTime += event.getStartTime() - event.getCustomer().getArrivalTime();
                }

                queue.add(event.execute());
            }
        }
        printStatistics(customersServed, customersLeft, totalWaitingTime);
    }
}
