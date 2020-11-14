import java.util.HashMap;

public class Troll extends Thing {
    public Troll() {
        super();
        super.states.put(1, "Troll lurks in the shadows.");
        super.states.put(2, "Troll is getting hungry.");
        super.states.put(3, "Troll is VERY hungry.");
        super.states.put(4, "Troll is SUPER HUNGRY and is about to ATTACK!");
        super.states.put(5, "Troll attacks!");
    }

    public Troll(HashMap<Integer, String> states, int currentState) {
        super(states, currentState);
    }

    public Thing nextState() {
        if (super.states.containsKey(super.currentState + 1)) {
            return new Troll(super.states, super.currentState + 1);
        } else {
            return new Troll(super.states, super.currentState);
        }
    }
}
