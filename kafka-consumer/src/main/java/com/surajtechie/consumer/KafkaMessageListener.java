package com.surajtechie.consumer;

import com.surajtechie.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;


@Service
public class KafkaMessageListener {
    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);
    //commented for test serialize and deserialize concept
   /*@KafkaListener(topics = "customer", topicPartitions = {@TopicPartition(topic="customer", partitions = {"2"})}, groupId = "st-groups-1")
    public void consume1(String message) {
       log.info("consumer 1 consume the message {} ", message);
    }
*/
    /* @KafkaListener(topics = "kafkaapplication-topicdemo", groupId = "st-groups")
    public void consume2(String message) {
        log.info("consumer 2 consume the message {} ", message);
    }
    @KafkaListener(topics = "kafkaapplication-topicdemo", groupId = "st-groups")
    public void consume3(String message) {
        log.info("consumer 3 consume the message {} ", message);
    }
    @KafkaListener(topics = "kafkaapplication-topicdemo", groupId = "st-groups")
    public void consume4(String message) {
        log.info("consumer 4 consume the message {} ", message);
    }*/

    @KafkaListener(topics = "customer-producer", groupId = "st-group")
    public void consume1(Customer customer) {
        log.info("consumer 1 consume the message {} ", customer.toString());
    }
}