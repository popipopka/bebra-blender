package com.meow.bebrablender.math.matrices;

import com.meow.bebrablender.general.Copiable;
import com.meow.bebrablender.math.vectors.Vector;

import java.util.Arrays;

public abstract class AbstractSquareMatrix<M extends SquareMatrix, V extends Vector>
        implements SquareMatrix<M, V>, Copiable<M> {

    protected double[][] data;
    private final int size;

    protected AbstractSquareMatrix(int expectedSize, double[][] data) {
        this.data = data;
        this.size = expectedSize;
        checkSize();
    }

    protected AbstractSquareMatrix(int expectedSize) {
        this.size = expectedSize;
        this.data = new double[size][size];
        checkSize();
    }

    private void checkSize() {
        if (this.size != this.data.length) {
            throw new IllegalArgumentException(String.format("Размерность данной матрицы %d на %d", size, size));
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("AbstractSquareMatrix{\n");
        for (double[] arr : data) {
            str.append(Arrays.toString(arr)).append("\n");
        }
        str.append("}");

        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractSquareMatrix<?, ?> that = (AbstractSquareMatrix<?, ?>) o;
        if (size != that.size) return false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(Double.compare(data[i][j], that.data[i][j]) != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(data);
    }

    @Override
    public void setNull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.data[i][j] = 0;
            }
        }
    }

    @Override
    public void setUnit() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    this.data[i][j] = 1;
                    continue;
                }
                this.data[i][j] = 0;
            }
        }
    }

    protected abstract M initialReturnThis();

    protected abstract V initialCreateVector(double[] data);

    @Override
    public M add(M m) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.data[i][j] = this.data[i][j] + m.data()[i][j];
            }
        }

        return initialReturnThis();
    }

    @Override
    public M add(M m1, M m2) {
        double[][] newData = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newData[i][j] = m1.data()[i][j] + m2.data()[i][j];
            }
        }

        setData(newData);
        return initialReturnThis();
    }

    @Override
    public M sub(M m) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.data[i][j] = this.data[i][j] - m.data()[i][j];
            }
        }

        return initialReturnThis();
    }

    @Override
    public M sub(M m1, M m2) {
        double[][] newData = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newData[i][j] = m1.data()[i][j] - m2.data()[i][j];
            }
        }

        setData(newData);
        return initialReturnThis();
    }

    @Override
    public V mulVec(V v) {
        double[] newData = new double[size];

        double[] vCoord = v.coords();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newData[i] += this.data[i][j] * vCoord[j];
            }
        }
        return initialCreateVector(newData);
    }

    @Override
    public M mul(M m) {
        double[][] newData = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                for (int k = 0; k < size; k++) {
                    newData[i][j] += this.data[i][k] * m.data()[k][j];
                }
            }
        }
        this.setData(newData);
        return initialReturnThis();
    }

    @Override
    public M mul(M m1, M m2) {
        double[][] newData = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                for (int k = 0; k < size; k++) {
                    newData[i][j] += m1.data()[i][k] * m2.data()[k][j];
                }
            }
        }

        setData(newData);
        return initialReturnThis();
    }

    @Override
    public M trans() {
        double[][] newData = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newData[i][j] = data[j][i];
            }
        }
        this.data = newData;

        return initialReturnThis();
    }

    @Override
    public double[][] data() {
        return this.data;
    }

    @Override
    public void setData(double[][] data) {
        if(this.data.length != data.length || this.data[0].length != data[0].length) {
            throw new IllegalArgumentException("Размерность массива не соответствует размерности матрицы");
        }
        this.data = data;
    }
}
