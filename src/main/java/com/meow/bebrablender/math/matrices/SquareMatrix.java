package com.meow.bebrablender.math.matrices;

/**
 *
 * @param <T> matrix type
 * @param <V> vector type
 */
public interface SquareMatrix<T, V> {

    double[][] getData();

    void setNull();

    void setUnit();

    T add(T m);

    T subtract(T m);

    V multiplyVector(V v);

    T multiply(T m);

    void transpose();
}
