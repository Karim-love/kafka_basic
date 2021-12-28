package com.karim.kafkaBasis.kafkaStreams;

import com.karim.kafkaBasis.cfg.LoadProperties;
import com.karim.kafkaBasis.define.CommonDefine;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

/**
 * Created by sblim
 * Date : 2021-12-20
 * Time : 오후 4:01
 */
public class KafkaStreamsBasis {

    private static final String RECEIVE_TOPIC_NAME = "karim-rcv-topic";
    private static final String SEND_TOPIC_NAME = "karim-send-topic";

    public static void main(final String[] args) throws Exception {

        LoadProperties.loadProperties();

        Properties props = new Properties();
        // 카프카 스트림즈를 유일하게 구분할 ID값
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
        // 스트림즈에 접근할 카프카 broker 정보
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, CommonDefine.KAFKA_IP);
        // 데이터를 어떤 형식으로 Read/Write 할지 성정 (키/값의 데이터 타입을 지정)
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        // 스트림 토폴로지를 정의하기 위한 빌더
        StreamsBuilder builder = new StreamsBuilder();

        // 소스 프로세서 동작 -> RECEIVE_TOPIC_NAME 토픽으로 부터 KStream 객체를 만든다.
        KStream<String, String> stringLength5Over = builder.stream(RECEIVE_TOPIC_NAME);

        // 스트림 프로세서 동작 ->
        // RECEIVE_TOPIC_NAME 토픽에서 가져온 데이터 중
        // length 가 5를 넘는 경우의 값만 남도록 필터링 하여 KStream 객체를 새롭게 생성
        KStream<String, String> filterStream = stringLength5Over.filter(
                ((key, value) -> value.length() > 5)
        );

        // 싱크 프로세서 동작 ->
        // SEND_TOPIC_NAME 토픽으로 KStream 데이터를 전달한다.
        filterStream.to(SEND_TOPIC_NAME);

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }
}
