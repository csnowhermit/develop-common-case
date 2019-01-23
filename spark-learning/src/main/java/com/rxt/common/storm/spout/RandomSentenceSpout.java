package com.rxt.common.storm.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义spout：随机生成句子，做wordcount用
 */
public class RandomSentenceSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;
    private String[] sentences = null;
    private Random random;
    private Map<String, String> pending = new ConcurrentHashMap<>();    //spout中缓存的数据

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
        random = new Random(System.currentTimeMillis());
        sentences = new String[]{
                "gp is a good company",
                "And if the golden sun",
                "four score and seven years ago",
                "storm hadoop spark hbase",
                "Would make my whole world bright",
                "storm would have to be with you",
                "Pipe to subprocess seems to be broken No output read",
                "You make me feel so happy",
                "For the moon never beams without bringing me dreams Of the beautiful Annalbel Lee",
                " But small change will not affect the user experience",
                "You need to add the above config in storm installation if you want to run the code",
                "In the latest version, the class packages have been changed from", "Now I may wither into the truth",
                "That the wind came out of the cloud",
                "at backtype storm utils ShellProcess",
                "Of those who were older than we"
        };
    }

    @Override
    public void nextTuple() {
        Utils.sleep(1000);
        String sentence = sentences[random.nextInt(sentences.length)];
        String messageId = UUID.randomUUID().toString().replaceAll("-", "");
        pending.put(messageId, sentence);
        collector.emit(new Values(sentence), messageId);
    }

    @Override
    public void ack(Object msgId) {
        System.out.println("消息处理成功：" + msgId);
        System.out.println("删除缓存中的数据。。。");
        pending.remove(msgId);
    }

    @Override
    public void fail(Object msgId) {
        System.out.println("消息处理失败：" + msgId);
        System.out.println("重新发送失败的消息。。。");
        collector.emit(new Values(pending.get(msgId)), msgId);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("spout"));
    }
}
