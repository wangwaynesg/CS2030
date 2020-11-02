/**
 * Cruise is the superclass for SmallCruise and BigCruise
 * identifier: unique String identifier
 * arrivalTime: time of arrival in HHMM format
 * numOfLoader: number of loaders needed to serve the cruise
 * serviceTime: time needed to serve the cruise (in minutes) 
 */

public class Cruise {
    private final String identifier;
    private final int arrivalTime;
    private final int numOfLoader;
    private final int serviceTime;

    public Cruise(String identifier, int arrivalTime, int numOfLoader, int serviceTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.numOfLoader = numOfLoader;
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {
        int hours = this.arrivalTime / 100;
        int minutes = this.arrivalTime % 100;
        return hours * 60 + minutes;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public int getServiceTime() {
        return this.serviceTime;
    }

    public int getNumOfLoadersRequired() {
        return this.numOfLoader;
    }

    public int getServiceCompletionTime() {
        return this.getArrivalTime() + this.serviceTime;
    }

    @Override
    public String toString() {
        return this.identifier + "@" + String.format("%04d", this.arrivalTime);
    }
}
