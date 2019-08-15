package com.rxt.common.autoFlow2;

/**
 * 矩阵中每个点
 */
public class Point {
    private int X;
    private int Y;
    private transient Location Z;

    public Point() {
    }

    public Point(Point point) {
        this.X = point.getX();
        this.Y = point.getY();
        this.Z = point.getZ();
    }

    public Point(int x, int y) {
        X = x;
        Y = y;
        Z = Location.HALL;    //默认在站厅
    }

    public Point(int x, int y, Location z) {
        X = x;
        Y = y;
        Z = z;
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

    public Location getZ() {
        return Z;
    }

    public Point setZ(Location z) {
        Z = z;
        return this;
    }

    @Override
    public String toString() {
        return X + "," + Y + "," + Z;
    }
}

/**
 * 枚举类型：站台/站厅
 */
enum Location{
    HALL,       //站厅
    PLATFORM    //站台
}
