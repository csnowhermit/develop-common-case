package com.rxt.common.gen;

import com.rxt.common.redEnvelopes.RandomValue05;

import java.sql.*;

public class RandomTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        for (int i = 0; i < 1000; i++) {
//            System.out.println(RandomValue05.createBonusList(18637, 60, (int)(18637/60*0.8), (int)(18637/60*1.2)));
//        }
        Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序

        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@10.10.56.106:1521:ORCL", "gzm", "gzm");


        String sql = "select to_char(OPER_DATE, 'yyyy-mm-dd'), LINE_NAME, STATION_NAME, FLOW_IN, FLOW_OUT, TIME_INTERVAL from passenger_flow where STATION_NAME=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "东山口");

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + ", " +
                    rs.getString(2) + ", " +
                    rs.getString(3) + ", " +
                    rs.getInt(4) + ", " +
                    rs.getInt(5) + ", " +
                    rs.getString(6)
            );
        }

        rs.close();
        preparedStatement.close();
        connection.close();
    }
}
