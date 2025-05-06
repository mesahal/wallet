package com.wallet.consumer.consumer;

import com.wallet.consumer.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "wallet-topic-6",groupId = "wallet-group-3")
    public void consume1(Customer customer) {
        logger.info("Consumed1 message: {}", customer.toString());
    }
//
//    @KafkaListener(topics = "wallet-topic-5",groupId = "wallet-group-2")
//    public void consume2(String message) {
//        logger.info("Consumed2 message: {}", message);
//    }
//
//    @KafkaListener(topics = "wallet-topic-5",groupId = "wallet-group-2")
//    public void consume3(String message) {
//        logger.info("Consumed3 message: {}", message);
//    }
//
//    @KafkaListener(topics = "wallet-topic-5",groupId = "wallet-group-2")
//    public void consume4(String message) {
//        logger.info("Consumed4 message: {}", message);
//    }
//
//
//    @KafkaListener(topics = "wallet-topic-5",groupId = "wallet-group-2")
//    public void consume5(String message) {
//        logger.info("Consumed5 message: {}", message);
//    }
//
//    @KafkaListener(topics = "wallet-topic-5",groupId = "wallet-group-2")
//    public void consume6(String message) {
//        logger.info("Consumed6 message: {}", message);
//    }

}
