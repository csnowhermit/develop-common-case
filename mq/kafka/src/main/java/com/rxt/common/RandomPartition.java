package com.rxt.common;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 自定义分区：随机分区
 */
public class RandomPartition implements Partitioner {

    private Random random = new Random();

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //获得分区列表
        List<PartitionInfo> partitionInfoList = cluster.partitionsForTopic(topic);
        int partitionNum = 0;

        if (key == null) {
            partitionNum = random.nextInt(partitionInfoList.size());    //随机分区
        } else {
            partitionNum = Math.abs((key.hashCode()) % partitionInfoList.size());
        }

        System.out.println("key --> " + key + " --> value --> " + value + " --> " + partitionNum);

        return partitionNum;    //返回指定发送的分区值
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }

}
