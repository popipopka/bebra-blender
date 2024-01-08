package com.meow.bebrablender.math.vectors;

/**
 *
 * @param <T> vector type
 */
public interface Vector<T> {
    double[] getCoords();

    void setCoords(double[] coords);

    T add(T v);

    T subtract(T v);
    T setSubtract(T v1, T v2);

    T multiply(double number);

    T divide(double number);

    double length();

    double scalarProduct(T v);

    T normalize();

    boolean isOrthogonal(T v);

    T to(T v);
}
