import java.util.HashMap;

public class Candle extends Thing {
    /*
    private final HashMap<Integer, String> states;
    private final Integer currentState;
    */

    public Candle() {
        super();
        super.states.put(1, "Candle flickers.");
        super.states.put(2, "Candle is getting shorter.");
        super.states.put(3, "Candle is about to burn out.");
        super.states.put(4, "Candle has burned out.");
    }

    public Candle(HashMap<Integer, String> states, int currentState) {
        super(states, currentState);
    }

    public Thing nextState() {
        if (super.states.containsKey(super.currentState + 1)) {
            return new Candle(super.states, super.currentState + 1);
        } else {
            return new Candle(super.states, super.currentState);
        }
    }
}
