package cs2030.simulator;

public class RNG {
    private final RandomGenerator randomGenerator;

    public RNG(int seed, double lambda, double mu, double rho) {
        this.randomGenerator = new RandomGenerator(seed, lambda, mu, rho);
    }

    public double genCustomerType() {
        return randomGenerator.genCustomerType();
    }

    public double genInterArrivalTime() {
        return randomGenerator.genInterArrivalTime();
    }

    public double genRandomRest() {
        return randomGenerator.genRandomRest();
    }

    public double genRestPeriod() {
        return randomGenerator.genRestPeriod();
    }

    public double genServiceTime() {
        return randomGenerator.genServiceTime();
    }
}