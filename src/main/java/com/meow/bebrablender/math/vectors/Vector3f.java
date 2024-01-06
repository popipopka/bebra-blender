package com.meow.bebrablender.math.vectors;

public class Vector3f extends AbstractVector<Vector3f> {
    public Vector3f(double[] coords) {
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

    public Vector3f product(Vector3f v) {
        double x = this.y() * v.z() - this.z() * v.y();
        double y = this.z() * v.x() - this.x() * v.z();
        double z = this.x() * v.y() - this.y() * v.x();

        return new Vector3f(new double[]{x, y, z});
    }
}
