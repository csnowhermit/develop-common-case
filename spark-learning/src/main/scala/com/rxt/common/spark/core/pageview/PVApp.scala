package com.rxt.common.spark.core.pageview

import java.net.URL

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * topN问题：
  * 网站PV：求用户访问量的top5
  */
object PVApp {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.WARN)

    val master = "local"
    val appName = "PVApp"
    val inputPath = "file:///opt/ELK/nginx-1.14.2/logs/access.log"

    val conf = new SparkConf().setMaster(master).setAppName(appName)
    val sc = new SparkContext(conf)

    val files = sc.textFile(inputPath)
    files.map(_.split("\t"))
      .filter(x => (x(1).startsWith("http") && x(1).endsWith(".html")))
      .map(x => (new URL(x(1)).getPath, 1L))
      .reduceByKey(_ + _)
      .map(x => (x._2, x._1))
      .sortByKey(false)
      .map(x => (x._2, x._1))
      .take(5)
      .foreach(println)

    sc.stop()

  }
}
