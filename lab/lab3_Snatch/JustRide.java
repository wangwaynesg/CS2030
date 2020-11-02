public class JustRide extends Service {
    public int computeFare(Request request) {
        return request.getDistance() * 22 + (request.isPeakHour() ? 500 : 0);
    }

    @Override
    public String toString() {
        return "JustRide";
    }
}
