package com.rxt.common.autoFlow2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao层：获取乘客ID
 */
public class PassengerDao extends OracleConn {

    public static List<String> getAllUserID() throws SQLException, ClassNotFoundException {
        List<String> userList = new ArrayList<>();

        Connection connection = OracleConn.getConn();

        String sql = "select UUID from GEN_T_CARD where rownum<=46000";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            userList.add(resultSet.getString(1));
        }

        OracleConn.closeAll(connection, preparedStatement, resultSet);
        return userList;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<String> userList = getAllUserID();
        System.out.println(userList.size());
        for (String s : userList) {
            System.out.println(s);
        }

    }
}
