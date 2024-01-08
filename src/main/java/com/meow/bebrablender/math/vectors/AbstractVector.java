package com.meow.bebrablender.math.vectors;

import com.meow.bebrablender.general.Copiable;

import java.util.Arrays;

/**
 * @param <V> vector type
 */

public abstract class AbstractVector<V extends Vector> implements Vector<V>, Copiable<V> {
    protected double[] coords;
    protected final int size;
    private static final double EPSILON = 1E-6;

    protected AbstractVector(int size, double[] coords) {
        this.coords = coords;
        this.size = size;
        checkSize();
    }

    public AbstractVector(int size) {
        this.size = size;
        this.coords = new double[size];
    }

    @Override
    public void setCoords(double[] coords) {
        if (coords.length != size) {
            throw new IllegalArgumentException("Размерность массива не соответствует размерности вектора");
        }
        this.coords = coords;
    }

    private void checkSize() {
        if (this.size != this.coords.length) {
            throw new IllegalArgumentException("Размерность данного вектора равна " + size);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractVector<?> that = (AbstractVector<?>) o;
        if (size != that.size) return false;

        for (int i = 0; i < size; i++) {
            if (Math.abs(coords[i] - that.coords[i]) <= EPSILON) {
                return true;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "AbstractVector{" +
                Arrays.toString(coords) +
                '}';
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coords);
    }

    @Override
    public double[] coords() {
        return coords;
    }

    @Override
    public V add(V v) {
        double[] vCoords = v.coords();
        for (int i = 0; i < this.coords.length; i++) {
            this.coords[i] += vCoords[i];
        }
        return initialReturnThis();
    }

    @Override
    public V add(V v1, V v2) {
        double[] v1Coords = v1.coords();
        double[] v2Coords = v2.coords();

        double[] newCoords = new double[size];
        for (int i = 0; i < this.coords.length; i++) {
            newCoords[i] = v1Coords[i] + v2Coords[i];
        }

        setCoords(newCoords);
        return initialReturnThis();
    }

    @Override
    public V sub(V v) {
        double[] vCoord = v.coords();
        for (int i = 0; i < this.coords.length; i++) {
            this.coords[i] -= vCoord[i];
        }
        return initialReturnThis();
    }

    @Override
    public V sub(V v1, V v2) {
        double[] v1Coords = v1.coords();
        double[] v2Coords = v2.coords();

        double[] newCoords = new double[size];
        for (int i = 0; i < this.coords.length; i++) {
            newCoords[i] = v1Coords[i] - v2Coords[i];
        }

        setCoords(newCoords);
        return initialReturnThis();
    }

    @Override
    public V mul(double number) {
        for (int i = 0; i < this.coords.length; i++) {
            this.coords[i] *= number;
        }
        return initialReturnThis();
    }

    @Override
    public V mul(V v, double number) {
        double[] vCoords = v.coords();

        double[] newCoords = new double[size];
        for (int i = 0; i < this.coords.length; i++) {
            newCoords[i] = vCoords[i] * number;
        }

        setCoords(newCoords);
        return initialReturnThis();
    }

    @Override
    public V div(double number) {
        if (number == 0) throw new ArithmeticException("Деление на ноль запрещено");

        for (int i = 0; i < this.coords.length; i++) {
            this.coords[i] /= number;
        }
        return initialReturnThis();
    }

    @Override
    public V div(V v, double number) {
        double[] vCoords = v.coords();

        double[] newCoords = new double[size];
        for (int i = 0; i < this.coords.length; i++) {
            newCoords[i] = vCoords[i] / number;
        }

        setCoords(newCoords);
        return initialReturnThis();
    }

    @Override
    public double len() {
        double sum = 0;
        for (double e : this.coords) {
            sum += Math.pow(e, 2);
        }
        return Math.sqrt(sum);
    }

    @Override
    public V norm() {
        double length = len();

        if (length != 0) div(length);
        return initialReturnThis();
    }

    @Override
    public double sclProd(V v) {
        double result = 0;
        for (int i = 0; i < size; i++) {
            result += this.coords[i] * v.coords()[i];
        }
        return result;
    }

    @Override
    public boolean isOrt(V v) {
        return sclProd(v) == 0;
    }

    @Override
    public V to(V v) {
        double[] vCoords = v.coords();
        for (int i = 0; i < size; i++) {
            coords[i] = vCoords[i] - coords[i];
        }
        return initialReturnThis();
    }

    protected abstract V initialReturnThis();
}
