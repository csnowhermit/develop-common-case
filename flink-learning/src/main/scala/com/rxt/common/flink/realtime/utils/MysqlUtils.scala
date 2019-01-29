package com.rxt.common.flink.realtime.utils

import java.sql.{Connection, DriverManager}

object MysqlUtils {

  var conn: Connection = null

  def getConnection(): Connection = {
    if (conn == null) {
      Class.forName("com.mysql.jdbc.Driver")
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8", "root", "123456")
    }
    conn
  }


}

