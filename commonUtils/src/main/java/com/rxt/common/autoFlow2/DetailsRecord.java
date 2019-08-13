package com.rxt.common.autoFlow;

/**
 * 每个用户的轨迹明细表
 */
public class DetailsRecord extends RPoint {
    private Long timestamp;    //时间戳

    public DetailsRecord(String flag) {
        super(flag);
    }

    public DetailsRecord(String flag, Long timestamp) {
        super(flag);
        this.timestamp = timestamp;
    }

    public DetailsRecord(String flag, Long timestamp, Point point) {
        super(flag, point);
        this.timestamp = timestamp;
    }

    public DetailsRecord(String flag, int x, int y, Long timestamp) {
        super(flag, x, y);
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return timestamp + "," + super.toString();
    }

    public static void main(String[] args) {
        System.out.println(new DetailsRecord("A2", System.currentTimeMillis(), ContextParam.randomPoint(1, "A2")));
    }
}
