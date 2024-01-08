package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.matrices.Matrix3f;
import com.meow.bebrablender.math.vectors.Vector3f;

import static java.lang.Math.*;
import static java.lang.Math.toRadians;

public class RotateZ implements AffineApplicable {
    public final Matrix3f transformMatrix;

    public RotateZ(double angle) {
        double c = cos(toRadians(angle));
        double s = sin(toRadians(angle));

        transformMatrix = new Matrix3f(new double[][]{
                {c, s, 0},
                {-s, c, 0},
                {0, 0, 1}
        });
    }

    @Override
    public Vector3f apply(Vector3f v) {
        v.setCoords(transformMatrix.multiplyVector(v).getCoords());
        return v;
    }
}
