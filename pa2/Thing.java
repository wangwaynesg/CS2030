import java.util.HashMap;

public abstract class Thing {
    protected final HashMap<Integer, String> states;
    protected final int currentState;

    public Thing() {
        this.states = new HashMap<>();
        this.currentState = 1;
    }

    public Thing(HashMap<Integer, String> states, Integer currentState) {
        this.states = new HashMap<>(states);
        this.currentState = currentState;
    }

    public abstract Thing nextState();

    @Override
    public String toString() {
        return this.states.get(this.currentState);
    }
}
