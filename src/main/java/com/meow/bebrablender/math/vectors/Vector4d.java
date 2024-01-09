package com.meow.bebrablender.math.vectors;

import java.util.Arrays;

public class Vector4d extends AbstractVector<Vector4d> {
    public Vector4d() {
        super(4);
    }

    public Vector4d(double x, double y, double z, double w) {
        super(4, new double[]{x, y, z, w});
    }

    public Vector4d(double[] coords) {
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
    public Vector4d copy() {
        return new Vector4d(Arrays.copyOf(this.coords, size));
    }

    @Override
    protected Vector4d initialReturnThis() {
        return this;
    }
}
