/**
 * SmallCruise is a subclass of Cruise
 * identifier: unique String identifier
 * arrivalTime: time of arrival in HHMM format
 * numOfLoader: 1 (default)
 * serviceTime: 30 (default)
 */

public class SmallCruise extends Cruise {
    public SmallCruise(String identifier, int arrivalTime) {
        super(identifier, arrivalTime, 1, 30);
    }
}
