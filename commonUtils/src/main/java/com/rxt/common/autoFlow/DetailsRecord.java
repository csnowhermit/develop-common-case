package com.rxt.common.autoFlow;

/**
 * 每个用户的轨迹明细表
 */
public class DetailsRecord extends RPoint {
    private String line_name;      //线路
    private String station_name;   //车站名称
    private String forward;    //方向
    private Long timestamp;    //时间戳

    public DetailsRecord(String flag) {
        super(flag);
    }

    public DetailsRecord(String line_name, String station_name, String forward, String flag, Long timestamp) {
        super(flag);
        this.line_name = line_name;
        this.station_name = station_name;
        this.forward = forward;
        this.timestamp = timestamp;
    }

    public DetailsRecord(String line_name, String station_name, String forward, String flag, Long timestamp, Point point) {
        super(flag, point);
        this.line_name = line_name;
        this.station_name = station_name;
        this.forward = forward;
        this.timestamp = timestamp;
    }

    public DetailsRecord(String line_name, String station_name, String forward, String flag, int x, int y, Long timestamp) {
        super(flag, x, y);
        this.line_name = line_name;
        this.station_name = station_name;
        this.forward = forward;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "DetailsRecord{" +
                "line_name=" + line_name + ", " +
                "station_name=" + station_name + ", " +
                "forward=" + forward + ", " +
                "timestamp=" + timestamp + ", " +
                "RPoint=" + super.toString() +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(new DetailsRecord("三号线", "大塘", "进站", "A2", System.currentTimeMillis(), ContextParam.randomPoint(1, "A2")));
    }
}
