public class Request {
    private final int distance;
    private final int numberOfPassengers;
    private final int time;

    public Request(int distance, int numberOfPassengers, int time) {
        this.distance = distance;
        this.numberOfPassengers = numberOfPassengers;
        this.time = time;
    }

    public int getDistance() {
        return this.distance;
    }

    public boolean isPeakHour() {
        return this.time >= 600 && this.time <= 900;
    }

    public int getNumberOfPassengers() {
        return this.numberOfPassengers;
    }

    @Override
    public String toString() {
        return this.distance + "km for " + this.numberOfPassengers + "pax @ " + this.time + "hrs";
    }
}
