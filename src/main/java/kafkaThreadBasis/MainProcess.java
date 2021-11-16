package kafkaThreadBasis;

import kafkaConsumer.KafkaClusterConsumerBasis;
import kafkaProducer.KafkaClusterProducerBasis;

/**
 * Created by sblim
 * Date : 2021-11-16
 * Time : 오후 2:55
 */
public class MainProcess {
    public static void main(String[] args) {

        KafkaClusterConsumerBasis kafkaClusterConsumerBasis = new KafkaClusterConsumerBasis();
        KafkaClusterProducerBasis kafkaClusterProducerBasis = new KafkaClusterProducerBasis();

        kafkaClusterConsumerBasis.start();
        kafkaClusterProducerBasis.start();
    }
}
