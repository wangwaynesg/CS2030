public class Lecture extends Class {
    private final int duration;

    public Lecture(String module, int id, String venue, Instructor instructor, int startTime) {
        super(module, id, venue, instructor, startTime);
        duration = 2;
    }

    public int getDuration() {
        return this.duration;
    }
}
