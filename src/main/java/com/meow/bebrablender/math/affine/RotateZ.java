package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.matrices.Matrix3d;
import com.meow.bebrablender.math.vectors.Vector3d;

import static java.lang.Math.*;
import static java.lang.Math.toRadians;

public class RotateZ implements AffineApplicable {
    public final Matrix3d transformMatrix;

    public RotateZ(double angle) {
        double c = cos(toRadians(angle));
        double s = sin(toRadians(angle));

        transformMatrix = new Matrix3d(new double[][]{
                {c, s, 0},
                {-s, c, 0},
                {0, 0, 1}
        });
    }

    @Override
    public Vector3d apply(Vector3d v) {
        v.setCoords(transformMatrix.mulVec(v).coords());
        return v;
    }
}