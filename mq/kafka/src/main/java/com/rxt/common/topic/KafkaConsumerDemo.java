/**
 * FileName: KafkaConsumerDemo
 * Author:   Ren Xiaotian
 * Date:     2018/11/11 12:11
 */

package com.rxt.common.topic;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * kafka 消费者
 * sh ./kafka-console-consumer.sh --bootstrap-server 192.168.117.101:9092,192.168.117.102:9092,192.168.117.103:9092 --topic test --from-beginning
 */
public class KafkaConsumerDemo extends Thread {
    private final KafkaConsumer consumer;

    public KafkaConsumerDemo(String topic) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.117.101:9092,192.168.117.102:9092,192.168.117.103:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaConsumerDemo");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        this.consumer = new KafkaConsumer(properties);
        this.consumer.subscribe(Collections.singletonList(topic));    //消费者订阅该topic
    }

    @Override
    public void run() {
        while (true) {
            ConsumerRecords<Integer, String> consumerRecords = consumer.poll(1000);
            for (ConsumerRecord record : consumerRecords) {
                System.out.println("receive: " + record.value());
            }
        }
    }

    public static void main(String[] args) {
        new KafkaConsumerDemo("test").start();
    }


}
