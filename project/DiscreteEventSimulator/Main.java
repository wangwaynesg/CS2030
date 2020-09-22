import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfServers = sc.nextInt();
        Server[] servers = new Server[numOfServers];
        
        int serverID = 1;
        for (int i = 0; i < numOfServers; i++) {
            servers[i] = new Server(serverID++);
        }

        Comparator<Event> comparator = new EventComparator();
        PriorityQueue<Event> queue = new PriorityQueue<Event>(comparator);

        int customerID = 1;

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            System.out.println(arrivalTime);
            queue.add(new ArriveEvent(new Customer(customerID++, arrivalTime), Arrays.asList(servers)));
        }

        while (queue.size() != 0) {
            System.out.println(queue);
            Event event = queue.remove();
            System.out.println(event + "\n\n");
            if (!(event instanceof LeaveEvent)) {
                queue.add(event.execute());
            }
        }
    }
}
