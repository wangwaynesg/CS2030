import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Schedule {
    private final ArrayList<Class> classes;

    public Schedule() {
        this.classes = new ArrayList<Class>();
    }

    public Schedule(ArrayList<Class> classes) {
        this.classes = classes;
    }

    public Schedule add(Class c) {
        ArrayList<Class> temp = new ArrayList<Class>();
        boolean clash = false;
        
        for (Class c1 : this.classes) {
            temp.add(c1);
            if (c.clashWith(c1)) {
                clash = true;
            }
        }

        if (!clash) {
            temp.add(c);
        }

        ClassComparator classComparator = new ClassComparator();
        Collections.sort(temp, classComparator);
        return new Schedule(temp);
    }

    @Override
    public String toString() {
        String answer = "";
        for (int i = 0; i < this.classes.size(); i++) {
            if (this.classes.get(i) instanceof Lecture) {
                answer += this.classes.get(i).getModule() + " L" +
                    this.classes.get(i).getID() + " @ " + 
                    this.classes.get(i).getVenue() + " [" +
                    this.classes.get(i).getInstructor().toString() + "] " +
                    this.classes.get(i).getStartTime() + "--" +
                    this.classes.get(i).getEndTime();
            }

            if (this.classes.get(i) instanceof Tutorial) {
                answer += this.classes.get(i).getModule() + " T" +
                    this.classes.get(i).getID() + " @ " +
                    this.classes.get(i).getVenue() + " [" +
                    this.classes.get(i).getInstructor().toString() + "] " +
                    this.classes.get(i).getStartTime() + "--" +
                    this.classes.get(i).getEndTime();
            }
            
            if (!(i == this.classes.size() - 1)) {
                answer += "\n";
            }
        }
        return answer;
    }
}
