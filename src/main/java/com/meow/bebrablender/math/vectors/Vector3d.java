package com.meow.bebrablender.math.vectors;

public class Vector3d extends AbstractVector<Vector3d> {
    public Vector3d() {
        super(3, new double[]{0, 0, 0});
    }

    public Vector3d(double x, double y, double z) {
        super(3, new double[]{x, y, z});
    }

    public Vector3d(double[] coords) {
        super(3, coords);
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

    public void setX(double x) {
        coords[0] = x;
    }

    public void setY(double y) {
        coords[1] = y;
    }

    public void setZ(double z) {
        coords[2] = z;
    }

    public Vector3d crossProd(Vector3d v) {
        double x = this.y() * v.z() - this.z() * v.y();
        double y = this.z() * v.x() - this.x() * v.z();
        double z = this.x() * v.y() - this.y() * v.x();

        return new Vector3d(x, y, z);
    }

    @Override
    public Vector3d copy() {
        return new Vector3d(this.coords);
    }

    @Override
    protected Vector3d initialReturnThis() {
        return this;
    }
}
