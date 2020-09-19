class Circle {
    private final Point centre;
    private final double radius;

    Circle(Point p, double radius) {
        this.centre = p;
        this.radius = radius;
    }

    boolean contains(Point p) {
        return this.centre.distanceTo(p) < this.radius;
    }

    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

    public String toString() {
        return "Circle at " + this.centre + " with radius " 
            + this.radius + " of area " + String.format("%.2f", this.getArea()) 
            + " and perimeter " + String.format("%.2f", this.getPerimeter());
    }
}
