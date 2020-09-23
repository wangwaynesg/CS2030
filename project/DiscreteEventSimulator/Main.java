import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;
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

        //Comparator<Event> comparator = new EventComparator();
        PriorityQueue<Event> queue = new PriorityQueue<Event>();

        int customerID = 1;

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            System.out.println(arrivalTime);
            Event event = new ArriveEvent(new Customer(customerID++, arrivalTime), list);
            queue.add(event);
        }

        while (queue.size() != 0) {
//            System.out.println(queue);

            Event event = queue.remove();
            System.out.println(event);
            if (event instanceof DoneEvent || event instanceof LeaveEvent) {
                event.execute();
            } else {
                queue.add(event.execute());
            }
        }
    }
}
