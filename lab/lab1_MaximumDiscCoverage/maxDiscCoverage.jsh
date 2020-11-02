Circle createUnitCircle(Point p, Point q) {
    Point m = p.midPoint(q);
    double theta = p.angleTo(q);
    double d = Math.sqrt(1 - p.distanceTo(m) * q.distanceTo(m));
    Point c = m.moveTo((theta + (Math.PI / 2)), d);
    return new Circle(c, 1);
}

int findMaxDiscCoverage(Point[] points) {
    int maxDiscCoverage = 0;
    int currentDiscCoverage;

    for (int i = 0; i < points.length - 1; i++) {
        for (int j = i + 1; j < points.length; j++) {
            Circle c = createUnitCircle(points[i], points[j]);
            currentDiscCoverage = 0;

            for (int k = 0; k < points.length; k++) {
                if (c.containsPoint(points[k])) {
                    currentDiscCoverage++;
                }
            }
            if (currentDiscCoverage > maxDiscCoverage) {
                maxDiscCoverage = currentDiscCoverage;
            }
        }
    }
    return maxDiscCoverage;
}
