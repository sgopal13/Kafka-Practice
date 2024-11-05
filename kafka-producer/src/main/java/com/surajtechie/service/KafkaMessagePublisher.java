package com.surajtechie.service;

import com.surajtechie.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessagetoTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = template.send("customer",2, null ,message);
        future.whenComplete((result,ex)->{
            if(ex ==null){
                System.out.println("Message Sent "+result.getRecordMetadata().offset());
            }else{
                System.out.println("Exception while sending message "+ex.getMessage());
            }
        });
    }


    public void sendSerializeCustomer(Customer customer){
        CompletableFuture<SendResult<String, Object>> future = template.send("customer-producer", customer);
        future.whenComplete((result,ex)->{
            if(ex ==null){
                System.out.println("Message Sent "+result.getRecordMetadata().offset());
            }else{
                System.out.println("Exception while sending message "+ex.getMessage());
            }
        });
    }
}
