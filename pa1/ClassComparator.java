import java.util.Comparator;

public class ClassComparator implements Comparator<Class> {
    @Override
    public int compare(Class a, Class b) {
        if (a.getStartTime() - b.getStartTime() == 0) {
            if (a.getEndTime() - b.getEndTime() == 0) {
                return a.getID() - b.getID();
            } else {
                return a.getEndTime() - b.getEndTime();
            }
        }
        return a.getStartTime() - b.getStartTime();
    }
}
