package com.meow.bebrablender.rasterization;

import com.meow.bebrablender.math.points.Point2d;

import java.util.Arrays;

public class ZBuffer {
    private final double[][] zBufferMatrix;

    public ZBuffer(int width, int height) {
        this.zBufferMatrix = new double[height][width];

        //Заполним максимальными значениями
        for (double[] row : zBufferMatrix) {
            Arrays.fill(row, Double.MAX_VALUE);
        }
    }

    public double getBufferValue(int row, int col) {
        return zBufferMatrix[row][col];
    }

    public ZBuffer setBufferValue(int row, int col, double value) {
        zBufferMatrix[row][col] = value;
        return this;
    }

    public boolean isFrontestPoint(Point2d point2d, double zValue) {
        //todo: сделать
        return zBufferMatrix[(int) point2d.getY()][(int) point2d.getX()] <= zValue;
    }
}
