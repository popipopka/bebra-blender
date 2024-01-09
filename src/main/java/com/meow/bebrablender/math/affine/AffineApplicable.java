package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.matrices.Matrix3d;
import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.vectors.Vector3d;

public interface AffineApplicable {
    Matrix4d apply(Matrix4d m);

    Vector3d applyToVector(Vector3d v);
}
