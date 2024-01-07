package com.meow.bebrablender.math.vectors;

public class Vector2f extends AbstractVector<Vector2f> {

    public Vector2f(double x, double y) {
        super(2, new double[]{x, y});
    }

    public Vector2f(double[] coords) {
        super(2, coords);
    }

    public double x() {
        return coords[0];
    }

    public double y() {
        return coords[1];
    }
}
