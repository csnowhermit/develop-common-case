package com.rxt.common.spark.core.fliterDemo

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 修正词组：有些省份缺少省字的，比如 “广西玉林…”，要修改成“广西省玉林…”，规范数据，用于后续关联。
  */
object RevisePhrase {
  def main(args: Array[String]): Unit = {
    val master = "local"
    val appName = "RevisePhrase"
    val inputPath = "file:///RevisePhrase.txt"

    //屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val conf = new SparkConf().setMaster(master).setAppName(appName)
    val sc = new SparkContext(conf)

    val file = sc.textFile(inputPath)

    val words = file.map(line =>
      if (line.contains("省")) {
        line
      } else {
        if (line.contains("黑龙江")) {
          val province = line.substring(0, 3)
          val city = line.substring(3)
          province + "省" + city
        } else {
          val province = line.substring(0, 2)
          val city = line.substring(2)
          province + "省" + city
        }
      }
    )
    words.collect().foreach(println)

    sc.stop()

  }
}
