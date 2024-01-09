package com.meow.bebrablender.math;

import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.vectors.Vector3d;

public class MathUtils {
    private MathUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Vector3d mulMatrix4ByVector3(final Matrix4d matrix, final Vector3d vertex) {
        double[][] matrixData = matrix.data();

        final double x = (vertex.x() * matrixData[0][0]) + (vertex.y() * matrixData[1][0]) + (vertex.z() * matrixData[2][0]) + matrixData[3][0];
        final double y = (vertex.x() * matrixData[0][1]) + (vertex.y() * matrixData[1][1]) + (vertex.z() * matrixData[2][1]) + matrixData[3][1];
        final double z = (vertex.x() * matrixData[0][2]) + (vertex.y() * matrixData[1][2]) + (vertex.z() * matrixData[2][2]) + matrixData[3][2];

        return new Vector3d(x, y, z);
    }
}
