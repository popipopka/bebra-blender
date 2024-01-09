package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.MathUtils;
import com.meow.bebrablender.math.matrices.Matrix3d;
import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.vectors.Vector3d;

import static java.lang.Math.*;
import static java.lang.Math.toRadians;

public class RotateZ implements AffineApplicable {
    public final Matrix4d transformMatrix;

    public RotateZ(double angle) {
        double c = cos(toRadians(angle));
        double s = sin(toRadians(angle));

        transformMatrix = new Matrix4d(new double[][]{
                {c, s, 0, 0},
                {-s, c, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });
    }

    @Override
    public Matrix4d apply(Matrix4d m) {
        return m.mul(transformMatrix);
    }

    @Override
    public Vector3d applyToVector(Vector3d v) {
        Vector3d mulV = MathUtils.mulMatrix4ByVector3(transformMatrix, v);
        v.setCoords(mulV.coords());
        return v;
    }
}
