import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.lang.Comparable;
import java.util.stream.IntStream;
import java.util.function.Supplier;
import java.util.Optional;

public class LazyList<T extends Comparable<T>> {
    private final List<Lazy<T>> list;

    private LazyList(List<Lazy<T>> list) {
        this.list = list;
    }

    public static <T extends Comparable<T>> LazyList<T> generate(int n, T seed, UnaryOperator<T> f) {
        return new LazyList<T>(
                Stream.iterate(Lazy.of(seed), x -> x.map(f))
                .limit(n)
                .collect(Collectors.toList())
                );
    }

    private static <T> Supplier<T> supply(UnaryOperator<T> f, T t) {
        return () -> f.apply(t);
    }

    public T get(int i) {
        return IntStream.rangeClosed(0, i)
            .mapToObj(x -> list.get(x).get())
            .reduce((first, second) -> second)
            .orElseThrow();
    }

    public int indexOf(T t) {
        return IntStream.range(0, list.size())
            .filter(x -> list.get(x).get().equals(t))
            .findFirst()
            .orElse(-1);
    }
}
