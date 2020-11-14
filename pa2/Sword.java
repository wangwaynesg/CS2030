import java.util.HashMap;

public class Sword extends Thing {
    private final boolean taken;

    public Sword() {
        super();
        this.taken = false;
        super.states.put(1, "Sword is shimmering.");
    }

    public Sword(HashMap<Integer, String> states, int currentState, boolean taken) {
        super(states, currentState);
        this.taken = taken;
    }

    public Sword takeSword() {
        return new Sword(super.states, super.currentState, true);
    }

    public Sword dropSword() {
        return new Sword(super.states, super.currentState, false);
    }

    public boolean getTaken() {
        return this.taken;
    }

    public Thing nextState() {
        return new Sword(super.states, super.currentState, this.taken);
    }
}
