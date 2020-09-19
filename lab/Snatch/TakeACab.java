public class TakeACab extends Service {
    public int computeFare(Request request) {
        return request.getDistance() * 33 + 200;
    }

    @Override
    public String toString() {
        return "TakeACab";
    }
}
