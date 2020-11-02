class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("point (%.3f, %.3f)", this.x, this.y);
    }

    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }

    public Point midPoint(Point q) {
        return new Point((this.x + q.getX()) / 2, (this.y + q.getY()) / 2);
    }

    public double distanceTo(Point q) {
        return Math.sqrt((q.getX() - this.x) * (q.getX() - this.x) + (q.getY() - this.y) * (q.getY() - this.y));
    }

    public double angleTo(Point q) {
        double dX = q.getX() - this.x;
        double dY = q.getY() - this.y;
        return Math.atan2(dY, dX);
    }

    public Point moveTo(double theta, double d) {
        return new Point(this.x + d * Math.cos(theta), this.y + d * Math.sin(theta));
    }
}
