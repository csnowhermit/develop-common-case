/**
 * FileName: KafkaProducerDemo
 * Author:   Ren Xiaotian
 * Date:     2018/11/11 11:02
 */

package com.rxt.common.topic;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Kafka 生产者
 * sh ./kafka-console-producer.sh --broker-list 192.168.117.101:9092,192.168.117.102:9092,192.168.117.103:9092 --topic test
 */
public class KafkaProducerDemo extends Thread {

    private final KafkaProducer<Integer, String> producer;
    private final String topic;
    private final boolean isAsync;    //是否异步发送

    public KafkaProducerDemo(String topic, boolean isAsync) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.117.101:9092,192.168.117.102:9092,192.168.117.103:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducerDemo");
        properties.put(ProducerConfig.ACKS_CONFIG, "-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<Integer, String>(properties);
        this.topic = topic;
        this.isAsync = isAsync;
    }

    @Override
    public void run() {
        int num = 0;
        while (num < 50) {
            final String message = "message_" + num;
            System.out.println("begin send message: " + message);
            if (isAsync) {    //异步发送
                producer.send(new ProducerRecord<Integer, String>(topic, message), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if (message != null) {
                            System.out.println("async-offest: " + metadata.offset() + " --> partition: " + metadata.partition());
                        }
                    }
                });
            } else {    //同步发送
                try {
                    RecordMetadata recordMetadata = producer.send(new ProducerRecord<Integer, String>(topic, message)).get();
                    System.out.println("sync-offest: " + recordMetadata.offset() + " --> partition: " + recordMetadata.partition());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num++;
        }
    }

    public static void main(String[] args) {
        new KafkaProducerDemo("test", true).start();
    }
}
