public abstract class Virus {
    protected final String name;
    protected final double probabilityOfMutating;

    public Virus(String name, double probabilityOfMutating) {
        this.name = name;
        this.probabilityOfMutating = probabilityOfMutating;
    }

    public abstract Virus spread(double random);

    @Override
    public String toString() {
        return name + " with " + String.format("%.3f", probabilityOfMutating) + " probability of mutating";
    }
}
