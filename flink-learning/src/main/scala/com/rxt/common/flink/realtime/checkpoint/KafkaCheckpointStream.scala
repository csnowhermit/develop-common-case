package com.rxt.common.flink.realtime.checkpoint

import java.time.LocalDateTime
import java.util.Properties

import com.rxt.common.flink.realtime.bean.CheckPointType
import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.configuration.Configuration
import org.apache.flink.runtime.state.filesystem.FsStateBackend
import org.apache.flink.streaming.api.{CheckpointingMode, TimeCharacteristic}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011
import org.apache.flink.util.Collector

object KafkaCheckpointStream {
  def main(args: Array[String]): Unit = {
    if (args.length < 1) {
      println("Usage: <topic>")
      System.exit(0)
    }

    val topic = args(0)
    val checkpointInterval = 1000
    val checkpointMode = CheckpointingMode.EXACTLY_ONCE //只有一次

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    env.enableCheckpointing(checkpointInterval, checkpointMode)
    env.setStateBackend(new FsStateBackend("file:///tmp/checkpoints"))
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    val props = new Properties()
    props.setProperty("bootstrap.servers", "192.168.117.101:9092,192.168.117.102:9092,192.168.117.103:9092")
    props.setProperty("auto.offset.reset", "latest") //从最新的offset开始消费
    props.setProperty("group.id", "flink-kafka-test")
    props.setProperty("enable.auto.commit", "false") //不允许自动提交

    env.setParallelism(2) //并行度为2

    val consumer = new FlinkKafkaConsumer011[String](topic, new SimpleStringSchema(), props)

    //flink接kafka数据：作为kafka的消费者
    env.addSource(consumer)
      .map(k => {
        val tokens = k.split(",")
        CheckPointType(tokens(0), tokens(1).toLong, tokens(2).toDouble)
      })
      .assignAscendingTimestamps(_.ts)
      .keyBy(_.key)
      .flatMap(new StatefuelMapper)
      .print()

    env.execute(getClass.getSimpleName)
  }

  class StatefuelMapper extends RichFlatMapFunction[CheckPointType, String] {
    private var state: ValueState[Integer] = _

    override def flatMap(value: CheckPointType, out: Collector[String]): Unit = {
      var currentState = state.value()
      if (currentState == null) {
        currentState = 0
      }
      currentState += 1

      out.collect(String.format("%s: (%s, %d)", LocalDateTime.now(), value, currentState))
      state.update(currentState)
    }

    override def open(parameters: Configuration): Unit = {
      state = getRuntimeContext.getState(new ValueStateDescriptor[Integer]("checkpoint_example", classOf[Integer]))
    }

  }

}
