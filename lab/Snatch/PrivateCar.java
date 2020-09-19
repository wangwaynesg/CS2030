public class PrivateCar extends Driver {
    public PrivateCar(String license, int waitingTime) {
        super(license, waitingTime);
    }

    @Override
    public String toString() {
        return super.toString() + " PrivateCar";
    }
}
