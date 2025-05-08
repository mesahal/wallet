package com.wallet.notificationservice.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.wallet.notificationservice.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("wallet-topic-5", message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Send message =[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message =[" + message + "] due to " + ex.getMessage());
            }
        });
    }

    public void sendEventsToTopic(Customer customer) {
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("wallet-topic-6", customer);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Send message =[" + customer.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message =[" + customer.toString() + "] due to " + ex.getMessage());
                }
            });
        } catch (Exception e) {
            System.out.println("Unable to send message =[" + customer.toString() + "] due to " + e.getMessage());
        }

    }

}
