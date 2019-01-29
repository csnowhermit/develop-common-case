package com.rxt.common.flink.realtime.utils

import java.util.Properties

import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

object KafkaUtils {

  import java.lang.management.ManagementFactory

  val mxBean = ManagementFactory.getOperatingSystemMXBean

  def main(args: Array[String]): Unit = {
    produceData()
  }

  def produceData(): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", "192.168.117.101:9092,192.168.117.102:9092,192.168.117.103:9092")
    props.put("acks", "-1")
    props.put("retries", "0")
    props.put("batch.size", "16384")
    props.put("linger.ms", "1")
    props.put("buffer.memory", "33554432")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    val totalMessageCount = 10

    for (i <- 0 until totalMessageCount) {
      val value = i % 5 + "," + System.currentTimeMillis + ",machine-1," + mxBean.getSystemLoadAverage
      println(s">>>>$i--$value")
      producer.send(new ProducerRecord[String, String]("flink-test", value), new Callback {
        override def onCompletion(recordMetadata: RecordMetadata, e: Exception): Unit = {
          if (e != null) {
            println("Failed to send message with exception " + e)
          }
        }
      })
      Thread.sleep(5000L)
    }
    producer.close()
  }

}
