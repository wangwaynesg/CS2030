public class ShareARide extends Service {
    public int computeFare(Request request) {
        return (request.getDistance() * 50 + (request.isPeakHour() ? 500 : 0)) / request.getNumberOfPassengers();
    }

    @Override
    public String toString() {
        return "ShareARide";
    }
}
