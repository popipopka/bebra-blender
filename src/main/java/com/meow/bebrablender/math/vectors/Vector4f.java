package com.meow.bebrablender.math.vectors;

public class Vector4f extends AbstractVector<Vector4f> {
    public Vector4f() {
        super(0, new double[]{0, 0, 0, 0});
    }

    public Vector4f(double x, double y, double z, double w) {
        super(4, new double[]{x, y, z, w});
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

    public void setX(double x) {
        coords[0] = x;
    }

    public void setY(double y) {
        coords[1] = y;
    }

    public void setZ(double z) {
        coords[2] = z;
    }

    public void setW(double w) {
        coords[3] = w;
    }

    @Override
    protected Vector4f initialReturnThis() {
        return this;
    }
}
