package com.rxt.common.spark.streaming.kafka

import kafka.serializer.StringDecoder
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


/**
  * 使用 KafkaUtils.createDirectStream() 方式接Kafka数据
  * 注意：使用spark Streaming接Kafka数据时，sbt scala的版本号要与spark支持的scala版本号一致，
  * 否则报错：main函数找不到
  */
object AcceptDirectKafka {
  def main(args: Array[String]): Unit = {

    //屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val conf = new SparkConf().setMaster("local[2]").setAppName("acceptKafka")
    //val ssc = new StreamingContext(conf, Duration(5000))
    val ssc = new StreamingContext(conf, Seconds(1))

    ssc.checkpoint(".") //因为使用到了updateStateByKey，所以必须设置checkpoint
    val topic = Set("test") //需要消费的Kafka数据的topic
    val kafkaParam = Map[String, String](
      "metadata.broker.list" -> "192.168.117.101:2181,192.168.117.102:2181,192.168.117.103:2181",
      "auto.offset.reset" -> "smallest")

    val stream: InputDStream[(String, String)] = createStream(ssc, kafkaParam, topic)

    stream.print()

    //    stream.map(_._2) //取出value
    //      .flatMap(_.split(" ")) //将字符串使用空格分隔
    //      .map(r => (r, 1)) //每个单词映射成一个Pair
    //      .updateStateByKey[Int](updateFunc) //用当前batch的数据区更新已有的数据
    //      .print() //默认为打印呢前10个数据

    //    stream.map(_._2).print()


    //启动streaming
    ssc.start() //启动流式计算
    ssc.awaitTermination() //等待直到计算终止
  }

  /**
    * 创建一个从Kafka获取数据的流
    *
    * @param ssc        Spark Streaming上下文
    * @param kafkaParam Kafka相关配置
    * @param topic      需要消费的topic的集合
    */
  def createStream(ssc: StreamingContext, kafkaParam: Map[String, String], topic: Set[String]) = {
    KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParam, topic)
  }

  val updateFunc = (currentValues: Seq[Int], preValue: Option[Int]) => {
    val curr = currentValues.sum
    val pre = preValue.getOrElse(0)
    Some(curr + pre)
  }

}
