package com.karim.kafkaBasis.kafkaProducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by sblim
 * Date : 2021-10-29
 * Time : 오후 2:54
 */
public class KafkaProducerBasis {

    private static final String KAFKA_SINGLE_IP = "192.168.124.238:1227";
    private static final String TOPIC_NAME = "karim-topic";

    public static void main(String[] args) {

        String sendMessage = "Karim velog gooooooooood";

        Properties configs = new Properties();
        configs.put("bootstrap.servers", KAFKA_SINGLE_IP); // kafka host 및 server 설정
        configs.put("acks", "1");                         // 자신이 보낸 메시지에 대해 카프카로부터 확인을 기다리지 않는다.
        configs.put("block.on.buffer.full", "true");        // 서버로 보낼 레코드를 버퍼링 할 때 사용할 수 있는 전체 메모리의 바이트수
        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");   // serialize 설정
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // serialize 설정
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);

        // kafka로 메세지 개시
        producer.send(new ProducerRecord<String, String>(TOPIC_NAME, sendMessage));

        producer.flush();
        // producer 닫기
        producer.close();
    }
}

