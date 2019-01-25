package com.rxt.common.storm.app;

import com.rxt.common.storm.bolt.PrintBolt;
import com.rxt.common.storm.bolt.SplitSentenceBolt;
import com.rxt.common.storm.bolt.WordCountBolt;
import com.rxt.common.storm.spout.RandomSentenceSpout;
import com.rxt.common.storm.utils.PropertiesUtils;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class WordCountTopology {
    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        if (args.length == 0) {
            System.out.println("Usage: <runEnv>");
            System.exit(0);
        }

        String runEnv = args[0];

        //1.构建TopologyBuilder
        TopologyBuilder topologyBuilder = new TopologyBuilder();

        topologyBuilder.setSpout("spout", new RandomSentenceSpout(), 2);    //构造随机spout，并发数为2
//        topologyBuilder.setSpout("spout", new KafkaSpout(buildKafkaConfig()), 1);    //构造kafka spout

        topologyBuilder.setBolt("split", new SplitSentenceBolt(), 2)
                .shuffleGrouping("spout");    //并发度为2，随机方式发到task
        topologyBuilder.setBolt("count", new WordCountBolt(), 4)
                .fieldsGrouping("split", new Fields("word"));    //并发度为4，按key分发到task
        topologyBuilder.setBolt("print", new PrintBolt(), 1)
                .shuffleGrouping("count");    //将聚合结果打印出来

        //2.config
        Config config = new Config();
        config.setDebug(false);
        config.setNumAckers(1);
        config.setMaxSpoutPending(1000);
        config.setNumWorkers(2);
        config.setTopologyWorkerMaxHeapSize(1024);
        config.put("show.topNum", PropertiesUtils.getPropertyValue("show.topNum"));

        //3.提交任务——两种模式：本地模式和集群模式
        if ("local".equals(runEnv)) {
            LocalCluster localCluster = new LocalCluster();
            localCluster.submitTopology("wordcount_topology", config, topologyBuilder.createTopology());
        } else if ("prod".equals(runEnv)) {
            StormSubmitter.submitTopology("wordcount_topology", config, topologyBuilder.createTopology());
        } else {
            System.err.println(">>>非法环境变量值");
        }
    }

    private static SpoutConfig buildKafkaConfig() {
        BrokerHosts zkrHosts = new ZkHosts(PropertiesUtils.getPropertyValue("kafka.zkhosts"));
        final String kafkaTopic = PropertiesUtils.getPropertyValue("kafka.topic");
        final String zkRoot = "/storm-app" + kafkaTopic;
        final String spoutId = kafkaTopic;

        SpoutConfig spoutConfig = new SpoutConfig(zkrHosts, kafkaTopic, zkRoot, spoutId);
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());

        return spoutConfig;
    }
}
