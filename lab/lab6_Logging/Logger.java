import java.util.function.Function;
import java.util.ArrayList;
import java.util.function.Predicate;

public interface Logger<T> {
    public static <T> Logger<T> make(T value) {
        if (value instanceof LoggerImpl) {
            throw new IllegalArgumentException("already a Logger");
        } else if (value == null) {
            throw new IllegalArgumentException("argument cannot be null");
        } else {
            return new LoggerImpl<T>(value, "Value initialized. Value = " + value.toString());
        }
    }

    public T get();

    public ArrayList<String> getLogs();

    public void printlog();

    public <U> Logger<U> map(Function<? super T, ? extends U> function);

    public <U> Logger<U> flatMap(Function<? super T, ? extends Logger<? extends U>> function);

    public boolean test(Predicate<T> predicate);
}
