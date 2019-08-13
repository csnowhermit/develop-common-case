package com.rxt.common.autoFlow2;

import com.google.gson.annotations.Expose;

public class RPoint extends Point {

    private transient PointTag flag;    //随机点位标识：X方向随机/Y方向随机

    public RPoint(PointTag flag) {
        this.flag = flag;
    }

    public RPoint(PointTag flag, Point point) {
        super(point);
        this.flag = flag;
    }

    public RPoint(PointTag flag, int x, int y) {
        super(x, y);
        this.flag = flag;
    }

    public PointTag getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return flag + "," + super.toString();
    }

    public static void main(String[] args) {
        System.out.println(new RPoint(PointTag.TAG_X, new Point(5, 10)));
        System.out.println(new RPoint(PointTag.TAG_Y, new Point(5, 10)));
    }
}

/**
 * 枚举类型：标识点位是X方向随机还是Y方向随机
 */
enum PointTag{
    TAG_X,    //X方向随机
    TAG_Y     //Y方向随机
}