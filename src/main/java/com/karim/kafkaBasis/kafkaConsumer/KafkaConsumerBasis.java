package com.karim.kafkaBasis.kafkaConsumer;

import com.karim.kafkaBasis.cfg.LoadProperties;
import com.karim.kafkaBasis.define.CommonDefine;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by sblim
 * Date : 2021-11-01
 * Time : 오전 10:48
 */

public class KafkaConsumerBasis {

    private static final String TOPIC_NAME = "karim-topic";

    public static void main(String[] args) {

        LoadProperties.loadProperties();

        Properties props = new Properties();

        // kafka server host 및 port 설정
        props.put("bootstrap.servers", CommonDefine.KAFKA_IP);
        props.put("group.id", "karim-group-id-1"); // group-id 설정
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // key deserializer
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // value deserializer

        // consumer 생성
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // topic 설정
        consumer.subscribe(Collections.singletonList(TOPIC_NAME));

        try {
            while (true) {
                // 계속 loop를 돌면서 producer의 message를 띄운다.
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records)
                    System.out.println(record.value());
            }
        } catch (Exception e) {
        } finally {
            consumer.close();
        }
    }
}
