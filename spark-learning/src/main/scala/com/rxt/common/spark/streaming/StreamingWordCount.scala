package com.rxt.common.spark.streaming

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 使用spark streaming接收 nc -lk port 命令发来的数据
  */
object StreamingWordCount {
  def main(args: Array[String]): Unit = {
    //屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val hostName = "localhost"
    val port = 7777

    val conf = new SparkConf().setMaster("local[2]").setAppName("StreamingWordCount")
    val ssc = new StreamingContext(conf, Seconds(10))

    val line = ssc.socketTextStream(hostName, port)

    val res = line.flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _)
    res.print()

    res.foreachRDD(wd => wd.foreachPartition(
      data => {
        for (row <- data) {
          println(row._1 + " --> " + row._2) //row._1为 键（word），row._2为 值（count）
        }
      }
    ))

    //启动streaming
    ssc.start() //启动流式计算
    ssc.awaitTermination() //等待直到计算终止
  }

}
