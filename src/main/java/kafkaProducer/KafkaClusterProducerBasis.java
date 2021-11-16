package kafkaProducer;

import com.google.gson.JsonObject;
import kafkaThreadBasis.instance.kafkaQueue;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by sblim
 * Date : 2021-11-16
 * Time : 오후 2:59
 */
public class KafkaClusterProducerBasis extends Thread{

    private static final String KAFKA_CLUSTER_IP = "192.168.124.220:9092,192.168.124.221:9092,192.168.124.250:9092";
    private static final String TOPIC_NAME = "karim-send-topic";

    @Override
    public void run() {

        Properties configs = new Properties();
        configs.put("bootstrap.servers", KAFKA_CLUSTER_IP); // kafka host 및 server 설정
        configs.put("acks", "1");                         // 자신이 보낸 메시지에 대해 카프카로부터 확인을 기다리지 않는다.
        configs.put("block.on.buffer.full", "true");        // 서버로 보낼 레코드를 버퍼링 할 때 사용할 수 있는 전체 메모리의 바이트수
        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");   // serialize 설정
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // serialize 설정
        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        JsonObject outputJsonData = new JsonObject();
        String data;

        while (true){
            try {
                // KafkaQueue에 있는 데이터가 있으면,
                if ((data = kafkaQueue.getInstance().poll(500, TimeUnit.MILLISECONDS)) != null){

                    outputJsonData.addProperty("plain", data);

                    // kafka로 메세지 개시
                    producer.send(new ProducerRecord<>(TOPIC_NAME, outputJsonData.toString()));
                }
            }catch (Exception e){
                if (producer != null) {
                    producer.close();
                    producer = null;
                }
            }
        }
    }
}