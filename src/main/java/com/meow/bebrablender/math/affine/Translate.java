package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.matrices.Matrix4f;
import com.meow.bebrablender.math.vectors.Vector3f;
import com.meow.bebrablender.math.vectors.Vector4f;
import com.meow.bebrablender.math.vectors.VectorsUtils;

public class Translate implements AffineApplicable {
    public final Matrix4f transformMatrix;

    public Translate(double tX, double tY, double tZ) {
        this.transformMatrix = new Matrix4f(new double[][]{
                {1, 0, 0, tX},
                {0, 1, 0, tY},
                {0, 0, 1, tZ},
                {0, 0, 0, 1}
        });
    }

    @Override
    public Vector3f apply(Vector3f v) {
        Vector4f v4f = VectorsUtils.v3fToV4f(v);

        v4f = transformMatrix.multiplyVector(v4f);

        Vector3f v3f = VectorsUtils.v4fToV3f(v4f);
        v.setCoords(v3f.getCoords());

        return v;
    }
}
