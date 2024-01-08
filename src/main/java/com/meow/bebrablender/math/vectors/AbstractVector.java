package com.meow.bebrablender.math.vectors;

import java.util.Arrays;

/**
 * @param <T> vector type
 */

public abstract class AbstractVector<T extends Vector> implements Vector<T> {
    protected double[] coords;
    protected final int size;
    private static final double EPSILON = 1e-6;

    protected AbstractVector(int size, double[] coords) {
        this.coords = coords;
        this.size = size;
        checkSize(coords.length);
    }

    @Override
    public void setCoords(double[] coords) {
        if (coords.length != size) {
            throw new IllegalArgumentException("Размерность массива не соответствует размерности вектора");
        }
        this.coords = Arrays.copyOf(coords, size);
    }

    private void checkSize(int expectedSize) {
        if (this.size != expectedSize) {
            throw new IllegalArgumentException("Размерность данного вектора равна " + expectedSize);
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
                return false;
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
    public double[] getCoords() {
        return coords;
    }

    @Override
    public T add(T v) {
        double[] vCoord = v.getCoords();
        for (int i = 0; i < this.coords.length; i++) {
            this.coords[i] += vCoord[i];
        }
        return initialReturnThis();
    }

    @Override
    public T subtract(T v) {
        double[] vCoord = v.getCoords();
        for (int i = 0; i < this.coords.length; i++) {
            this.coords[i] -= vCoord[i];
        }
        return initialReturnThis();
    }

    @Override
    public T multiply(double number) {
        for (int i = 0; i < this.coords.length; i++) {
            this.coords[i] *= number;
        }
        return initialReturnThis();
    }

    @Override
    public T divide(double number) {
        if (number == 0) throw new ArithmeticException("Деление на ноль запрещено");

        for (int i = 0; i < this.coords.length; i++) {
            this.coords[i] /= number;
        }
        return initialReturnThis();
    }

    @Override
    public double length() {
        double sum = 0;
        for (double e : this.coords) {
            sum += Math.pow(e, 2);
        }
        return Math.sqrt(sum);
    }

    @Override
    public T normalize() {
        double length = length();

        if (length != 0) divide(length);
        return initialReturnThis();
    }

    @Override
    public double scalarProduct(T v) {
        double result = 0;
        for (int i = 0; i < size; i++) {
            result += this.coords[i] * v.getCoords()[i];
        }
        return result;
    }

    @Override
    public boolean isOrthogonal(T v) {
        return scalarProduct(v) == 0;
    }

    @Override
    public T to(T v) {
        double[] vCoords = v.getCoords();
        for (int i = 0; i < size; i++) {
            coords[i] = vCoords[i] - coords[i];
        }
        return initialReturnThis();
    }

    protected abstract T initialReturnThis();
}
