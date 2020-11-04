import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.lang.Comparable;

public class LazyList<T extends Comparable<T>> {
    private final List<Lazy<T>> list;

    private LazyList(List<Lazy<T>> list) {
        this.list = list;
    }

    public static <T extends Comparable<T>> LazyList<T> generate(int n, T seed, UnaryOperator<T> f) {
        return new LazyList<T>(
                Stream.iterate(seed, x -> f.apply(x))
                .limit(n)
                .map(Lazy::of)
                .collect(Collectors.toList())
                );
    }

    public T get(int i) {
        return this.list.get(i).get();
    }
}
