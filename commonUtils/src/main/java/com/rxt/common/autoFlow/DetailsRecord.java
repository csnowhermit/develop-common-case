package com.rxt.common.autoFlow;

/**
 * 每个用户的轨迹明细表
 */
public class DetailsRecord extends RPoint {
    private String forward;    //方向
    private Long timestamp;    //时间戳

    public DetailsRecord(String flag) {
        super(flag);
    }

    public DetailsRecord(String forward, String flag, Long timestamp) {
        super(flag);
        this.forward = forward;
        this.timestamp = timestamp;
    }

    public DetailsRecord(String forward, String flag, Long timestamp, Point point) {
        super(flag, point);
        this.forward = forward;
        this.timestamp = timestamp;
    }

    public DetailsRecord(String forward, String flag, int x, int y, Long timestamp) {
        super(flag, x, y);
        this.forward = forward;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "DetailsRecord{" +
                "forward=" + forward + ", " +
                "timestamp=" + timestamp + ", " +
                "RPoint=" + super.toString() +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(new DetailsRecord("进站", "A2", System.currentTimeMillis(), ContextParam.randomPoint(1, "A2")));
    }
}
