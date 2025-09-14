package user_service_spring.aston.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import user_service_spring.aston.entity.User;

@Service
public class UserKafkaProducer {

    private final KafkaTemplate<String, User> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(UserKafkaProducer.class);

    public UserKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserToKafka(User user) {
        kafkaTemplate.send("user_service_topic", user);
        log.info("user sent to kafka");
    }
}
