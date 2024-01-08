package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.vectors.Vector3d;
import com.meow.bebrablender.math.vectors.Vector4d;
import com.meow.bebrablender.math.vectors.VectorsUtils;

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
    public Vector3d apply(Vector3d v) {
        Vector4d v4f = VectorsUtils.v3fToV4f(v);

        v4f = transformMatrix.mulVec(v4f);

        Vector3d v3f = VectorsUtils.v4fToV3f(v4f);
        v.setCoords(v3f.coords());

        return v;
    }
}
