package com.meow.bebrablender.math.vectors;

/**
 *
 * @param <T> vector type
 */
public interface Vector<T> {
    double[] getCoords();

    T add(T v);

    T subtract(T v);

    T multiply(double number);

    T divide(double number);

    double length();

    double scalarProduct(T v);

    T normalize();

    boolean isOrthogonal(T v);

    T to(T v);
}
