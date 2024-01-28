package com.akash.springboot.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer {

    private static final Logger LOGGER= LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String,String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String message){
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("javaTopic", message);
        future.whenComplete((result,ex)->{
            if(ex==null){
                LOGGER.info("Message '{}' sent to topic '{}' to partition {} with offset {}",message,result.getRecordMetadata().topic(),result.getRecordMetadata().partition(),result.getRecordMetadata().offset());
            }
            else{
                LOGGER.error("Unable to send message: {} due to : {}",message,ex.getMessage());
            }
        });

    }
}
