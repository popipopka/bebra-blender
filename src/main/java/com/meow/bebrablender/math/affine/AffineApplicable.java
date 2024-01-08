package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.vectors.Vector3f;

public interface AffineApplicable {
    Vector3f apply(Vector3f v);
}
