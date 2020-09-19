class Vector2D {
    //private double x;
    //private double y;
    private double[] coord2D = new double[2];

    Vector2D(double x, double y) {
        this.coord2D[0] = x;
        this.coord2D[1] = y;
    }

    void add(Vector2D v) {
        this.coord2D[0] = this.coord2D[0] + v.coord2D[0];
        this.coord2D[1] = this.coord2D[1] + v.coord2D[1];
        // line A
    }

    @Override
    public String toString() {
        return("x = " + this.coord2D[0] + " y = " + this.coord2D[1]);
    }
}
