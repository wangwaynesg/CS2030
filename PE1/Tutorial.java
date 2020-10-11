public class Tutorial extends Class {
    private final int duration;

    public Tutorial(String module, int id, String venue, Instructor instructor, int startTime) {
        super(module, id, venue, instructor, startTime);
        duration = 1;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getEndTime() {
        return this.getStartTime() + 1;
    }

    @Override
    public boolean clashWith(Class c) {
        boolean timeClash = (this.getStartTime() >= c.getStartTime() && this.getStartTime() < c.getEndTime()) || 
                (c.getStartTime() >= this.getStartTime() && c.getStartTime() < this.getEndTime());
        if (c instanceof Tutorial) {
            if (timeClash) {
                return this.hasSameInstructor(c) || this.hasSameVenue(c);
            } else {
                return false;
            }
        } else {
            return this.hasSameModule(c) && timeClash;
        }
    }
}
