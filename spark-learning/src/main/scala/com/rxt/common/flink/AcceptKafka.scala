package com.rxt.common.flink

/**
  * flink 接 kafka 消息
  */
object AcceptKafka {
  def main(args: Array[String]): Unit = {


//    val env = StreamExecutionEnvironment.getExecutionEnvironment
//    env.getConfig.disableSysoutLogging
//    env.getConfig.setRestartStrategy(RestartStrategies.fixedDelayRestart(4, 10000))
//    // create a checkpoint every 5 seconds
//    env.enableCheckpointing(5000)
//    // make parameters available in the web interface
//    env.getConfig.setGlobalJobParameters(params)
//
//    // create a Kafka streaming source consumer for Kafka 0.10.x
//    val kafkaConsumer = new FlinkKafkaConsumer010(
//      params.getRequired("input-topic"),
//      new SimpleStringSchema,
//      params.getProperties)
//
//    val messageStream = env
//      .addSource(kafkaConsumer)
//      .map(in => prefix + in)
//
//    // create a Kafka producer for Kafka 0.10.x
//    val kafkaProducer = new FlinkKafkaProducer010(
//      params.getRequired("output-topic"),
//      new SimpleStringSchema,
//      params.getProperties)
//
//    // write data into Kafka
//    messageStream.addSink(kafkaProducer)
//
//    env.execute("Kafka 0.10 Example")
//    ---------------------
//    作者：Spark高级玩法
//    来源：CSDN
//    原文：https://blog.csdn.net/rlnLo2pNEfx9c/article/details/81024534
//    版权声明：本文为博主原创文章，转载请附上博文链接！
  }
}
