public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return super.speak() + ": Woof";
    }
}
