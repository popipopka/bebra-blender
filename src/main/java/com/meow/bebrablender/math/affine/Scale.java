package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.matrices.Matrix3f;
import com.meow.bebrablender.math.vectors.Vector3f;

public class Scale implements AffineApplicable {
    public final Matrix3f transformMatrix;

    public Scale(double sX, double sY, double sZ) {
        this.transformMatrix = new Matrix3f(new double[][]{
                {sX, 0, 0},
                {0, sY, 0},
                {0, 0, sZ}
        });
    }

    @Override
    public Vector3f apply(Vector3f v) {
        v.setCoords(transformMatrix.multiplyVector(v).getCoords());
        return v;
    }
}
