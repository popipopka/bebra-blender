package com.meow.bebrablender.math.matrices;

/**
 *
 * @param <M> matrix type
 * @param <V> vector type
 */
public interface SquareMatrix<M, V> {

    double[][] data();

    void setData(double[][] data);

    void setNull();

    void setUnit();

    M add(M m);

    M add(M m1, M m2);

    M sub(M m);

    M sub(M m1, M m2);

    V mulVec(V v);

    M mul(M m);

    M mul(M m1, M m2);

    M trans();
}
