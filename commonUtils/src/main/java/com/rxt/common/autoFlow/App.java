package com.rxt.common.autoFlow;

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
    private static List<String> pass_in = Arrays.asList("A进", "B进", "D进");
    private static List<String> pass_out = Arrays.asList("A出", "D出");

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException, IOException {
//        ContextParam.print();
//        System.out.println(ContextParam.randomPoint(1, "A2"));
        List<String> userList = PassengerDao.getAllUserID();
        int tag = 0;    //1表示绝对坐标，其他值表示相对坐标
        int base = 500;    //每次时间至少增长
        int bound = 300;   //每次时间随机数bound

        Connection connection = OracleConn.getConn();

        String sql = "select OPER_DATE, LINE_NAME, STATION_NAME, HOURS, MINTUES, SECONDS, FLOW_IN, FLOW_OUT from gen_s_datang";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        // 获取
        Map<String, List<String>> passRouteMap = ContextParam.getPassRouteMap();

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
                    List<DetailsRecord> detailsRecordList = new ArrayList<>();

                    //1.对于进站，先选定一个人
                    String userid = userList.get(new Random().nextInt(userList.size()));

                    //2.对于每个人，选定一条路径
                    List<String> routes = passRouteMap.get(pass_in.get(new Random().nextInt(pass_in.size())));

                    //3.在这条路上每个区域内随机若干个点
                    for (String route : routes) {
                        for (int j = 0; j < 5 + new Random().nextInt(10); j++) {
                            mystamp += new Random().nextInt(bound) + base;
                            detailsRecordList.add(new DetailsRecord("进站", route, mystamp, ContextParam.randomPoint(tag, route)));
                        }
                    }

                    //4.打印每个人进站的记录
                    System.out.println(userid + ", " + detailsRecordList);
                    fileOutputStream.write((userid + ", " + detailsRecordList + "\n").getBytes());
                }
            }
            fileOutputStream.flush();    //进站处理完刷新一下

            // 再处理出站
            if (flow_out > 0) {
                for (int i = 0; i < flow_out; i++) {
                    long mystamp = timestamp;    //每个人一个时间戳
                    List<DetailsRecord> detailsRecordList = new ArrayList<>();

                    //1.对于出站，先选定一个人
                    String userid = userList.get(new Random().nextInt(userList.size()));

                    //2.对于每个人，选定一条路径
                    List<String> routes = passRouteMap.get(pass_out.get(new Random().nextInt(pass_out.size())));

                    //3.在这条路上每个区域内随机若干个点
                    for (String route : routes) {
                        for (int j = 0; j < 5 + new Random().nextInt(10); j++) {
                            mystamp += new Random().nextInt(bound) + base;
                            detailsRecordList.add(new DetailsRecord("出站", route, mystamp, ContextParam.randomPoint(tag, route)));
                        }
                    }

                    //4.打印每个人出站的记录
                    System.out.println(userid + ", " + detailsRecordList);
                    fileOutputStream.write((userid + ", " + detailsRecordList + "\n").getBytes());
                }
            }
            fileOutputStream.flush();    //出站处理完刷新一下
        }

        fileOutputStream.close();
        OracleConn.closeAll(connection, preparedStatement, resultSet);
    }
}
