package com.meow.bebrablender.rasterization;

import com.meow.bebrablender.math.points.Point2d;

public class ZBuffer {
    private double[][] zBufferMatrix;

    public ZBuffer(int width, int height) {
        this.zBufferMatrix = new double[width][height];
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
        return false;
    }
}
