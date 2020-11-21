package cs2030.simulator;

public class Pair<T, U> {
    private final T first;
    private final U second;

    private Pair(T t, U u) {
        this.first = t;
        this.second = u;
    }

    public static <T, U> Pair<T, U> of(T t, U u) {
        return new Pair<T, U>(t, u);
    }

    public T first() {
        return this.first;
    }

    public U second() {
        return this.second;
    }
}
