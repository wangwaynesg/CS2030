public class Lecture extends Class {
    private final int duration;

    public Lecture(String module, int id, String venue, Instructor instructor, int startTime) {
        super(module, id, venue, instructor, startTime);
        duration = 2;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getEndTime() {
        return this.getStartTime() + 2;
    }

    @Override
    public boolean clashWith(Class c) {
        boolean timeClash = (c.getStartTime() >= this.getStartTime() && c.getStartTime() < this.getEndTime()) ||
                (this.getStartTime() >= c.getStartTime() && this.getStartTime() < c.getEndTime());
        if (c instanceof Lecture) {
            return this.hasSameVenue(c) && timeClash;
        } else {
            return this.hasSameModule(c) && timeClash;
        }
    }
}
