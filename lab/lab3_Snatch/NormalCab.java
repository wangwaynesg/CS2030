public class NormalCab extends Driver {
    public NormalCab(String license, int waitingTime) {
        super(license, waitingTime);
    }

    @Override
    public String toString() {
        return super.toString() + " NormalCab";
    }
}
