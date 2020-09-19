class Point {
    private double x;
    private double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double distanceTo(Point q) {
        double dx = this.x - q.x;
        double dy = this.y - q.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

   public String toString() {
        return "(" + this.x + ", " + this.y + ")"; //(1.00, 2.00)
    }

   @Override
   public boolean equals(Object obj) {
       if (this == obj) {
           return true;
       } else if (obj instanceof Point) {
           Point p = (Point) obj;
           return p.x == this.x && p.y == this.y;   
       } else {
           return false;
       }
   }
}
