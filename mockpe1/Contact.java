import java.util.List;
import java.util.Arrays;

public class Contact {
    private final Person first;
    private final Person second;
    private final double time;

    public Contact(Person first, Person second, double time) {
        this.first = first;
        this.second = second;
        this.time = time;
    }

    public List<Person> transmit(double random) {
        Person tempFirst = first.infectWith(second.transmit(random), random);
        Person tempSecond = second.infectWith(first.transmit(random), random);

        return Arrays.asList(tempFirst, tempSecond);
    }

    public double timeOfContact() {
        return this.time;
    }

    public List<Person> getPeople() {
        return Arrays.asList(first, second);
    }
}
