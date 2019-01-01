package com.rxt.common.spark.streaming.kafka

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * spark streaming接收kafka数据
  */
object AcceptGateway {
  def main(args: Array[String]): Unit = {

    //屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val hostName = "192.168.117.101"
    val port = 9092

    val conf = new SparkConf().setMaster("local[2]").setAppName("AcceptGateway")
    val ssc = new StreamingContext(conf, Seconds(1))

    val line = ssc.socketTextStream(hostName, port)

    line.print()

    //line.saveAsTextFiles(args(3))

    //    line.foreachRDD(wd => wd.foreachPartition(
    //      data => {
    //        for (row <- data) {
    //
    //          try {
    //            var row_b: Array[Byte] = row.getBytes("utf-8")
    //
    //            for (b <- row_b) {
    //              print(b)
    //            }
    //            println("\n+++++++++++++++++++++++")
    //
    //            //var row_b: Array[Byte] = row.substring(6, row.length).getBytes("utf-8")
    //            //println(row.charAt(0) + " ==> " + row.charAt(1) + " ==> " + row.charAt(2) + " ==> " + row.charAt(3) + " ==> " + row.charAt(4))
    //
    //            //println(Integer.toBinaryString(Integer.parseInt(new String(row_b))))
    //          } catch {
    //            case e: Exception => println("throwed Exception")
    //          }
    //        }
    //
    //      }
    //    ))

    //启动streaming
    ssc.start() //启动流式计算
    ssc.awaitTermination() //等待直到计算终止


  }
}
