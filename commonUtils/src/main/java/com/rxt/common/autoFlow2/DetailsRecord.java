package com.rxt.common.autoFlow2;

/**
 * 每个用户的轨迹明细表
 */
public final class DetailsRecord extends RPoint {
    private Long timestamp;    //时间戳

    public DetailsRecord(PointTag flag) {
        super(flag);
    }

    public DetailsRecord(PointTag flag, Long timestamp) {
        super(flag);
        this.timestamp = timestamp;
    }

    public DetailsRecord(PointTag flag, Long timestamp, Point point) {
        super(flag, point);
        this.timestamp = timestamp;
    }

    public DetailsRecord(PointTag flag, int x, int y, Long timestamp) {
        super(flag, x, y);
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return timestamp + "," + super.toString();
    }

    public static void main(String[] args) {
        RPoint rPoint = new RPoint(PointTag.TAG_X, new Point(5, 10));
        System.out.println(new DetailsRecord(rPoint.getFlag(), System.currentTimeMillis(), ContextParam.randomPoint(rPoint)));
    }
}
