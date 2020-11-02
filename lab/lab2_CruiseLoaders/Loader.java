public class Loader {
    private final int identifier;
    private final Cruise cruise;

    public Loader(int identifier, Cruise cruise) {
        this.identifier = identifier;
        this.cruise = cruise;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public int getNextAvailableTime() {
        return this.cruise.getServiceCompletionTime();
    }

    public boolean canServe(Cruise cruise) {
        return this.getNextAvailableTime() <= cruise.getArrivalTime();
    }

    public Loader serve(Cruise cruise) {
        if (this.canServe(cruise)) {
            return new Loader(this.identifier, cruise);
        } else {
            return this;
        }
    }

    public Cruise getCruise() {
        return this.cruise;
    }

    @Override
    public String toString() {
        return "Loader " + this.identifier + " serving " + this.cruise;
    }
}
