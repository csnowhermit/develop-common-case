package com.rxt.common.storm.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.Random;

/**
 * bolt：切分句子，extends BaseRichBolt
 */
public class SplitSentenceBolt extends BaseRichBolt {

    OutputCollector _collector;
    Random random = new Random();

    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        _collector = collector;
    }

    @Override
    public void execute(Tuple input) {
        String sentence = input.getString(0);
        for (String word : sentence.split("\\s+")) {
            _collector.emit(input, new Values(word, 1));    //建立anchor树
        }

        if (random.nextInt(10) % 3 == 0) {
            _collector.fail(input);    //模拟失败的情况
        } else {
            _collector.ack(input);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word", "cnt"));
    }
}
