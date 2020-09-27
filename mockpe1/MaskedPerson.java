import java.util.Arrays;
import java.util.List;

public class MaskedPerson extends Person {
    public MaskedPerson(String name) {
        super(name);
    }

    public MaskedPerson(String name, List<Virus> listOfViruses) {
        super(name, listOfViruses);
    }

    @Override
    public List<Virus> transmit(double random) {
        if (random <= SimulationParameters.MASK_EFFECTIVENESS) {
            return Arrays.asList(new Virus[0]);
        } else {
            return super.transmit(random);
        }
    }

    @Override
    public Person infectWith(List<Virus> listOfViruses, double random) {
        if (random <= SimulationParameters.MASK_EFFECTIVENESS) {
            return new MaskedPerson(this.name, Arrays.asList(new Virus[0]));
        } else {
            return super.infectWith(listOfViruses, random);
        }
    }
}
