package com.rxt.common.flink.realtime.sink

import java.sql.{Connection, PreparedStatement}

import com.rxt.common.flink.realtime.utils.MysqlUtils
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}

/**
  * sink目的地为MySQL
  */
class Sink2MySQL extends RichSinkFunction[String] {
  private var ps: PreparedStatement = null
  private var connection: Connection = null

  override def open(parameters: Configuration): Unit = {
    connection = MysqlUtils.getConnection()
    val sql = "insert into Student(id, name, password, age) values(?, ?, ?, ?);"
    ps = this.connection.prepareStatement(sql)
  }

  //循环调用
  override def invoke(value: String, context: SinkFunction.Context[_]): Unit = {
    ps.setString(1, value)
  }

  override def close(): Unit = {
    if (ps != null) {
      ps.close()
    }
    if (connection != null) {
      connection.close()
    }
  }

}
