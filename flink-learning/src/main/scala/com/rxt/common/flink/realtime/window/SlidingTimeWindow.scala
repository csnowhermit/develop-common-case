package com.rxt.common.flink.realtime.window

import com.rxt.common.flink.realtime.bean.CarWc
import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * 滑动时间窗口
  */
object SlidingTimeWindow {
  def main(args: Array[String]): Unit = {
    val params = ParameterTool.fromArgs(args)
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.getConfig.setGlobalJobParameters(params)
    env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime)
    env.setParallelism(1)
    val ds = env.socketTextStream("127.0.0.1", 7777)
      .map(r => {
        val rs = r.split(",")
        CarWc(rs(0).trim.toInt, rs(1).trim.toLong)
      })

    ds.keyBy(0) //滑动时间窗口
      .window(SlidingProcessingTimeWindows.of(Time.seconds(10), Time.seconds(5))) //组内：每隔5s，统计10s的数据
      .sum(1)
      .print()

    env.execute(getClass.getSimpleName)
  }

  class AverageAggregate extends AggregateFunction[CarWc, (Long, Long), Double] {

    override def createAccumulator(): (Long, Long) = (0L, 0L)

    override def add(value: CarWc, accumulator: (Long, Long)): (Long, Long) =
      (accumulator._1 + value.speed, accumulator._2 + 1L)

    override def getResult(accumulator: (Long, Long)): Double =
      accumulator._1 / accumulator._2

    override def merge(a: (Long, Long), b: (Long, Long)): (Long, Long) =
      (a._1 + b._1, a._2 + b._2)
  }

}
