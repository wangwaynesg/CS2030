public class Tutorial extends Class {
    private final int duration;

    public Tutorial(String module, int id, String venue, Instructor instructor, int startTime) {
        super(module, id, venue, instructor, startTime);
        duration = 1;
    }

    public int getDuration() {
        return this.duration;
    }
	
	@Override
	public boolean clashWith(Class c) {
		if (c instanceof Tutorial) {
			if ((this.getStartTime() + 1) > c.getStartTime() && (this.getStartTime() - 1) < c.getStartTime()) {
                    return this.hasSameInstructor(c) || this.hasSameVenue(c);
                } else {
                    return false;
                }
		} else {
			return this.hasSameModule(c) &&
                    ((this.getStartTime() + 2) > c.getStartTime() && 
					(this.getStartTime() - 1) < c.getStartTime());
		}
	}
}
