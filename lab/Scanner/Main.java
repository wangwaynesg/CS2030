import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String line= null;
        Scanner s = new Scanner(System.in);
        
        line = s.nextLine();
        int a = Integer.parseInt(line);
        System.out.println(a - 1);

        line = s.nextLine();
        double b = Double.parseDouble(line);
        System.out.println(b - 1.0);

        line = s.nextLine();
        int n = Integer.parseInt(line);

        for (int i = 0; i < n; i++) {
            line = s.nextLine();
            System.out.println(line);
        }

        line = s.nextLine();
        String[] tokens = line.split(" ");
        int t = Integer.parseInt(tokens[0]);

        for (int i = 1; i <= t; i++) {
            System.out.println(tokens[i]);
        }
    }
}
