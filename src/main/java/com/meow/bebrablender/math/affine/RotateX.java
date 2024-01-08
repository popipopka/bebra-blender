package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.matrices.Matrix3f;
import com.meow.bebrablender.math.vectors.Vector3f;

import static java.lang.Math.*;

public class RotateX implements AffineApplicable {
    public final Matrix3f transformMatrix;

    public RotateX(double angle) {
        double c = cos(toRadians(angle));
        double s = sin(toRadians(angle));

        transformMatrix = new Matrix3f(new double[][]{
                {1, 0, 0},
                {0, c, s},
                {0, -s, c}
        });
    }

    @Override
    public Vector3f apply(Vector3f v) {
        v.setCoords(transformMatrix.multiplyVector(v).getCoords());
        return v;
    }
}
