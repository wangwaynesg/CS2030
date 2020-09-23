import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfServers = sc.nextInt();
        Server[] servers = new Server[numOfServers];
        
        int serverID = 1;
        for (int i = 0; i < numOfServers; i++) {
            servers[i] = new Server(serverID++);
        }
        List<Server> list = Arrays.asList(servers);

        PriorityQueue<Event> queue = new PriorityQueue<Event>();

        int customerID = 1;

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            Event event = new ArriveEvent(new Customer(customerID++, arrivalTime), list);
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
                    totalWaitingTime += event.getStartTime() - event.getCustomerArrivalTime();
                }
                queue.add(event.execute());
            }
        }

        double averageWaitingTime = totalWaitingTime / customersServed;
        System.out.println("[" + String.format("%.3f", averageWaitingTime) + " " + customersServed + " " + customersLeft + "]");
    }
}
