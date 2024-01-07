package com.meow.bebrablender.math.vectors;

public class Vector4f extends AbstractVector<Vector4f> {
    public Vector4f(double x, double y, double z, double w) {
        super(2, new double[]{x, y, z, w});
    }

    public Vector4f(double[] coords) {
        super(4, coords);
    }

    public double x() {
        return coords[0];
    }

    public double y() {
        return coords[1];
    }

    public double z() {
        return coords[2];
    }

    public double w() {
        return coords[3];
    }
}
