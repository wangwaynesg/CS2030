public abstract class Class {
    private final String module;
    private final int id;
    private final String venue;
    private final Instructor instructor;
    private final int startTime;

    public Class(String module, int id, String venue, Instructor instructor, int startTime) {
        this.module = module;
        this.id = id;
        this.venue = venue;
        this.instructor = instructor;
        this.startTime = startTime;
    }

    public String getModule() {
        return this.module;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public String getVenue() {
        return this.venue;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getID() {
        return this.id;
    }

    public boolean hasSameModule(Class c) {
        return this.module.equals(c.getModule());
    }

    public boolean hasSameInstructor(Class c) {
        return this.instructor.equals(c.getInstructor());
    }

    public boolean hasSameVenue(Class c) {
        return this.venue.equals(c.getVenue());
    }

    public abstract boolean clashWith(Class c);

    public abstract int getEndTime();
}
