package com.rxt.common.autoFlow2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {
    private static Map<String, List<RPoint>> passRouteMap = null;
    private static List<String> pass_in = new ArrayList<>();
    private static List<String> pass_out = new ArrayList<>();

    static {
        passRouteMap = ContextParam.getPassRouteMap();
        for (String s : passRouteMap.keySet()) {
            if (s.contains("进")) {
                pass_in.add(s);
            } else {
                pass_out.add(s);
            }
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


        Connection connection = OracleConn.getConn();

        String sql = "select OPER_DATE, LINE_NAME, STATION_NAME, HOURS, MINTUES, SECONDS, FLOW_IN, FLOW_OUT from gen_s_datang";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/autoFlow.txt"));

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
                    long mystamp = timestamp;    //每个人一个时间戳
                    RecordSet recordSet = new RecordSet();

                    //1.对于进站，先选定一个人
                    String userid = userList.get(new Random().nextInt(userList.size()));

                    //2.对于每个人，选定一条路径
                    List<RPoint> rPointList = passRouteMap.get(pass_in.get(new Random().nextInt(pass_in.size())));

                    recordSet.setHeader(new Header(userid, line_name, station_name, "进站"));

                    //3.对每一个点进行随机
                    for (RPoint rPoint : rPointList) {
                        mystamp += new Random().nextInt(bound) + base;    //当前一步的时间戳
                        recordSet.getBody().getDetailsRecordList().add(new DetailsRecord(rPoint.getFlag(), mystamp, ContextParam.randomPoint(rPoint)));
                    }

                    //4.打印每个人进站的记录
//                    System.out.println(recordSet);
//                    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();    //构建json字符串，排除掉@Expose注解修饰的字段
                    Gson gson = new Gson();
                    fileOutputStream.write((gson.toJson(recordSet) + "\n").getBytes());
                }
            }
            fileOutputStream.flush();    //进站处理完刷新一下

//            // 再处理出站
//            if (flow_out > 0) {
//                for (int i = 0; i < flow_out; i++) {
//                    long mystamp = timestamp;    //每个人一个时间戳
//                    RecordSet recordSet = new RecordSet();
//
//                    //1.对于出站，先选定一个人
//                    String userid = userList.get(new Random().nextInt(userList.size()));
//
//                    //2.对于每个人，选定一条路径
//                    List<RPoint> rPointList = passRouteMap.get(pass_out.get(new Random().nextInt(pass_out.size())));
//
//                    recordSet.setHeader(new Header(userid, line_name, station_name, "出站"));
//
//                    //3.对每个点进行随机
//                    for (RPoint rPoint : rPointList) {
//                        mystamp += new Random().nextInt(bound) + base;
//                        recordSet.getBody().getDetailsRecordList().add(new DetailsRecord(rPoint.getFlag(), mystamp, ContextParam.randomPoint(rPoint)));
//                    }
//
//                    //4.打印每个人出站的记录
////                    System.out.println(recordSet);
////                    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();    //构建json字符串，排除掉@Expose注解修饰的字段
//                    Gson gson = new Gson();
//                    fileOutputStream.write((gson.toJson(recordSet) + "\n").getBytes());
//                }
//            }
//            fileOutputStream.flush();    //出站处理完刷新一下
            System.out.println("已处理完 " + line_name + " " + station_name + " 站 " + sb.toString() + " 时间数据");
        }

        fileOutputStream.close();
        OracleConn.closeAll(connection, preparedStatement, resultSet);
    }
}
