package com.rxt.common.autoFlow;

/**
 * 矩阵中每个点
 */
public class Point {
    private int X;
    private int Y;

    public Point() {
    }

    public Point(Point point) {
        this.X = point.getX();
        this.Y = point.getY();
    }

    public Point(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public Point setX(int x) {
        X = x;
        return this;
    }

    public int getY() {
        return Y;
    }

    public Point setY(int y) {
        Y = y;
        return this;
    }

    @Override
    public String toString() {
        return X + "," + Y;
    }
}
