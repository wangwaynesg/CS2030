import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        Roster roster = new Roster("test");

        String[] arguments = new String[4];
        String currentStudent = "";
        String currentModule = "";
        Module module = new Module("temp");
        Student student = new Student("temp");

        if (N > 0) {
            arguments = sc.nextLine().split("\\s+|\\t+");
            currentStudent = arguments[0];
            currentModule = arguments[1];
            student = new Student(arguments[0]);
            module = new Module(arguments[1]);
        }

        for (int i = 0; i < N; i++) {
            if (arguments[0].equals(currentStudent)) {
                if (arguments[1].equals(currentModule)) {
                } else {
                    student = student.put(module);
                    module = new Module(arguments[1]);
                }
                module = module.put(new Assessment(arguments[2], arguments[3]));
            } else {
                roster.put(student.put(module));
                student = new Student(arguments[0]);
                module = new Module(arguments[1]);
                module = module.put(new Assessment(arguments[2], arguments[3]));
            }
            currentStudent = arguments[0];
            currentModule = arguments[1];

            if (i == N - 1) {
                roster.put(student.put(module));
            } else {
                String line = sc.nextLine();
                arguments = line.split("\\s+|\\t+");
            }
        }
        
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] query = line.split("\\s+|\\t+");
            System.out.println(roster.getGrade(query[0], query[1], query[2]));
        }
    }
}
