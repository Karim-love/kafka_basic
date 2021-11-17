package com.karim.kafkaBasis.kafkaThreadBasis;

import com.karim.kafkaBasis.kafkaConsumer.KafkaClusterConsumerBasis;
import com.karim.kafkaBasis.kafkaProducer.KafkaClusterProducerBasis;

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
