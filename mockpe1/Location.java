import java.util.List;
import java.util.Arrays;

public class Location {
    private final String name;
    private final List<Person> occupants;

    public Location(String name) {
        this.name = name;
        this.occupants = Arrays.asList(new Person[0]);
    }

    public Location(String name, List<Person> occupants) {
        this.name = name;
        this.occupants = occupants;
    }

    public List<Person> getOccupants() {
        return this.occupants;
    }

    public Location accept(Person person) {
        Person[] personArray = new Person[this.occupants.size() + 1];
        for (int i = 0; i < personArray.length - 1; i++) {
            personArray[i] = occupants.get(i);
        }

        personArray[personArray.length - 1] = person;
        return new Location(this.name, Arrays.asList(personArray));
    }

    public Location remove(String personName) {
        Person[] personArray = new Person[this.occupants.size() - 1];

        for (int i = 0, j = 0; i < personArray.length ; i++, j++) {
            if (occupants.get(j).toString().equals(personName)) {
                i--;
                continue;
            } else {
                personArray[i] = occupants.get(j);
            }
        }
        
        return new Location(this.name, Arrays.asList(personArray));
    }

    @Override
    public String toString() {
        return this.name;
    }
}
