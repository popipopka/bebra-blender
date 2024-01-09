package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.matrices.Matrix4d;
import com.meow.bebrablender.math.vectors.Vector3d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AffineMatricesTest {
    @Test
    void scale() {
        AffineTransform a = new AffineTransform();
        Vector3d v = new Vector3d(2, 3, 4);

        Matrix4d expected = a.scale(v);
        Matrix4d actual = new Matrix4d(new double[][]{
                {2, 0, 0, 0},
                {0, 3, 0, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 1}
        });
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void translate() {
        AffineTransform a = new AffineTransform();
        Vector3d v = new Vector3d(5, 5, 5);

        Matrix4d expected = a.translate(v);
        Matrix4d actual = new Matrix4d(new double[][]{
                {1, 0, 0, 5},
                {0, 1, 0, 5},
                {0, 0, 1, 5},
                {0, 0, 0, 1}
        });
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateX() {
        AffineTransform affine = new AffineTransform();
        double a = 45;
        double ra = Math.toRadians(a);
        Vector3d v = new Vector3d(a, 0, 0);

        Matrix4d expected = affine.rotate(v);
        Matrix4d actual = new Matrix4d(new double[][]{
                {1, 0, 0, 0},
                {0, Math.cos(ra), Math.sin(ra), 0},
                {0, -Math.sin(ra), Math.cos(ra), 0},
                {0, 0, 0, 1}
        });
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateY() {
        AffineTransform affine = new AffineTransform();
        double a = 45;
        double ra = Math.toRadians(a);
        Vector3d v = new Vector3d(0, a, 0);

        Matrix4d expected = affine.rotate(v);
        Matrix4d actual = new Matrix4d(new double[][]{
                {Math.cos(ra), 0, Math.sin(ra), 0},
                {0, 1, 0, 0},
                {-Math.sin(ra), 0, Math.cos(ra), 0},
                {0, 0, 0, 1}
        });
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateZ() {
        AffineTransform affine = new AffineTransform();
        double a = 45;
        double ra = Math.toRadians(a);
        Vector3d v = new Vector3d(0, 0, a);

        Matrix4d expected = affine.rotate(v);
        Matrix4d actual = new Matrix4d(new double[][]{
                {Math.cos(ra), Math.sin(ra), 0, 0},
                {-Math.sin(ra), Math.cos(ra), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });
        Assertions.assertEquals(expected, actual);
    }
}