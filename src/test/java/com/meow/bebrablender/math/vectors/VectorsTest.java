package com.meow.bebrablender.math.vectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VectorsTest {
    @Test
    void add() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(-7, 4, 0);

        v1.add(v2);

        double[] expected = v1.getCoords();
        double[] actual = {-6, 6, 3};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void subtract() {
        Vector3f v1 = new Vector3f(1, 2, 0);
        Vector3f v2 = new Vector3f(-7, 2, 3);

        v1.subtract(v2);

        double[] expected = v1.getCoords();
        double[] actual = {8, 0, -3};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void multiply() {
        Vector3f v1 = new Vector3f(1, -2, 0);

        v1.multiply(6);

        double[] expected = v1.getCoords();
        double[] actual = {6, -12, 0};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void divide() {
        Vector3f v1 = new Vector3f(8, -3, 0);

        v1.divide(2);

        double[] expected = v1.getCoords();
        double[] actual = {4, -1.5f, 0};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void divideZero() {
        Vector3f v1 = new Vector3f(1, -3, 0);

        Assertions.assertThrows(ArithmeticException.class, () -> v1.divide(0));
    }

    @Test
    void length() {
        Vector3f v1 = new Vector3f(1, 6, 3);

        double expected = v1.length();
        double actual = Math.sqrt(46);

        Assertions.assertEquals(0, Double.compare(expected, actual));
    }

    @Test
    void normalize() {
        Vector2f expected = new Vector2f(1, 2);
        expected.normalize();

        double x = 1 / Math.sqrt(5);
        double y = 2 / Math.sqrt(5);
        Vector2f actual = new Vector2f(x, y);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void normalizeZeroVector() {
        Vector4f expected = new Vector4f(0, 0, 0, 0);
        expected.normalize();

        Vector4f actual = new Vector4f(0, 0, 0, 0);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void product() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(4, 5, 6);


        Vector3f expected = v1.product(v2);
        Vector3f actual = new Vector3f(-3, 6, -3);


        Assertions.assertEquals(expected, actual);
    }

    @Test
    void scalarProduct() {
        Vector3f v1 = new Vector3f(-7, 2, 0);
        Vector3f v2 = new Vector3f(9, 1, 3);

        double expected = v1.scalarProduct(v2);
        double actual = -61;


        Assertions.assertEquals(0, Double.compare(expected, actual));
    }
}
