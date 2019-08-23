package com.rxt.common.autoFlow2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 统数：客流密度热力图
 * create table sta_datang_tongji_details as
 * select
 * case area when 'null' then 'B口外铁马阵'
 * else area end region,
 * stamp, count(distinct user_id) nums,
 * listagg(rpoint, ',') within group (order by rpoint) newline
 * from
 * (select user_id, forward, floor(mystamp/1000) stamp, X||'-'||Y rpoint, area from gen_s_details) tmp
 * group by  area, stamp;
 */
public class AggData_heatMap {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Connection connection = OracleConn.getConn();
        Statement statement = connection.createStatement();
        String sql = "select region, stamp, nums, newline from sta_datang_tongji_details order by stamp asc";

        ResultSet rs = statement.executeQuery(sql);

        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/data/heart_map.txt"));

        int count = 0;
        while (rs.next()) {
            String region = rs.getString(1);
            String stamp = rs.getString(2);
            int nums = rs.getInt(3);
            String newLine = rs.getString(4);

            String[] arrs = newLine.split(",");
            for (String s : arrs) {
                StringBuffer sb = new StringBuffer("[");
                sb.append(s.split("-")[0]).append(",");
                sb.append(s.split("-")[1]).append(",");
                sb.append("'").append(region).append("',");
                sb.append(stamp).append(",");
                sb.append(nums).append("],").append("\n");

//                System.out.println(sb.toString());
                fileOutputStream.write(sb.toString().getBytes());
            }

            count++;
            if (count % 10000 == 0) {
                System.out.println("已处理 " + count + " 条记录");
            }
        }
        System.out.println("已处理完成 " + count + " 条记录");

        OracleConn.closeAll(connection, statement, rs);
        fileOutputStream.close();
    }
}
