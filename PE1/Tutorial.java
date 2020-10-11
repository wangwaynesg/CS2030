public class Tutorial extends Class {
    private final int duration;

    public Tutorial(String module, int id, String venue, Instructor instructor, int startTime) {
        super(module, id, venue, instructor, startTime);
        duration = 1;
    }

    public int getDuration() {
        return this.duration;
    }
}
