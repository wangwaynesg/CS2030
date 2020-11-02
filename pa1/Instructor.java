public class Instructor {
    private final String name;

    public Instructor(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Instructor) {
            Instructor i = (Instructor) obj;
            return this.name.equals(i.getName());
        } else {
            return false;
        }
    }
}
