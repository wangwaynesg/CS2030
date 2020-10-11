public class BetaCoronavirus extends Virus {
    public BetaCoronavirus() {
        super("Beta Coronavirus", 0);
    }

    @Override
    public Virus spread(double random) {
        return new BetaCoronavirus();
    }
}
