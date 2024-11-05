package com.surajtechie.controller;


import com.surajtechie.model.Customer;
import com.surajtechie.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("producer-app")
public class KafkaMessagePublisherController {

    @Autowired
    private KafkaMessagePublisher messagePublisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<String> publishMessage(@PathVariable("message") String message) {
        try {
            for (int i = 0; i < 100; i++) {
                messagePublisher.sendMessagetoTopic(message + "-" + i);
            }
            return ResponseEntity.ok("message publish successfully..!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @PostMapping("/sendCustomer")
    public ResponseEntity<String> publishCustomerObject(@RequestBody Customer customer) {
        try {
            messagePublisher.sendSerializeCustomer(customer);
            return ResponseEntity.ok("message publish successfully..!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }
}
