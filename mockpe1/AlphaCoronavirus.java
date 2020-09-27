public class AlphaCoronavirus extends Virus {
    public AlphaCoronavirus(double probabilityOfMutating) {
        super("Alpha Coronavirus", probabilityOfMutating);
    }

    @Override
    public Virus spread(double random) {
        if (random <= probabilityOfMutating) {
            return new SARS_CoV_2(probabilityOfMutating);
        } else {
            return new AlphaCoronavirus(probabilityOfMutating * SimulationParameters.VIRUS_MUTATION_PROBABILITY_REDUCTION);
        }
    }
}
