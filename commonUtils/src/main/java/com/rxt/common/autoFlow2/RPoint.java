package com.rxt.common.autoFlow2;

public class RPoint extends Point {

    private transient PointTag flag;    //随机点位标识：X方向随机/Y方向随机
    private transient double randMulti;    //随机倍数

    public RPoint(PointTag flag) {
        this.flag = flag;
        this.randMulti = getMulti(flag);
    }

    public RPoint(PointTag flag, RandMultiple multi) {
        this.flag = flag;
        this.randMulti = getMulti(flag, multi);
    }

    public RPoint(PointTag flag, Point point) {
        super(point);
        this.flag = flag;
        this.randMulti = getMulti(flag);
    }

    public RPoint(PointTag flag, RandMultiple multi, Point point) {
        super(point);
        this.flag = flag;
        this.randMulti = getMulti(flag, multi);
    }

    public RPoint(PointTag flag, int x, int y) {
        super(x, y);
        this.flag = flag;
        this.randMulti = getMulti(flag);
    }

    public RPoint(PointTag flag, RandMultiple multi, int x, int y) {
        super(x, y);
        this.flag = flag;
        this.randMulti = getMulti(flag, multi);
    }

    public PointTag getFlag() {
        return flag;
    }

    public double getRandMulti() {
        return randMulti;
    }

    /**
     * 默认放大倍数为1.0倍
     */
    private static double getMulti(PointTag flag) {
        return getMulti(flag, RandMultiple.ONCE);
    }

    /**
     * 转换枚举类型倍数为double倍数
     *
     * @param multiple 枚举类型
     * @return
     */
    private static double getMulti(PointTag flag, RandMultiple multiple) {
        double multi = 1.0;

        if (flag == PointTag.NONE){    //如果没有随机方向，则不能进行随机倍数的设定
            return multi;
        }

        switch (multiple) {
            case ONCE:
                multi = 1.0;
                break;
            case ONCE_HALF:
                multi = 1.5;
                break;
            case DOUBLE:
                multi = 2.0;
                break;
            case DOUBLE_HALF:
                multi = 2.5;
                break;
            case TRIPLE:
                multi = 3.0;
                break;
            default:
                multi = 1.0;
        }
        return multi;
    }

    @Override
    public String toString() {
        return flag + "," + randMulti + "," + super.toString();
    }

    public static void main(String[] args) {
//        System.out.println(new RPoint(PointTag.TAG_X, new Point(5, 10)));
//        System.out.println(new RPoint(PointTag.TAG_Y, new Point(5, 10)));
//
//        System.out.println(getMulti(PointTag.TAG_X, RandMultiple.ONCE_HALF));
//        System.out.println(getMulti(PointTag.TAG_Y, RandMultiple.DOUBLE_HALF));
//        System.out.println(getMulti(PointTag.NONE, RandMultiple.TRIPLE));
//
//        System.out.println(new RPoint(PointTag.TAG_X, RandMultiple.DOUBLE_HALF, new Point(5, 10)));
//        System.out.println(new RPoint(PointTag.TAG_Y, RandMultiple.ONCE_HALF, new Point(5, 10)));
//        System.out.println(new RPoint(PointTag.TAG_Y, new Point(5, 10)));
        System.out.println(ContextParam.randomPoint(new RPoint(PointTag.NONE, new Point(1045, 881))));

    }
}

/**
 * 枚举类型：标识点位是X方向随机还是Y方向随机
 */
enum PointTag {
    NONE,     //不随机
    TAG_X,    //X方向随机
    TAG_Y     //Y方向随机
}

/**
 * 枚举类型：随机倍数
 */
enum RandMultiple {
    ONCE,           //1倍：随机数×1
    ONCE_HALF,      //1.5倍：随机数×1.5
    DOUBLE,         //2倍：随机数×2
    DOUBLE_HALF,    //2.5倍：随机数×2.5
    TRIPLE          //3倍：随机数×3
}