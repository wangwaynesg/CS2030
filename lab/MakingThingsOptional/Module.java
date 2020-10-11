import java.util.HashMap;
import java.util.Map;

public class Module implements Keyable {
    private final HashMap<String, String> assessments;
    private final String title;

    public Module(String title) {
        this.title = title;
        this.assessments = new HashMap<>();
    }

    public Module(String title, HashMap<String, String> assessments) {
        this.title = title;
        this.assessments = assessments;
    }

    public String getKey() {
        return this.title;
    }

    public Module put(Assessment assessment) {
        HashMap<String, String> temp = new HashMap<>();
        for (Map.Entry<String, String> entry : this.assessments.entrySet()) {
            temp.put(entry.getKey(), entry.getValue());
        }
        temp.put(assessment.getKey(), assessment.getGrade());
        return new Module(this.title, temp);
    }

    @Override
    public String toString() {
        String output = "";
        for (Map.Entry<String, String> entry : this.assessments.entrySet()) {
            output += new Assessment(entry.getKey(), entry.getValue()).toString();
        }
        return this.title + ": {" + "}";
    }
}
