import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.Optional;
import java.lang.Comparable;

public class Lazy<T extends Comparable<T>> {
    private Optional<T> value;
    private final Supplier<T> supplier;

    private Lazy(T t) {
        this.value = Optional.empty();
        this.supplier = () -> t;
    }

    private Lazy(Supplier<T> s) {
        this.value = Optional.empty();
        this.supplier = s;
    }

    public static <T extends Comparable<T>> Lazy<T> of(T t) {
        return new Lazy<T>(t);
    }

    public static <T extends Comparable<T>> Lazy<T> of(Supplier<T> s) {
        Optional<Supplier<T>> temp = Optional.ofNullable(s);
        return new Lazy<T>(temp.orElseThrow());
    }

    public T get() {
        this.value.ifPresentOrElse(
                x -> {},
                () -> {
                    this.value = Optional.of(supplier.get());
                });
        return this.value.orElseThrow();
    }

    public <U extends Comparable<U>> Lazy<U> map(Function<T, U> function) {
        return Lazy.of((Supplier<U>) () -> function.apply(this.value.orElseGet(this.supplier)));
    }

    public <U extends Comparable<U>> Lazy<U> flatMap(Function<T, Lazy<U>> function) {
        return Lazy.of((Supplier<U>) () -> function.apply(this.value.orElseGet(this.supplier)).get());
    }

    public <U extends Comparable<U>, R extends Comparable<R>> Lazy<R> combine(Lazy<U> lazy, BiFunction<T, U, R> bifunction) {
        return Lazy.of((Supplier<R>) () -> bifunction.apply(
                    this.value.orElseGet(this.supplier),
                    lazy.value.orElseGet(lazy.supplier))
                );
    }

    public Lazy<Boolean> test(Predicate<T> predicate) {
        return Lazy.of((Supplier<Boolean>) () -> predicate.test(this.value.orElseGet(this.supplier)));
    }

    public Lazy<Integer> compareTo(Lazy<T> other) {
        return Lazy.of((Supplier<Integer>) () -> this.get().compareTo(other.get()));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Lazy) {
            Lazy<?> other = (Lazy<?>) object;
            return this.get().equals(other.get());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.value.map(T::toString).orElse("?");
    }
}
