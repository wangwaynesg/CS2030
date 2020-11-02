public class Assessment implements Keyable {
    private final String component;
    private final String grade;

    public Assessment(String component, String grade) {
        this.component = component;
        this.grade = grade;
    }

    public String getGrade() {
        return this.grade;
    }

    public String getKey() {
        return this.component;
    }

    @Override
    public String toString() {
        return "{" + this.component + ": " + this.grade + "}";
    }
}
