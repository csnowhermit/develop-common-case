package com.rxt.common.autoFlow2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {
    private static Map<String, List<RPoint>> passRouteMap = null;
    private static List<String> pass_in = new ArrayList<>();
    private static List<String> pass_out = new ArrayList<>();

    private static Connection connection = null;
    private static PreparedStatement preparedStatement1 = null;
    //    private static PreparedStatement preparedStatement2 = null;
    private static Statement statement = null;
    private static ResultSet resultSet;

    private static FileOutputStream fos_text = null;

    static {
        try {
            connection = OracleConn.getConn();
            fos_text = new FileOutputStream(new File("d:/data/fos_text.csv"));

            String sql = "select OPER_DATE, LINE_NAME, STATION_NAME, HOURS, MINTUES, SECONDS, FLOW_IN, FLOW_OUT from gen_s_datang where OPER_DATE='2018-05-01'";
//            String saveSQL = "insert into gen_s_details(user_id, line_name, station_name, forward, mystamp, X, Y, area) " +
//                    "values('?', '?', '?', '?', '?', ?, ?, '?')";

            preparedStatement1 = connection.prepareStatement(sql);
//            preparedStatement2 = connection.prepareStatement(saveSQL);
            statement = connection.createStatement();

            resultSet = preparedStatement1.executeQuery();

            passRouteMap = ContextParam.getPassRouteMap();
            passRouteMap.keySet().forEach(e -> {
                if (e.contains("进")) {
                    pass_in.add(e);
                } else {
                    pass_out.add(e);
                }
            });

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException, IOException, InterruptedException {
        System.out.println(pass_in);
        System.out.println(pass_out);

        List<String> userList = PassengerDao.getAllUserID();

        Thread.sleep(5000);
        System.out.println("系统初始化完成");
        Thread.sleep(5000);

        int base = 2500;    //至少增长2500ms
        int bound = 500;    //每次的随机数

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/data/autoFlow.txt"));

        while (resultSet.next()) {
            String oper_date = resultSet.getString(1);
            String line_name = resultSet.getString(2);
            String station_name = resultSet.getString(3);
            String hours = resultSet.getString(4);
            String minutes = resultSet.getString(5);
            String seconds = resultSet.getString(6);
            int flow_in = resultSet.getInt(7);
            int flow_out = resultSet.getInt(8);

            StringBuffer sb = new StringBuffer(oper_date).append(" ")
                    .append(hours).append(":")
                    .append(minutes).append(":")
                    .append(seconds);

            Long timestamp = simpleDateFormat.parse(sb.toString()).getTime();    //13位毫秒时间戳

            // 先处理进站
            if (flow_in > 0) {
                for (int i = 0; i < flow_in; i++) {
                    String forward = "进站";
                    long mystamp = timestamp;    //每个人一个时间戳
                    RecordSet recordSet = new RecordSet();

                    //1.对于进站，先选定一个人
                    String userid = userList.get(new Random().nextInt(userList.size()));

                    //2.对于每个人，选定一条路径
                    List<RPoint> rPointList = passRouteMap.get(pass_in.get(new Random().nextInt(pass_in.size())));

                    recordSet.setHeader(new Header(userid, line_name, station_name, forward));

                    //3.对每一个点进行随机
                    for (RPoint rPoint : rPointList) {
                        mystamp += new Random().nextInt(bound) + base;    //当前一步的时间戳

                        DetailsRecord detailsRecord = new DetailsRecord(rPoint.getFlag(), mystamp, ContextParam.randomPoint(rPoint));
                        save2DB(userid, line_name, station_name, forward, detailsRecord);
//                        System.out.println(detailsRecord);

                        recordSet.getBody().getDetailsRecordList().add(detailsRecord);
                    }

                    //4.打印每个人进站的记录
//                    System.out.println(new Gson().toJson(recordSet));
//                    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();    //构建json字符串，排除掉@Expose注解修饰的字段
                    Gson gson = new Gson();
                    fileOutputStream.write((gson.toJson(recordSet) + "\n").getBytes());
                }
            }
//            preparedStatement2.execute("commit");
            statement.execute("commit");
            fileOutputStream.flush();    //进站处理完刷新一下

            // 再处理出站
            if (flow_out > 0) {
                for (int i = 0; i < flow_out; i++) {
                    String forward = "出站";
                    long mystamp = timestamp;    //每个人一个时间戳
                    RecordSet recordSet = new RecordSet();

                    //1.对于出站，先选定一个人
                    String userid = userList.get(new Random().nextInt(userList.size()));

                    //2.对于每个人，选定一条路径
                    List<RPoint> rPointList = passRouteMap.get(pass_out.get(new Random().nextInt(pass_out.size())));

                    recordSet.setHeader(new Header(userid, line_name, station_name, forward));

                    //3.对每个点进行随机
                    for (RPoint rPoint : rPointList) {
                        mystamp += new Random().nextInt(bound) + base;

                        DetailsRecord detailsRecord = new DetailsRecord(rPoint.getFlag(), mystamp, ContextParam.randomPoint(rPoint));
                        save2DB(userid, line_name, station_name, forward, detailsRecord);
//                        System.out.println(detailsRecord);

                        recordSet.getBody().getDetailsRecordList().add(new DetailsRecord(rPoint.getFlag(), mystamp, ContextParam.randomPoint(rPoint)));
                    }

                    //4.打印每个人出站的记录
//                    System.out.println(recordSet);
//                    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();    //构建json字符串，排除掉@Expose注解修饰的字段
                    Gson gson = new Gson();
                    fileOutputStream.write((gson.toJson(recordSet) + "\n").getBytes());
                }
            }
//            preparedStatement2.execute("commit");
            statement.execute("commit");
            fileOutputStream.flush();    //出站处理完刷新一下
            System.out.println("已处理完 " + line_name + " " + station_name + " 站 " + sb.toString() + " 时间数据");
        }

        fileOutputStream.close();
        fos_text.close();
        OracleConn.closeAll(connection, preparedStatement1, resultSet);
        OracleConn.closeAll(connection, statement, resultSet);
    }

    /**
     * 明细表存库
     *
     * @param userid
     * @param line_name
     * @param station_name
     * @param forward
     * @param detailsRecord
     */
    private static void save2DB(String userid, String line_name, String station_name, String forward, DetailsRecord detailsRecord) {
//        try {
//            preparedStatement2.setString(1, userid);
//            preparedStatement2.setString(2, line_name);
//            preparedStatement2.setString(3, station_name);
//            preparedStatement2.setString(4, forward);
//            preparedStatement2.setString(5, detailsRecord.getTimestamp().toString());
//            preparedStatement2.setInt(6, detailsRecord.getX());
//            preparedStatement2.setInt(7, detailsRecord.getY());
//            preparedStatement2.setString(8, detailsRecord.getArea().getFlags());
//
//            preparedStatement2.execute();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        String saveSQL = "insert into gen_s_details(user_id, line_name, station_name, forward, mystamp, X, Y, area) " +
//                "values('" + userid + "', " +
//                "'" + line_name + "', " +
//                "'" + station_name + "', " +
//                "'" + forward + "', " +
//                "'" + detailsRecord.getTimestamp() + "', " +
//                detailsRecord.getX() + ", " +
//                detailsRecord.getY() + ", " +
//                "'" + detailsRecord.getArea().getFlags() + "')";
//
//        try {
//            statement.execute(saveSQL);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        USER_ID
//                LINE_NAME
//        STATION_NAME
//                FORWARD
//        MYSTAMP
//                X
//        Y
//                AREA
        StringBuffer sb = new StringBuffer();
        sb.append(userid).append(",");
//        sb.append(line_name).append(",");
//        sb.append(station_name).append(",");
//        sb.append(forward).append(",");
        sb.append(detailsRecord.getTimestamp()).append(",");
        sb.append(detailsRecord.getX()).append(",");
        sb.append(detailsRecord.getY()).append(",");
        sb.append(detailsRecord.getArea().getFlags()).append("\n");

        try {
            fos_text.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}