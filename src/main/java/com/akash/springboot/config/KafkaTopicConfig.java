package com.akash.springboot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name("javaTopic")
                .build();
    }

    @Bean
    public NewTopic createJsonTopic(){
        return TopicBuilder.name("javaTopic_json")
                .build();
    }
}
