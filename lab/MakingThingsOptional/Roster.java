import java.util.HashMap;
import java.util.Optional;

public class Roster extends KeyableMap<String, Student> {
    public Roster(String year) {
        super(year);
    }

    public Roster(String year, HashMap<String, Student> students) {
        super(year, students);
    }

    public Roster put(Student student) {
        HashMap<String, Student> temp = new HashMap<>();
        temp = this.item;
        temp.put(student.getKey(), student);
        return new Roster(this.key, temp);
    }

    public String getGrade(String studentName, String moduleTitle, String component) {
        return this.get(studentName)
                .flatMap(x -> x.get(moduleTitle))
                .flatMap(x -> x.get(component))
                .map(Assessment::getGrade)
                .orElse("No such record: " + studentName + " " + moduleTitle + " " + component);
    }
}
