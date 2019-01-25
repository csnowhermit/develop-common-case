package com.rxt.common.storm.bolt;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Bolt：拆分句子，extends BaseBasicBolt
 */
public class SplitSentenceBolt2 extends BaseBasicBolt {

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String line = input.getString(0);
        String[] arrWords = line.split("\\s+");
        for (String word : arrWords) {
            collector.emit(new Values(word, 1));
        }
    }

    /**
     * 声明输出的field
     *
     * @param declarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word", "cnt"));
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }
}
