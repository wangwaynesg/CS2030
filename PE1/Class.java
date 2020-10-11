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

    public boolean clashWith(Class c) {
        if (this instanceof Tutorial) {
            if (c instanceof Tutorial) {
                if ((this.getStartTime() + 1) > c.getStartTime() && (this.getStartTime() - 1) < c.getStartTime()) {
                    return this.hasSameInstructor(c) || this.hasSameVenue(c);
                } else {
                    return false;
                }
            }

            if (c instanceof Lecture) {
                return this.hasSameModule(c) &&
                        ((this.getStartTime() + 2) > c.getStartTime() && (this.getStartTime() - 1) < c.getStartTime());
            }
        } else if (this instanceof Lecture) {
            if (c instanceof Lecture) {
                return /*this.hasSameModule(c) &&*/ this.hasSameVenue(c) &&
                        ((this.getStartTime() + 2) > c.getStartTime() && (this.getStartTime() - 2) < c.getStartTime());
            }
            if (c instanceof Tutorial) {
                return this.hasSameModule(c) &&
                        ((c.getStartTime() + 2) > this.getStartTime() && (c.getStartTime() - 1) < this.getStartTime());
            }
        }

        return false;
    }

    /*
    @Override
    public String toString() {
        if (this instanceof Lecture) {
            return module + " L" + id + " @ " + venue + " [" + instructor.toString() + "] " + startTime + "--" + (startTime + 2);
        }
        if (this instanceof Tutorial) {
            return module + " T" + id + " @ " + venue + " [" + instructor.toString() + "] " + startTime + "--" + (startTime + 1);
        }
        return "";
    }
    */
}
