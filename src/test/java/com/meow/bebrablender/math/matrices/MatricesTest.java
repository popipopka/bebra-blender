package com.meow.bebrablender.math.matrices;

import com.meow.bebrablender.math.vectors.Vector3d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatricesTest {
    @Test
    void setNull() {
        Matrix3d expected = new Matrix3d(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        expected.setNull();

        Matrix3d actual = new Matrix3d(new double[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setUnit() {
        Matrix3d expected = new Matrix3d(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});
        expected.setUnit();

        Matrix3d actual = new Matrix3d(new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void add() {
        Matrix3d m1 = new Matrix3d(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});

        Matrix3d m2 = new Matrix3d(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});

        Matrix3d expected = m1.add(m2);
        Matrix3d actual = new Matrix3d(new double[][]{
                {2, 4, 6},
                {8, 10, 12},
                {14, 16, 18}});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addNew() {
        Matrix3d m1 = new Matrix3d(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});

        Matrix3d m2 = new Matrix3d(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});

        Matrix3d expected = new Matrix3d(new double[3][3]);
        expected.add(m1, m2);

        Matrix3d actual = new Matrix3d(new double[][]{
                {2, 4, 6},
                {8, 10, 12},
                {14, 16, 18}});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sub() {
        Matrix3d m1 = new Matrix3d(new double[][]{
                {1, 2, 3},
                {4, 10, 6},
                {7, 8, 9}});

        Matrix3d m2 = new Matrix3d(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});

        Matrix3d expected = m1.sub(m2);
        Matrix3d actual = new Matrix3d(new double[][]{
                {0, 0, 0},
                {0, 5, 0},
                {0, 0, 0}});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void subNew() {
        Matrix3d m1 = new Matrix3d(new double[][]{
                {1, 2, 3},
                {4, 10, 6},
                {7, 8, 9}});

        Matrix3d m2 = new Matrix3d(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}});


        Matrix3d expected = new Matrix3d(new double[3][3]);
        expected.sub(m1, m2);

        Matrix3d actual = new Matrix3d(new double[][]{
                {0, 0, 0},
                {0, 5, 0},
                {0, 0, 0}});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void mulVect() {
        Matrix3d m = new Matrix3d(new double[][]{
                {3, -1, 2},
                {4, 2, 0},
                {-5, 6, 1}});

        Vector3d v = new Vector3d(8, 7, 2);

        Vector3d expected = m.mulVec(v);
        Vector3d actual = new Vector3d(21, 46, 4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void mul() {
        Matrix3d m1 = new Matrix3d(new double[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}});
        Matrix3d m2 = new Matrix3d(new double[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}});

        Matrix3d expected = m1.mul(m2);
        Matrix3d actual = new Matrix3d(new double[][]{
                {3, 3, 3},
                {3, 3, 3},
                {3, 3, 3}});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void mulNew() {
        Matrix3d m1 = new Matrix3d(new double[][]{
                {3, -1, 2},
                {4, 2, 0},
                {-5, 6, 1}});
        Matrix3d m2 = new Matrix3d(new double[][]{
                {5, 4, 7},
                {2, 2, 0},
                {1, 1, 3}});

        Matrix3d expected = new Matrix3d(new double[3][3]);
        expected.mul(m1, m2);

        Matrix3d actual = new Matrix3d(new double[][]{
                {15, 12, 27},
                {24, 20, 28},
                {-12, -7, -32}});

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void trans() {
        Matrix3d expected = new Matrix3d(new double[][]{
                {1, 2, 3},
                {4, 10, 6},
                {7, 8, 9}});
        expected.trans();

        Matrix3d actual = new Matrix3d(new double[][]{
                {1, 4, 7},
                {2, 10, 8},
                {3, 6, 9}});

        Assertions.assertEquals(expected, actual);
    }
}
