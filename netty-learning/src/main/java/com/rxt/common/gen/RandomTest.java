package com.rxt.common.gen;

import com.rxt.common.redEnvelopes.RandomValue02;
import com.rxt.common.redEnvelopes.RandomValue05;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
//        for (int i = 0; i < 1000; i++) {
//            System.out.println(RandomValue05.createBonusList(18637, 60, (int)(18637/60*0.8), (int)(18637/60*1.2)));
//        }
        Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序

        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@10.10.56.106:1521:ORCL", "gzm", "gzm");

        String sql = "select to_char(OPER_DATE, 'yyyy-mm-dd'), LINE_NAME, STATION_NAME, FLOW_IN, FLOW_OUT, TIME_INTERVAL from passenger_flow where STATION_NAME=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "大塘");
//        preparedStatement.setString(2, "2018-05-01");

        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/datang.csv"));

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
//            System.out.println(rs.getString(1) + ", " +
//                    rs.getString(2) + ", " +
//                    rs.getString(3) + ", " +
//                    rs.getInt(4) + ", " +
//                    rs.getInt(5) + ", " +
//                    rs.getString(6)
//            );

            String oper_date = rs.getString(1);    //日期
            String line_name = rs.getString(2);    //线路
            String station_name = rs.getString(3);   //车站名称
            int flow_in = rs.getInt(4);    //进站量
            int flow_out = rs.getInt(5);   //出站量
            String time_interval = rs.getString(6);    //时段

            if ("00".equals(time_interval) ||
                    "01".equals(time_interval) ||
                    "02".equals(time_interval) ||
                    "03".equals(time_interval) ||
                    "04".equals(time_interval) ||
                    "05".equals(time_interval)) {
                continue;
            }
            divided(fileOutputStream, oper_date, line_name, station_name, flow_in, flow_out, time_interval);
        }

        rs.close();
        preparedStatement.close();
        connection.close();

        fileOutputStream.flush();
        fileOutputStream.close();
    }

    /**
     * 拆分客流到每秒
     *
     * @param fileOutoutStream
     * @param oper_date
     * @param line_name
     * @param station_name
     * @param flow_in
     * @param flow_out
     * @param time_interval
     */
    private static void divided(FileOutputStream fileOutoutStream, String oper_date, String line_name, String station_name, int flow_in, int flow_out, String time_interval) throws IOException {

        List<Integer> flow_in_list = new ArrayList<>();
        List<Integer> flow_out_list = new ArrayList<>();

        if ("06".equals(time_interval)) {
            flow_in_list = RandomValue02.createBonusList(flow_in, 60 - 7);
            flow_out_list = RandomValue02.createBonusList(flow_out, 60 - 7);

            int minutes = 7;
//            int seconds = 0;

            for (int i = 0; i < flow_in_list.size(); i++) {
                flow_in_list.get(i);    //进站量
                flow_out_list.get(i);    //出站量

                String pringline = new PassengerPerSeconds(oper_date, line_name, station_name, time_interval, Integer.toString(minutes), "00", flow_in_list.get(i), flow_out_list.get(i)).toString();

                fileOutoutStream.write(pringline.getBytes());

                //                seconds++;
//                if (seconds == 60){
//                    minutes ++;
//                    seconds = 0;
//                }
                minutes++;
            }


        } else if ("23".equals(time_interval)) {
            flow_in_list = RandomValue02.createBonusList(flow_in, 48);
            flow_out_list = RandomValue02.createBonusList(flow_out, 48);

            int minutes = 0;
//            int seconds = 0;

            for (int i = 0; i < flow_in_list.size(); i++) {
                flow_in_list.get(i);    //进站量
                flow_out_list.get(i);    //出站量

                String pringline = new PassengerPerSeconds(oper_date, line_name, station_name, time_interval, Integer.toString(minutes), "00", flow_in_list.get(i), flow_out_list.get(i)).toString();

                fileOutoutStream.write(pringline.getBytes());

                //                seconds++;
//                if (seconds == 60){
//                    minutes ++;
//                    seconds = 0;
//                }
                minutes++;
            }

        } else {
            flow_in_list = RandomValue02.createBonusList(flow_in, 60);
            flow_out_list = RandomValue02.createBonusList(flow_out, 60);

            int minutes = 0;
//            int seconds = 0;

            for (int i = 0; i < flow_in_list.size(); i++) {
                flow_in_list.get(i);    //进站量
                flow_out_list.get(i);    //出站量

                String pringline = new PassengerPerSeconds(oper_date, line_name, station_name, time_interval, Integer.toString(minutes), "00", flow_in_list.get(i), flow_out_list.get(i)).toString();

                fileOutoutStream.write(pringline.getBytes());

                //                seconds++;
//                if (seconds == 60){
//                    minutes ++;
//                    seconds = 0;
//                }
                minutes++;
            }
        }

    }

}

class PassengerPerSeconds {
    private String oper_date;    //日期
    private String line_name;    //线路
    private String station_name;    //车站
    private String hours;     //时
    private String minutes;   //分
    private String seconds;   //秒
    private int flow_in;      //进站量
    private int flow_out;     //出站量

    public PassengerPerSeconds() {
    }

    public PassengerPerSeconds(String oper_date, String line_name, String station_name, String hours, String minutes, String seconds, int flow_in, int flow_out) {
        this.oper_date = oper_date;
        this.line_name = line_name;
        this.station_name = station_name;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.flow_in = flow_in;
        this.flow_out = flow_out;
    }

    public String getOper_date() {
        return oper_date;
    }

    public void setOper_date(String oper_date) {
        this.oper_date = oper_date;
    }

    public String getLine_name() {
        return line_name;
    }

    public void setLine_name(String line_name) {
        this.line_name = line_name;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }

    public int getFlow_in() {
        return flow_in;
    }

    public void setFlow_in(int flow_in) {
        this.flow_in = flow_in;
    }

    public int getFlow_out() {
        return flow_out;
    }

    public void setFlow_out(int flow_out) {
        this.flow_out = flow_out;
    }

    @Override
    public String toString() {
//        return "PassengerPerSeconds{" +
//                "oper_date='" + oper_date + '\'' +
//                ", line_name='" + line_name + '\'' +
//                ", station_name='" + station_name + '\'' +
//                ", hours='" + hours + '\'' +
//                ", minutes='" + minutes + '\'' +
//                ", seconds='" + seconds + '\'' +
//                ", flow_in=" + flow_in +
//                ", flow_out=" + flow_out +
//                '}';
        return oper_date + "," +
                line_name + "," +
                station_name + "," +
                hours + "," +
                minutes + "," +
                seconds + "," +
                flow_in + "," +
                flow_out + "\n";
    }
}

