package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.MathUtils;
import com.meow.bebrablender.math.matrices.Matrix3d;
import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.vectors.Vector3d;

public class Scale implements AffineApplicable {
    public final Matrix4d transformMatrix;

    public Scale(double sX, double sY, double sZ) {
        this.transformMatrix = new Matrix4d(new double[][]{
                {sX, 0, 0, 0},
                {0, sY, 0, 0},
                {0, 0, sZ, 0},
                {0, 0, 0, 1}
        });
    }

    @Override
    public Matrix4d apply(Matrix4d m) {
        transformMatrix.setData(m.mul(transformMatrix).data());
        return transformMatrix;
    }

    @Override
    public Vector3d applyToVector(Vector3d v) {
        Vector3d mulV = MathUtils.mulMatrix4ByVector3(transformMatrix, v);
        v.setCoords(mulV.coords());
        return v;
    }
}
