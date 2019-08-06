package com.rxt.common.autoFlow;

public class RPoint extends Point {
    private String flag;    //区域标识

    public RPoint(String flag) {
        this.flag = flag;
    }

    public RPoint(String flag, Point point) {
        super(point);
        this.flag = flag;
    }

    public RPoint(String flag, int x, int y) {
        super(x, y);
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "RPoint{" +
                "flag='" + flag + '\'' + ", " +
                "point=" + super.toString() +
                '}';
    }
}
