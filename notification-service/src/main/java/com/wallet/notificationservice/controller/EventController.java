package com.wallet.notificationservice.controller;

import com.wallet.notificationservice.dto.Customer;
import com.wallet.notificationservice.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String  message) {
        try {
            for(int i=0;i<10000;i++) {
                kafkaMessagePublisher.sendMessage(message+ " : " +i);
            }
            kafkaMessagePublisher.sendMessage(message);
            return ResponseEntity.ok("message published successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/publish")
    public ResponseEntity<?> publishMessage(@RequestBody Customer customer) {
        try {
            kafkaMessagePublisher.sendEventsToTopic(customer);
            return ResponseEntity.ok("message published successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
