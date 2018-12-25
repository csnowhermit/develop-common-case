package com.rxt.common.spark.core

import java.net.URL

import org.apache.commons.cli.{GnuParser, Options}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 计算网站PV：页面访问量，页面打开一次或刷新一次，PV+1
  */
object ComputePV {

  /**
    * 计算PV
    *
    * @param file
    * @return
    */
  def computePV(textRDD: RDD[String]): RDD[(String, Long)] = {
    val splitTextFileRDD = textRDD.map(_.split("\t"))
    val result = splitTextFileRDD.filter(log => log(1).startsWith("http") && log(1).endsWith(".html"))
      .map(log => ((new URL(log(1))).getPath, 1L))
      .reduceByKey(_ + _)

    result
  }

  /**
    * 将计算结果保存至HDFS
    *
    * @param result
    * @param hdfsPath
    */
  def save2HDFS(result: RDD[(String, Long)], hdfsPath: String): Unit = {
    result.saveAsTextFile(hdfsPath)
  }

  /**
    * 将计算结果保存至MySQL
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
//      .write.mode(SaveMode.Append)    //以追加方式写
//      .jdbc("jdbc:mysql://localhost:3306/test?useSSL=false", "web_pv", props)
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
    //    //如果指定了存储模式为HDFS，但未指定存储目录，则抛异常
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

    //    file.map(_.split("\t"))
    //      .filter(log => log(1).startsWith("http") && log(1).endsWith(".html"))
    //      .map(log => (new URL(log(1)).getPath, 1L))
    //      .reduceByKey(_ + _)
    //      .collect()
    //      .foreach(println)


    val result = computePV(file) //计算PV
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
