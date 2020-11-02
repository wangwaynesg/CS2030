import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;

public class LoggerImpl<T> implements Logger<T> {
    private final T value;
    private final ArrayList<String> logs;

    private LoggerImpl(T value) {
        this.value = value;
        this.logs = new ArrayList<>();
    }

    public LoggerImpl(T value, String log) {
        this.value = value;
        this.logs = new ArrayList<>();
        this.logs.add(log);
    }

    public LoggerImpl(T value, String log, ArrayList<String> logs) {
        this.value = value;
        this.logs = new ArrayList<>();
        for (String string : logs) {
            this.logs.add(string);
        }
        this.logs.add(log);
    }

    public LoggerImpl(T value, ArrayList<String> logs) {
        this.value = value;
        this.logs = new ArrayList<>();
        for (String string : logs) {
            this.logs.add(string);
        }
    }

    public T get() {
        return this.value;
    }

    public ArrayList<String> getLogs() {
        return this.logs;
    }

    public void printlog() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
    
    public <U> Logger<U> map(Function<? super T, ? extends U> function) {
        U newValue = function.apply(this.value);
        String log;
        if (newValue.equals(this.value)) {
            log = "Value unchanged. Value = " + newValue.toString();
        } else {
            log = "Value changed! New value = " + newValue.toString();
        }
        return new LoggerImpl<U>(newValue, log, this.logs);
    }

    public <U> Logger<U> flatMap(Function<? super T, ? extends Logger<? extends U>> function) {
        Logger<? extends U> tempLogger = function.apply(this.value);
        U newValue = tempLogger.get();
        String log;
        if (newValue.equals(this.value)) {
            log = "Value unchanged. Value = " + newValue.toString();
        } else {
            log = "Value changed! New value = " + newValue.toString();
        }
        
        ArrayList<String> temp = new ArrayList<>();
        for (String string : this.logs) {
            temp.add(string);
        }
        for (int i = 1; i < tempLogger.getLogs().size(); i++) {
            temp.add(tempLogger.getLogs().get(i));
        }
        
        return new LoggerImpl<U>(newValue, temp);
    }

    public boolean test(Predicate<T> predicate) {
        return predicate.test(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof LoggerImpl) {
            LoggerImpl<?> other = (LoggerImpl<?>) obj;
            return this.value.equals(other.value) && this.logs.equals(other.logs);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Logger[" + value + "]";
    }
}
