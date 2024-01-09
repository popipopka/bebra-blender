package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.MathUtils;
import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.vectors.Vector3d;

public class Translate implements AffineApplicable {
    public final Matrix4d transformMatrix;

    public Translate(double tX, double tY, double tZ) {
        this.transformMatrix = new Matrix4d(new double[][]{
                {1, 0, 0, tX},
                {0, 1, 0, tY},
                {0, 0, 1, tZ},
                {0, 0, 0, 1}
        });
    }

    @Override
    public Matrix4d apply(Matrix4d m) {
        return transformMatrix.mul(m);
    }

    @Override
    public Vector3d applyToVector(Vector3d v) {
        Vector3d mulV = MathUtils.mulMatrix4ByVector3(transformMatrix, v);
        v.setCoords(mulV.coords());
        return v;
    }
}
