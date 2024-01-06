package com.meow.bebrablender.math.matrices;

import com.meow.bebrablender.math.matrices.Matrix3f;
import com.meow.bebrablender.math.vectors.Vector3f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatricesTest {
    @Test
    void setNull() {
        Matrix3f expected = new Matrix3f(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        expected.setNull();

        Matrix3f actual = new Matrix3f(new double[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setUnit() {
        Matrix3f expected = new Matrix3f(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        expected.setUnit();

        Matrix3f actual = new Matrix3f(new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void add() {
        Matrix3f m1 = new Matrix3f(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});

        Matrix3f m2 = new Matrix3f(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});

        Matrix3f expected = m1.add(m2);
        Matrix3f actual = new Matrix3f(new double[][]{
                {2, 4, 6},
                {8, 10, 12},
                {14, 16, 18}});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void subtract() {
        Matrix3f m1 = new Matrix3f(new double[][]{
                {1, 2, 3},
                {4, 10, 6},
                {7, 8, 9}});

        Matrix3f m2 = new Matrix3f(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});

        Matrix3f expected = m1.subtract(m2);
        Matrix3f actual = new Matrix3f(new double[][]{
                {0, 0, 0},
                {0, 5, 0},
                {0, 0, 0}});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void multiplyVector() {
        Matrix3f m = new Matrix3f(new double[][]{
                {3, -1, 2},
                {4, 2, 0},
                {-5, 6, 1}});

        Vector3f v = new Vector3f(new double[]{8, 7, 2});

        Vector3f expected = m.multiplyVector(v);
        Vector3f actual = new Vector3f(new double[]{21, 46, 4});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void multiply() {
        Matrix3f m1 = new Matrix3f(new double[][]{
                {3, -1, 2},
                {4, 2, 0},
                {-5, 6, 1}});
        Matrix3f m2 = new Matrix3f(new double[][]{
                {5, 4, 7},
                {2, 2, 0},
                {1, 1, 3}});

        Matrix3f expected = m1.multiply(m2);
        Matrix3f actual = new Matrix3f(new double[][]{
                {15, 12, 27},
                {24, 20, 28},
                {-12, -7, -32}});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void transpose() {
        Matrix3f expected = new Matrix3f(new double[][]{
                {1, 2, 3},
                {4, 10, 6},
                {7, 8, 9}});
        expected.transpose();

        Matrix3f actual = new Matrix3f(new double[][]{
                {1, 4, 7},
                {2, 10, 8},
                {3, 6, 9}});

        Assertions.assertEquals(expected, actual);
    }
}
