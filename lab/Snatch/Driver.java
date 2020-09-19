public class Driver {
    private final String license;
    private final int waitingTime;

    public Driver (String license, int waitingTime) {
        this.license = license;
        this.waitingTime = waitingTime;
    }

    public int getWaitingTime() {
        return this.waitingTime;
    }

    @Override
    public String toString() {
        return this.license + " (" + this.waitingTime + " mins away)";
    }
}
