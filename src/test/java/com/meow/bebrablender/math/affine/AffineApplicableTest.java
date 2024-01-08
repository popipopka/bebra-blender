package com.meow.bebrablender.math.affine;

import com.meow.bebrablender.math.vectors.Vector3f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AffineApplicableTest {
    @Test
    void rotateX45() {
        Vector3f v = new Vector3f(4, 5, 9);
        Vector3f c = v.copy();

        double a = 45;
        double ra = Math.toRadians(a);

        Vector3f expected = new RotateX(a).apply(v);
        Vector3f actual = new Vector3f(
                c.x(),
                c.y() * Math.cos(ra) + Math.sin(ra) * c.z(),
                Math.cos(ra) * c.z() - Math.sin(ra) * c.y()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateX30() {
        Vector3f v = new Vector3f(1, 6, 2);
        Vector3f c = v.copy();

        double a = 30;
        double ra = Math.toRadians(a);

        Vector3f expected = new RotateX(a).apply(v);
        Vector3f actual = new Vector3f(
                c.x(),
                c.y() * Math.cos(ra) + Math.sin(ra) * c.z(),
                -Math.sin(ra) * c.y() + Math.cos(ra) * c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateX0() {
        Vector3f v = new Vector3f(1, 0, 1);
        Vector3f c = v.copy();

        double a = 0;
        double ra = Math.toRadians(a);


        Vector3f expected = new RotateX(a).apply(v);
        Vector3f actual = new Vector3f(
                c.x(),
                c.y() * Math.cos(ra) + Math.sin(ra) * c.z(),
                -Math.sin(ra) * c.y() + Math.cos(ra) * c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateY45() {
        Vector3f v = new Vector3f(4, 5, 9);
        Vector3f c = v.copy();

        double a = 45;
        double ra = Math.toRadians(a);

        Vector3f expected = new RotateY(a).apply(v);
        Vector3f actual = new Vector3f(
                Math.cos(ra) * c.x() + Math.sin(ra) * c.z(),
                c.y(),
                -Math.sin(ra) * c.x() + Math.cos(ra) * c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateY30() {
        Vector3f v = new Vector3f(1, 6, 2);
        Vector3f c = v.copy();

        double a = 30;
        double ra = Math.toRadians(a);

        Vector3f expected = new RotateY(a).apply(v);
        Vector3f actual = new Vector3f(
                Math.cos(ra) * c.x() + Math.sin(ra) * c.z(),
                c.y(),
                -Math.sin(ra) * c.x() + Math.cos(ra) * c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateY0() {
        Vector3f v = new Vector3f(1, 0, 1);
        Vector3f c = v.copy();

        double a = 0;
        double ra = Math.toRadians(a);

        Vector3f expected = new RotateY(ra).apply(v);
        Vector3f actual = new Vector3f(
                Math.cos(ra) * c.x() + Math.sin(ra) * c.z(),
                c.y(),
                -Math.sin(ra) * c.x() + Math.cos(ra) * c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateZ45() {
        Vector3f v = new Vector3f(4, 5, 9);
        Vector3f c = v.copy();

        double a = 45;
        double ra = Math.toRadians(a);

        Vector3f expected = new RotateZ(ra).apply(v);
        Vector3f actual = new Vector3f(
                c.x() * Math.cos(ra) + Math.sin(ra) * c.y(),
                -Math.sin(ra) * c.x() + Math.cos(ra) * c.y(),
                c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateZ30() {
        Vector3f v = new Vector3f(1, 6, 2);
        Vector3f c = v.copy();

        double a = 30;
        double ra = Math.toRadians(a);

        Vector3f expected = new RotateZ(ra).apply(v);
        Vector3f actual = new Vector3f(
                c.x() * Math.cos(ra) + Math.sin(ra) * c.y(),
                -Math.sin(ra) * c.x() + Math.cos(ra) * c.y(),
                c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void rotateZ0() {
        Vector3f v = new Vector3f(1, 0, 1);
        Vector3f c = v.copy();

        double a = 0;
        double ra = Math.toRadians(a);

        Vector3f expected = new RotateZ(ra).apply(v);
        Vector3f actual = new Vector3f(
                c.x() * Math.cos(ra) + Math.sin(ra) * c.y(),
                -Math.sin(ra) * c.x() + Math.cos(ra) * c.y(),
                c.z()
        );

        Assertions.assertEquals(expected, actual);
    }


    @Test
    void scale() {
        Vector3f v = new Vector3f(2, 6, 8);
        Vector3f c = v.copy();
        
        double sX = 8;
        double sY = 4;
        double sZ = 2;

        Vector3f expected = new Scale(sX, sY, sZ).apply(v);
        Vector3f actual = new Vector3f(
                sX * c.x(),
                sY * c.y(),
                sZ * c.z()
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void translate() {
        Vector3f v = new Vector3f(1, 6, 3);
        Vector3f c = v.copy();
        
        double sX = 2;
        double sY = 10;
        double sZ = 8;

        Vector3f expected = new Scale(sX, sY, sZ).apply(v);
        Vector3f actual = new Vector3f(
                sX + c.x(),
                sY + c.y(),
                sZ + c.z()
        );

        Assertions.assertEquals(expected, actual);
    }
}
