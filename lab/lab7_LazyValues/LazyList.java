import java.util.List;
:q
:wq!
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.lang.Comparable;
import java.util.stream.IntStream;
import java.util.function.Supplier;

public class LazyList<T extends Comparable<T>> {
    private final List<Lazy<T>> list;

    private LazyList(List<Lazy<T>> list) {
        this.list = list;
    }

    public static <T extends Comparable<T>> LazyList<T> generate(int n, T seed, UnaryOperator<T> f) {
        return new LazyList<T>(
                Stream.iterate(seed, f)
                .map(Lazy::of)
                .limit(n)
                .collect(Collectors.toList())
                );
    }

    private static <T> Supplier<T> supply(UnaryOperator<T> f, T t) {
        return () -> f.apply(t);
    }

    public T get(int i) {
        return this.list.get(i).get();
    }
}
