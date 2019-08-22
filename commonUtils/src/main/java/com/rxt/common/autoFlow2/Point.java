package com.rxt.common.autoFlow2;

import com.rxt.common.autoFlow.Region;

/**
 * 矩阵中每个点
 */
public class Point {
    private int X;
    private int Y;
    private transient Location Z;
    private transient com.rxt.common.autoFlow.Region area;    //点位所属区域

    public Point() {
    }

    public Point(Point point) {
        this.X = point.getX();
        this.Y = point.getY();
        this.Z = point.getZ();
        this.area = ContextParam.fixArea(point.getX(), point.getY(), point.getZ());
    }

    public Point(int x, int y) {
        X = x;
        Y = y;
        Z = Location.HALL;    //默认在站厅
        this.area = ContextParam.fixArea(x, y);
    }

    public Point(int x, int y, Location z) {
        X = x;
        Y = y;
        Z = z;
        this.area = ContextParam.fixArea(x, y, z);

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

    public Region getArea() {
        return area;
    }

    public Point setArea(Region area) {
        this.area = area;
        return this;
    }

    @Override
    public String toString() {
        return X + "," + Y + "," + Z + "," + area.getFlags();
    }
}

/**
 * 枚举类型：站台/站厅
 */
enum Location {
    HALL,       //站厅
    PLATFORM    //站台
}
