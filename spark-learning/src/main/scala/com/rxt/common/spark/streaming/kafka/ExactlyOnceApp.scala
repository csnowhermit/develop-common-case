package com.rxt.common.spark.streaming.kafka

import com.alibaba.dcm.DnsCacheManipulator
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 流式数据处理：只消费一次
  */
object ExactlyOnceApp {
  def main(args: Array[String]): Unit = {

    //屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    //设置DNS
    DnsCacheManipulator.setDnsCache("DESKTOP-NVPG7MB", "127.0.0.1")
    DnsCacheManipulator.setDnsCache("master.hadoop", "192.168.117.101")
    DnsCacheManipulator.setDnsCache("node1.hadoop", "192.168.117.102")
    DnsCacheManipulator.setDnsCache("node2.hadoop", "192.168.117.103")

    val conf = new SparkConf().setMaster("local[2]").setAppName("ExactlyOnceApp")
    val ssc = new StreamingContext(conf, Seconds(10))

    val topics = Array("test")
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "192.168.117.101:9092,192.168.117.102:9092,192.168.117.103:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "test.group",
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    stream.foreachRDD(rdd => {
      println(rdd.count())
    })

    ssc.start()
    ssc.awaitTermination()
  }

}
