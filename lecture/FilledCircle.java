import java.awt.Color;

class FilledCircle extends Circle {
    private final Color color;

    FilledCircle(Point p, double radius, Color color) {
        super(p, radius);
        this.color = color;
    }

    Color getColor() {
        return this.color;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", " + this.getColor();
    }
}
