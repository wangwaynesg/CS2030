public class Booking {
    private final Driver driver;
    private final Request request;

    public Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;
    }

    public int findLowestFare() {
        int justRideFare = new JustRide().computeFare(request);
        int takeACabFare = new TakeACab().computeFare(request);
        int shareARideFare = new ShareARide().computeFare(request);

        if (driver instanceof NormalCab) {
            return Math.min(justRideFare, takeACabFare);
        } else if (driver instanceof PrivateCar) {
            return Math.min(justRideFare, shareARideFare);
        }
        return -1;
    }

    public String findBestService() {
        if (driver instanceof NormalCab) {
            return (new JustRide().computeFare(request)) < (new TakeACab().computeFare(request)) ? "(JustRide)" : "(TakeACab)";
        } else if (driver instanceof PrivateCar) {
            return (new JustRide().computeFare(request)) < (new ShareARide().computeFare(request)) ? "(JustRide)" : "(ShareARide)";
        }
        return "";
    }

    public int compareTo(Booking booking) {
        if (this.findLowestFare() - booking.findLowestFare() != 0) {
            return this.findLowestFare() - booking.findLowestFare();
        } else {
            return this.driver.getWaitingTime() - booking.getDriver().getWaitingTime();
        }
    }

    public Driver getDriver() {
        return this.driver;
    }

    @Override
    public String toString() {
        return "$" + String.format("%.2f", ((double) this.findLowestFare()) / 100) + " using " + this.driver.toString() + " " + this.findBestService();
    }
}
