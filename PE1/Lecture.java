public class Lecture extends Class {
    private final int duration;

    public Lecture(String module, int id, String venue, Instructor instructor, int startTime) {
        super(module, id, venue, instructor, startTime);
        duration = 2;
    }

    public int getDuration() {
        return this.duration;
    }
	
	@Override
	public boolean clashWith(Class c) {
		if (c instanceof Lecture) {
            boolean lectureTimeClash = !((this.getStartTime() + 2) <= c.getStartTime() &&
                    (this.getStartTime() - 2) > c.getStartTime());
			return this.hasSameVenue(c) && lectureTimeClash;
		} else {
			return this.hasSameModule(c) &&
                    ((c.getStartTime() + 2) > this.getStartTime() && 
					(c.getStartTime() - 1) < this.getStartTime());
		}
	}
}
