package com.rxt.common.spark.streaming.kafka

import com.alibaba.dcm.DnsCacheManipulator
import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 流式数据处理：只消费一次
  */
object ExactlyOnceApp {
  def main(args: Array[String]): Unit = {

    //设置DNS
    DnsCacheManipulator.setDnsCache("DESKTOP-NVPG7MB", "127.0.0.1")

    val conf = new SparkConf().setMaster("local[2]").setAppName("ExactlyOnceApp")
    val ssc = new StreamingContext(conf, Seconds(100))

    val topic = Set("test") //需要消费的Kafka数据的topic
    val kafkaParam = Map[String, String](
      "metadata.broker.list" -> "192.168.117.101:2181,192.168.117.102:2181,192.168.117.103:2181",
      "auto.offset.reset" -> "smallest")

    val stream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParam, topic)

    stream.foreachRDD(rdd => {
      if (rdd != null) {
        rdd.foreachPartition(partition => {
          partition.foreach(line => {
            println(line)
          })
        })
      }
    })

    ssc.start()
    ssc.awaitTermination()
  }

}
