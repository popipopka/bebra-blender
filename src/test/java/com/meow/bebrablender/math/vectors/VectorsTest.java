package com.meow.bebrablender.math.vectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class VectorsTest {
    @Test
    void add() {
        Vector3d v1 = new Vector3d(1, 2, 3);
        Vector3d v2 = new Vector3d(-7, 4, 0);

        v1.add(v2);

        double[] expected = v1.coords();
        double[] actual = {-6, 6, 3};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void addNew() {
        Vector3d v1 = new Vector3d(1, 2, 3);
        Vector3d v2 = new Vector3d(-7, 4, 0);

        Vector3d expected = new Vector3d();
        expected.add(v1, v2);

        Vector3d actual = new Vector3d(new double[]{-6, 6, 3});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sub() {
        Vector3d v1 = new Vector3d(1, 2, 0);
        Vector3d v2 = new Vector3d(-7, 2, 3);

        v1.sub(v2);

        double[] expected = v1.coords();
        double[] actual = {8, 0, -3};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void subNew() {
        Vector3d v1 = new Vector3d(1, 2, 0);
        Vector3d v2 = new Vector3d(-7, 2, 3);

        Vector3d expected = new Vector3d();
        expected.sub(v1, v2);

        Vector3d actual = new Vector3d(new double[]{-6, 6, 3});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void mul() {
        Vector3d v1 = new Vector3d(1, -2, 0);

        v1.mul(6);

        double[] expected = v1.coords();
        double[] actual = {6, -12, 0};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void mulNew() {
        Vector3d v1 = new Vector3d(1, -2, 0);

        Vector3d expected = new Vector3d();
        expected.mul(v1, 6);

        Vector3d actual = new Vector3d(new double[]{6, -12, 0});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void div() {
        Vector3d v1 = new Vector3d(8, -3, 0);

        v1.div(2);

        double[] expected = v1.coords();
        double[] actual = {4, -1.5, 0};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void divNew() {
        Vector3d v1 = new Vector3d(8, -3, 0);

        Vector3d expected = new Vector3d();
        expected.mul(v1, 2);

        Vector3d actual = new Vector3d(new double[]{4, -1.5, 0});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void divZero() {
        Vector3d v1 = new Vector3d(1, -3, 0);

        Assertions.assertThrows(ArithmeticException.class, () -> v1.div(0));
    }

    @Test
    void len() {
        Vector3d v1 = new Vector3d(1, 6, 3);

        double expected = v1.len();
        double actual = Math.sqrt(46);

        Assertions.assertEquals(0, Double.compare(expected, actual));
    }

    @Test
    void norm() {
        Vector2d expected = new Vector2d(1, 2);
        expected.norm();

        double x = 1 / Math.sqrt(5);
        double y = 2 / Math.sqrt(5);
        Vector2d actual = new Vector2d(x, y);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void normZeroVector() {
        Vector4d expected = new Vector4d(0, 0, 0, 0);
        expected.norm();

        Vector4d actual = new Vector4d(0, 0, 0, 0);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void crossProd() {
        Vector3d v1 = new Vector3d(1, 2, 3);
        Vector3d v2 = new Vector3d(4, 5, 6);


        Vector3d expected = v1.crossProd(v2);
        Vector3d actual = new Vector3d(-3, 6, -3);


        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sclProd() {
        Vector3d v1 = new Vector3d(-7, 2, 0);
        Vector3d v2 = new Vector3d(9, 1, 3);

        double expected = v1.sclProd(v2);
        double actual = -61;


        Assertions.assertEquals(0, Double.compare(expected, actual));
    }

    @Test
    void isOrt() {
        Vector3d v1 = new Vector3d(0, 0, 1);
        Vector3d v2 = new Vector3d(0, 1, 0);

        boolean expected = v1.isOrt(v2);

        Assertions.assertTrue(expected);
    }

    @Test
    void to() {
        Vector3d v1 = new Vector3d(15, 3, 3);
        Vector3d v2 = new Vector3d(7, 9, 3);

        Vector3d expected = v1.to(v2);
        Vector3d actual = new Vector3d(-8, 6, 0);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void crossMagnitude() {
        Vector2d v1 = new Vector2d(-2, 0);
        Vector2d v2 = new Vector2d(4, 5);

        double expected = v1.crossMagnitude(v2);
        double actual = -10;

        Assertions.assertEquals(0, Double.compare(expected, actual));
    }
}
