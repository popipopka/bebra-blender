package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.matrices.Matrix3d;
import com.meow.bebrablender.math.vectors.Vector3d;

public class Scale implements AffineApplicable {
    public final Matrix3d transformMatrix;

    public Scale(double sX, double sY, double sZ) {
        this.transformMatrix = new Matrix3d(new double[][]{
                {sX, 0, 0},
                {0, sY, 0},
                {0, 0, sZ}
        });
    }

    @Override
    public Vector3d apply(Vector3d v) {
        v.setCoords(transformMatrix.mulVec(v).coords());
        return v;
    }
}
