public class RecycledLoader extends Loader {
    public RecycledLoader(int identifier, Cruise cruise) {
        super(identifier, cruise);
    }

    @Override
    public RecycledLoader serve(Cruise cruise) {
        if (this.canServe(cruise)) {
            String identifier = cruise.getIdentifier();
            int arrivalTime = (cruise.getArrivalTime() / 60) * 100 + cruise.getArrivalTime() % 60;
            int numOfLoader = cruise.getNumOfLoadersRequired();
            int serviceTime = cruise.getServiceTime() + 60;
            
            return new RecycledLoader(this.getIdentifier(), new Cruise(identifier, arrivalTime, numOfLoader, serviceTime));
        } else {
            return this;
        }
    }
    
    @Override
    public String toString() {
        return "Recycled " + super.toString();
    }
}