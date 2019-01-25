package com.rxt.common.storm.bolt;

import com.rxt.common.storm.utils.MapSortUtil;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WordCountBolt：上一个bolt为SplitSentenceBolt或SplitSentenceBolt2
 */
public class WordCountBolt extends BaseBasicBolt {
    private Map<String, Long> counters = new ConcurrentHashMap<>();
    private long topNum = 10;

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        if (stormConf.containsKey("show.topNum")) {
            topNum = Long.valueOf(stormConf.get("show.topNum").toString());
        }
        super.prepare(stormConf, context);
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        //根据上一bolt输出的Fields拿到数据
        String word = input.getStringByField("word");
        Integer cnt = input.getIntegerByField("cnt");

        if (counters.containsKey(word)) {
            Long count = counters.get(word);
            counters.put(word, count + cnt);
        } else {
            counters.put(word, Long.valueOf(topNum));
        }

        long length = 0;

        counters = MapSortUtil.sortByValue(counters);    //利用自定义工具类对map进行按值排序

        if (topNum < counters.keySet().size()) {
            length = topNum;
        } else {
            length = counters.keySet().size();
        }

        int count = 0;

        //准备数据：按值排序之后打印前topNum个
        for (String key : counters.keySet()) {
            if (count >= length) {
                break;
            }

            if (count == 0) {
                word = "[" + key + ":" + counters.get(key) + "]";
            } else {
                word = word + ", [" + key + ":" + counters.get(key) + "]";
            }
            count++;
        }

        String result = "The first " + topNum + ": " + word;
        collector.emit(new Values(result));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("output_word_stat"));
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }
}
