/**
 * BigCruise is the subclass of Cruise
 * identifier: unique String identifier
 * arrivalTime: time of arrival in HHMM format
 * numOfLoader: 1 loader per every 40 meters of lengthOfCruise
 * serviceTimeL 1 minute for every 50 passengers
 *
 * lengthOfCruise: length of cruise
 * numOfPassengers: number of passengers
 */

public class BigCruise extends Cruise {
    private final int lengthOfCruise;
    private final int numOfPassengers;

    public BigCruise(String identifier, int arrivalTime, int lengthOfCruise, int numOfPassengers) {
        super(identifier,
                arrivalTime,
                (lengthOfCruise % 40 == 0) ? lengthOfCruise / 40 : lengthOfCruise / 40 + 1,
                (numOfPassengers % 50 == 0) ? numOfPassengers / 50 : numOfPassengers / 50 + 1);
        this.lengthOfCruise = lengthOfCruise;
        this.numOfPassengers = numOfPassengers;
    }
}
