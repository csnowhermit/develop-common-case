package com.rxt.common.flink.realtime.common

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object WordCount {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val ds = env.socketTextStream("127.0.0.1", 7777)
      .flatMap(line => line.split("\\s+").map(word => (word, 1)))
      .keyBy(0)
      .timeWindow(Time.seconds(10))
      .sum(1)

    println(env.getExecutionPlan)
    env.execute(getClass.getSimpleName)

  }
}
