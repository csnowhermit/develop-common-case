package com.rxt.common.spark.streaming.kafka

import com.alibaba.dcm.DnsCacheManipulator
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.{SparkConf, SparkContext, TaskContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}


/**
  * spark streaming 整合 kafka offset管理
  * http://spark.apache.org/docs/latest/streaming-kafka-0-10-integration.html
  */
object OffsetApp {
  def main(args: Array[String]): Unit = {

    //屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    //设置DNS
    DnsCacheManipulator.setDnsCache("DESKTOP-NVPG7MB", "127.0.0.1")
    DnsCacheManipulator.setDnsCache("master.hadoop", "192.168.117.101")
    DnsCacheManipulator.setDnsCache("node1.hadoop", "192.168.117.102")
    DnsCacheManipulator.setDnsCache("node2.hadoop", "192.168.117.103")

    val conf = new SparkConf().setMaster("local[2]").setAppName("OffsetApp")
    val ssc = new StreamingContext(conf, Seconds(10))

    //TODO... spark streaming 对接 kafka
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "192.168.117.101:9092,192.168.117.102:9092,192.168.117.103:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )
    val topics = Array("test")

    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    stream.foreachRDD(rdd => {
      if (!rdd.isEmpty()) {
        val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        rdd.foreachPartition(partition => {
          val o: OffsetRange = offsetRanges(TaskContext.get().partitionId())
          println(s"${o.topic} ${o.partition} ${o.fromOffset} ${o.untilOffset}")
        })

        // some time later, after outputs have completed
        // 将偏移量提交到kafka保存
        stream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
      }
    })

    ssc.start()
    ssc.awaitTermination()
  }
}
