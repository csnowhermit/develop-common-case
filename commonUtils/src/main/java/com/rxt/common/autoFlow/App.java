package com.rxt.common.autoFlow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException, InterruptedException {
//        ContextParam.print();
//        System.out.println(ContextParam.randomPoint("A2"));
        List<String> userList = PassengerDao.getAllUserID();

        Connection connection = OracleConn.getConn();

        String sql = "select OPER_DATE, LINE_NAME, STATION_NAME, HOURS, MINTUES, SECONDS, FLOW_IN, FLOW_OUT from gen_s_datang";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        // 获取
        Map<String, List<String>> passRouteMap = ContextParam.getPassRouteMap();

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
                CountDownLatch countDownLatch = new CountDownLatch(flow_in);
                for (int i = 0; i < flow_in; i++) {
                    new Thread().start();
                    countDownLatch.countDown();
                }
                countDownLatch.await();
            }

            // 再处理出站
            if (flow_out > 0) {
                CountDownLatch countDownLatch = new CountDownLatch(flow_out);
                for (int i = 0; i < flow_out; i++) {
                    new Thread().start();
                    countDownLatch.countDown();
                }
                countDownLatch.await();
            }


        }

        OracleConn.closeAll(connection, preparedStatement, resultSet);
    }
}
