package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.vectors.Vector3d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AffineVectorTest {
    @Test
    void rotateX45() {
        Vector3d v = new Vector3d(4, 5, 9);
        Vector3d c = v.copy();

        double a = 45;
        double ra = Math.toRadians(a);

        Vector3d expected = new RotateX(a).applyToVector(v);
        Vector3d actual = new Vector3d(
                c.x(),
                c.y() * Math.cos(ra) + Math.sin(ra) * c.z(),
                Math.cos(ra) * c.z() - Math.sin(ra) * c.y()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateX30() {
        Vector3d v = new Vector3d(1, 6, 2);
        Vector3d c = v.copy();

        double a = 30;
        double ra = Math.toRadians(a);

        Vector3d expected = new RotateX(a).applyToVector(v);
        Vector3d actual = new Vector3d(
                c.x(),
                c.y() * Math.cos(ra) + Math.sin(ra) * c.z(),
                -Math.sin(ra) * c.y() + Math.cos(ra) * c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateX0() {
        Vector3d v = new Vector3d(1, 0, 1);
        Vector3d c = v.copy();

        double a = 0;
        double ra = Math.toRadians(a);


        Vector3d expected = new RotateX(a).applyToVector(v);
        Vector3d actual = new Vector3d(
                c.x(),
                c.y() * Math.cos(ra) + Math.sin(ra) * c.z(),
                -Math.sin(ra) * c.y() + Math.cos(ra) * c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateY45() {
        Vector3d v = new Vector3d(4, 5, 9);
        Vector3d c = v.copy();

        double a = 45;
        double ra = Math.toRadians(a);

        Vector3d expected = new RotateY(a).applyToVector(v);
        Vector3d actual = new Vector3d(
                Math.cos(ra) * c.x() + Math.sin(ra) * c.z(),
                c.y(),
                -Math.sin(ra) * c.x() + Math.cos(ra) * c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateY30() {
        Vector3d v = new Vector3d(1, 6, 2);
        Vector3d c = v.copy();

        double a = 30;
        double ra = Math.toRadians(a);

        Vector3d expected = new RotateY(a).applyToVector(v);
        Vector3d actual = new Vector3d(
                Math.cos(ra) * c.x() + Math.sin(ra) * c.z(),
                c.y(),
                -Math.sin(ra) * c.x() + Math.cos(ra) * c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateY0() {
        Vector3d v = new Vector3d(1, 0, 1);
        Vector3d c = v.copy();

        double a = 0;
        double ra = Math.toRadians(a);

        Vector3d expected = new RotateY(ra).applyToVector(v);
        Vector3d actual = new Vector3d(
                Math.cos(ra) * c.x() + Math.sin(ra) * c.z(),
                c.y(),
                -Math.sin(ra) * c.x() + Math.cos(ra) * c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateZ45() {
        Vector3d v = new Vector3d(4, 5, 9);
        Vector3d c = v.copy();

        double a = 45;
        double ra = Math.toRadians(a);

        Vector3d expected = new RotateZ(ra).applyToVector(v);
        Vector3d actual = new Vector3d(
                c.x() * Math.cos(ra) + Math.sin(ra) * c.y(),
                -Math.sin(ra) * c.x() + Math.cos(ra) * c.y(),
                c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateZ30() {
        Vector3d v = new Vector3d(1, 6, 2);
        Vector3d c = v.copy();

        double a = 30;
        double ra = Math.toRadians(a);

        Vector3d expected = new RotateZ(ra).applyToVector(v);
        Vector3d actual = new Vector3d(
                c.x() * Math.cos(ra) + Math.sin(ra) * c.y(),
                -Math.sin(ra) * c.x() + Math.cos(ra) * c.y(),
                c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateZ0() {
        Vector3d v = new Vector3d(1, 0, 1);
        Vector3d c = v.copy();

        double a = 0;
        double ra = Math.toRadians(a);

        Vector3d expected = new RotateZ(ra).applyToVector(v);
        Vector3d actual = new Vector3d(
                c.x() * Math.cos(ra) + Math.sin(ra) * c.y(),
                -Math.sin(ra) * c.x() + Math.cos(ra) * c.y(),
                c.z()
        );

        Assertions.assertEquals(expected, actual);
    }


    @Test
    void scale() {
        Vector3d v = new Vector3d(2, 6, 8);
        Vector3d c = v.copy();

        double sX = 8;
        double sY = 4;
        double sZ = 2;

        Vector3d expected = new Scale(sX, sY, sZ).applyToVector(v);
        Vector3d actual = new Vector3d(
                sX * c.x(),
                sY * c.y(),
                sZ * c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void translate() {
        Vector3d v = new Vector3d(1, 6, 3);
        Vector3d c = v.copy();

        double sX = 2;
        double sY = 10;
        double sZ = 8;

        Vector3d expected = new Scale(sX, sY, sZ).applyToVector(v);
        Vector3d actual = new Vector3d(
                sX + c.x(),
                sY + c.y(),
                sZ + c.z()
        );

        Assertions.assertEquals(expected, actual);
    }
}
