package com.rxt.common.spark.mllib

import org.apache.spark.{SparkContext,SparkConf}
import org.apache.spark.mllib.classification.NaiveBayesModel
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint

/**
  * spark mllib：贝叶斯分类
  */
object NaiveBayes {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("NaiveBayes")
    val sc = new SparkContext(conf)
    val path = "../data/sample_football_weather.txt"
    val data = sc.textFile(path)
    val parsedData =data.map {
      line =>
        val parts =line.split(',')
        LabeledPoint(parts(0).toDouble,Vectors.dense(parts(1).split(' ').map(_.toDouble)))
    }
    //样本划分train和test数据样本60%用于train
    val splits = parsedData.randomSplit(Array(0.6,0.4),seed = 11L)
    val training =splits(0)
    val test =splits(1)
    //获得训练模型,第一个参数为数据，第二个参数为平滑参数，默认为1，可改变
    val model =NaiveBayes.train(training,lambda = 1.0)
    //对测试样本进行测试
    //对模型进行准确度分析
    val predictionAndLabel= test.map(p => (model.predict(p.features),p.label))
    val accuracy =1.0 *predictionAndLabel.filter(x => x._1 == x._2).count() / test.count()
    //打印一个预测值
    println("NaiveBayes精度----->" + accuracy)
    //我们这里特地打印一个预测值：假如一天是   晴天(0)凉(2)高(0)高(1) 踢球与否
    println("假如一天是   晴天(0)凉(2)高(0)高(1) 踢球与否:" + model.predict(Vectors.dense(0.0,2.0,0.0,1.0)))

    //保存model
    val ModelPath = "../model/NaiveBayes_model.obj"
    model.save(sc,ModelPath)
    //val testmodel = NaiveBayesModel.load(sc,ModelPath)
  }
}
