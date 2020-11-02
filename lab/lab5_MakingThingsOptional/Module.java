import java.util.HashMap;
import java.util.Map;

public class Module extends KeyableMap<String, Assessment> {
    public Module(String title) {
        super(title);
    }

    public Module(String title, HashMap<String, Assessment> assessments) {
        super(title, assessments);
    }

    public Module put(Assessment assessment) {
        HashMap<String, Assessment> temp = new HashMap<>();
        temp = this.item;
        temp.put(assessment.getKey(), assessment);
        return new Module(this.key, temp);
    }
}
