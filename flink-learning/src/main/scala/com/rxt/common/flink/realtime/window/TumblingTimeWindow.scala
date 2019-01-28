package com.rxt.common.flink.realtime.window

import com.rxt.common.flink.realtime.bean.CarWc
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time

/**
  * 滚动时间窗口
  */
object TumblingTimeWindow {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    val ds = env.socketTextStream("127.0.0.1", 7777)
      .map(line => {
        val tokens = line.split(",")
        tokens
      })
      .filter(_.length == 3)
      .map(tokens => CarWc(tokens(0).trim.toInt, tokens(1).trim.toInt, tokens(2).trim.toLong))
//      .assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks[CarWc] {
//
//        var maxTs = Long.MinValue
//
//        override def getCurrentWatermark: Watermark = {
//          new Watermark(maxTs - 1)
//        }
//
//        override def extractTimestamp(element: CarWc, previousElementTimestamp: Long): Long = {
//          maxTs = Math.max(element.ts, maxTs)
//          print(element.ts)
//          element.ts
//        }
//      })
      .keyBy(_.sensorId)
      .window(TumblingProcessingTimeWindows.of(Time.seconds(10)))
      .sum("speed")
      .print()

    env.execute(getClass.getSimpleName)
  }
}
