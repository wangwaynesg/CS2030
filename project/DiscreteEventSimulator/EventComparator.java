import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getStartTime() < e2.getStartTime()) {
            return -1;
        }
        if (e1.getStartTime() > e2.getStartTime()) {
            return 1;
        }
        return 0;
    }
}
