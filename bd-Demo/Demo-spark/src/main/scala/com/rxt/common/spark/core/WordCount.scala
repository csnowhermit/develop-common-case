package com.rxt.common.spark.core

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {

    val master = "local"
    val appName = "wordcount"
    val inputPath = "file:///test.log"

    //屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val conf = new SparkConf().setMaster(master).setAppName(appName)
    val sc = new SparkContext(conf)

    val file = sc.textFile(inputPath)

    val words = file.flatMap(_.split(",")).map(word => (word, 1)).reduceByKey(_ + _)
    words.collect().foreach(println)

    sc.stop()

  }
}
