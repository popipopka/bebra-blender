package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.vectors.Vector3d;

public interface AffineApplicable {
    Vector3d apply(Vector3d v);
}
