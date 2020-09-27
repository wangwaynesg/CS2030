public class SARS_CoV_2 extends Virus {
    public SARS_CoV_2(double probabilityOfMutating) {
        super("SARS-CoV-2", probabilityOfMutating);
    }

    @Override
    public Virus spread(double random) {
        if(random <= probabilityOfMutating) {
            return new BetaCoronavirus();
        } else {
            return new SARS_CoV_2(probabilityOfMutating * SimulationParameters.VIRUS_MUTATION_PROBABILITY_REDUCTION);
        }
    }
}
