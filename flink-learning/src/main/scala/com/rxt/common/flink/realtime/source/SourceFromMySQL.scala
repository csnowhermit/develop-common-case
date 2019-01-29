package com.rxt.common.flink.realtime.source

import java.sql.{Connection, PreparedStatement}

import com.rxt.common.flink.realtime.utils.MysqlUtils
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, SourceFunction}

/**
  * 从MySQL读取数据作为source
  */
class SourceFromMySQL extends RichParallelSourceFunction[String] {
  private var ps: PreparedStatement = null
  private var connection: Connection = null;

  override def open(parameters: Configuration): Unit = {
    connection = MysqlUtils.getConnection()
    val sql = "select * from Order"
    ps = this.connection.prepareStatement(sql)
  }

  override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
    val resultSet = ps.executeQuery()
    while (resultSet.next()) {
      ctx.collect(resultSet.getString("id"))
    }
  }

  override def close(): Unit = {
    if (ps != null) {
      ps.close()
    }
    if (connection != null) {
      connection.close()
    }
  }

  override def cancel(): Unit = {

  }
}
