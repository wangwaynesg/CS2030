import java.util.List;
import java.util.Arrays;

public class Person {
    protected final String name;
    private final List<Virus> listOfViruses;

    public Person(String name) {
        this.name = name;
        this.listOfViruses = Arrays.asList(new Virus[0]);
    }

    public Person(String name, List<Virus> listOfViruses) {
        this.name = name;
        this.listOfViruses = listOfViruses;
    }

    /**
     * Returns a List of viruses which are spread by the Person.
     */
    public List<Virus> transmit(double random) {
        Virus[] virusArray = new Virus[this.listOfViruses.size()];
        this.listOfViruses.toArray(virusArray);
        for (int i = 0; i < this.listOfViruses.size(); i++) {
            virusArray[i] = virusArray[i].spread(random);
        }
        return Arrays.asList(virusArray);
    }

    /**
     * Returns a new Person object infected by the list of viruses.
     */
    public Person infectWith(List<Virus> listOfViruses, double random) {
        Virus[] virusArray = new Virus[this.listOfViruses.size() + listOfViruses.size()];
        for (int i = 0; i < this.listOfViruses.size(); i++) {
            virusArray[i] = this.listOfViruses.get(i);
        }

        for (int j = this.listOfViruses.size(); j < virusArray.length; j++) {
            virusArray[j] = listOfViruses.get(j - this.listOfViruses.size());
        }

        if (this instanceof MaskedPerson) {
            return new MaskedPerson(this.name, Arrays.asList(virusArray));
        } else {
            return new Person(this.name, Arrays.asList(virusArray));
        }
    }

    public boolean test(String name) {
        for (Virus virus : listOfViruses) {
            if (name.equals("Alpha Coronavirus") && virus instanceof AlphaCoronavirus) {
                return true;
            } else if (name.equals("SARS-CoV-2") && virus instanceof SARS_CoV_2) {
                return true;
            } else if (name.equals("Beta Coronavirus") && virus instanceof BetaCoronavirus) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
