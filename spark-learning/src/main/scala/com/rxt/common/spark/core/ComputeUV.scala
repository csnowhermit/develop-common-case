package com.rxt.common.spark.core

import java.net.URL

import org.apache.commons.cli.{GnuParser, Options}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 计算网站UV：独立访客数，每增加一个独立访客，UV+1
  */
object ComputeUV {

  /**
    * 计算UV
    *
    * @param textRDD
    * @return
    */
  def computeUV(textRDD: RDD[String]): RDD[(String, Long)] = {
    val splitTextFileRDD = textRDD.map(_.split("\t"))
    val result = splitTextFileRDD.filter(log => log(1).startsWith("http") && log(1).endsWith(".html"))
      .map(log => ((new URL(log(1))).getPath, log(2)))
      .distinct()
      .map(urlWithCookies => (urlWithCookies._1, 1L))
      .reduceByKey(_ + _)

    result
  }

  /**
    * 保存结果至HDFS
    *
    * @param result
    * @param hdfsPath
    */
  def save2HDFS(result: RDD[(String, Long)], hdfsPath: String): Unit = {
    result.saveAsTextFile(hdfsPath)
  }

  /**
    * 保存结果至MySQL
    *
    * @param sc
    * @param result
    */
  def save2MySQL(sc: SparkContext, result: RDD[(String, Long)]): Unit = {
    val sqlContext = new SQLContext(sc)

    val props = new java.util.Properties()
    props.setProperty("driver", "com.mysql.jdbc.Driver")
    props.setProperty("user", "root")
    props.setProperty("password", "123456")

    import sqlContext.implicits._

//    result.toDF("url", "count")
//      .write.mode(SaveMode.Append)
//      .jdbc("jdbc:mysql://localhost:3306/test?useSSL=false", "web_uv", props)
  }


  def main(args: Array[String]): Unit = {
    //    val opts = new Options
    //    opts.addOption("input_path", true, "Input Path")
    //    opts.addOption("save_mode", true, "[DB/HDFS]")
    //    opts.addOption("output_path", true, "Output Path")
    //
    //    val parser = new GnuParser().parse(opts, args)
    //
    //    if (parser.hasOption("input_path") == false) {
    //      throw new IllegalArgumentException("Missing --input_path")
    //    }
    //
    //    if (parser.hasOption("save_mode") == false) {
    //      throw new IllegalArgumentException("Missing --save_mode")
    //    }
    //
    //    if (parser.getOptionValue("save_mode") == "HDFS") {
    //      if (parser.hasOption("output_path") == false) {
    //        throw new IllegalArgumentException("Missing --output_path")
    //      }
    //    }

    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.WARN)

    val master = "local"
    val appName = "ComputePV"
    val inputPath = "file:///opt/ELK/nginx-1.14.2/logs/access.log"

    val conf = new SparkConf().setMaster(master).setAppName(appName)
    val sc = new SparkContext(conf)

    sc.hadoopConfiguration.set("mapreduce.input.fileinputformat.input.dir.recursive", "true")

    val file = sc.textFile(inputPath)

    val result = computeUV(file)
    result.foreach(println)
    save2MySQL(sc, result)

    //    if (parser.getOptionValue("save_mode") == "HDFS") {
    //      save2HDFS(result, parser.getOptionValue("output_path"))
    //    } else {
    //      save2MySQL(sc, result)
    //    }

    sc.stop()

  }
}
