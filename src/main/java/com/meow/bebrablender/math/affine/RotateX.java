package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.matrices.Matrix3d;
import com.meow.bebrablender.math.vectors.Vector3d;

import static java.lang.Math.*;

public class RotateX implements AffineApplicable {
    public final Matrix3d transformMatrix;

    public RotateX(double angle) {
        double c = cos(toRadians(angle));
        double s = sin(toRadians(angle));

        transformMatrix = new Matrix3d(new double[][]{
                {1, 0, 0},
                {0, c, s},
                {0, -s, c}
        });
    }

    @Override
    public Vector3d apply(Vector3d v) {
        v.setCoords(transformMatrix.mulVec(v).coords());
        return v;
    }
}
