package com.karim.kafkaBasis.kafkaConsumer;

import com.karim.kafkaBasis.kafkaThreadBasis.instance.kafkaQueue;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by sblim
 * Date : 2021-11-16
 * Time : 오후 2:57
 */
public class KafkaClusterConsumerBasis extends Thread{
    private static final String KAFKA_CLUSTER_IP = "192.168.124.220:9092,192.168.124.221:9092,192.168.124.250:9092";
    private static final String TOPIC_NAME = "karim-receive-topic";

    public void run(){
        Properties props = new Properties();

        // kafka server host 및 port 설정
        props.put("bootstrap.servers", KAFKA_CLUSTER_IP);
        props.put("group.id", "karim-group-id-1"); // group-id 설정
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // key deserializer
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // value deserializer

        // consumer 생성
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // topic 설정
        consumer.subscribe(Collections.singletonList(TOPIC_NAME));

        ConsumerRecords<String, String> records ;

        try {
            while (true) {
                // 계속 loop를 돌면서 producer의 message를 띄운다.
                if ((records = consumer.poll(100)) != null) {
                    if (!records.isEmpty()) {
                        // 큐에 데이터를 넣는다
                        for (ConsumerRecord<String, String> record : records)
                            kafkaQueue.getInstance().offer(record.value());
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            consumer.close();
        }
    }
}
