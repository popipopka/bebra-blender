package com.meow.bebrablender.math.vectors;

/**
 *
 * @param <V> vector type
 */
public interface Vector<V> {
    double[] coords();

    void setCoords(double[] coords);

    V add(V v);

    V add(V v1, V v2);

    V sub(V v);
    
    V sub(V v1, V v2);

    V mul(double number);
    
    V mul(V v, double number);

    V div(double number);
    
    V div(V v, double number);

    double len();

    double sclProd(V v);

    V norm();

    boolean isOrt(V v);

    V to(V v);
}
