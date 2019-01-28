package com.rxt.common.spark.sql

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

/**
  * spark-sql：多个DataFrame一次性union
  */
object UnionDemo {
  def main(args: Array[String]): Unit = {
    val master = "local[4]"
    val appName = "Spark SQL Union Example"

    //屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    val spark = SparkSession
      .builder()
      .appName(appName)
      .master(master)
      .config("spark.some.config.option", "some-value")
      .getOrCreate()
//    import spark.implicits._
//    val df1 = spark.sparkContext.parallelize(1 to 4).map(i => (i,i*10)).toDF("id","x")
//    val df2 = spark.sparkContext.parallelize(1 to 4).map(i => (i,i*100)).toDF("id","y")
//    val df3 = spark.sparkContext.parallelize(1 to 4).map(i => (i,i*1000)).toDF("id","z")
//
//    val dfs = Seq(df1, df2, df3)
//    dfs.reduce(_ union _).show(1000)

  }
}
