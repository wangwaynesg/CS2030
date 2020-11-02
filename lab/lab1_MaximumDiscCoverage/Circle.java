class Circle {
    private Point centre;
    private double radius;

    public Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "circle of radius " + String.format("%.1f", this.radius) + " centered at " + this.centre.toString();
    }

    public boolean containsPoint(Point p) {
        if (this.centre.distanceTo(p) <= 1) {
            return true;
        } else {
            return false;
        }
    }
}
