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

    public void setX(double x) {
        coords[0] = x;
    }

    public void setY(double y) {
        coords[1] = y;
    }

    public double crossMagnitude(Vector2f v) {
        return coords[0] * v.y() - coords[1] * v.x();
    }

    @Override
    protected Vector2f initialReturnThis() {
        return this;
    }
}
