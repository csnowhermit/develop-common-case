package com.rxt.common.autoFlow2;

import java.sql.*;

/**
 * Oracle连接
 */
public class OracleConn {
    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@10.10.56.106:1521:ORCL", "gzm", "gzm");

        return connection;
    }

    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }


}
