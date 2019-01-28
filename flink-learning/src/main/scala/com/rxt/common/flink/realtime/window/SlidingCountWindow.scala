package com.rxt.common.flink.realtime.window

import com.rxt.common.flink.realtime.bean.CarWc
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala._

/**
  * 滑动窗口计数
  */
object SlidingCountWindow {
  def main(args: Array[String]): Unit = {
    val params = ParameterTool.fromArgs(args)
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    env.getConfig.setGlobalJobParameters(params)
    env.setParallelism(1)

    env.socketTextStream("127.0.0.1", 7777)
      .map(line => {
        val tokens = line.split(",")
        CarWc(tokens(0).trim.toInt, tokens(1).trim.toLong)
      })
      .keyBy(0)    //按key分组，
      .countWindow(5, 3) //组内：每隔3格，统计5格的数据
      .sum(1)    //按第1列求和：下标从0开始
      .print()

    env.execute(getClass.getSimpleName)
  }
}
