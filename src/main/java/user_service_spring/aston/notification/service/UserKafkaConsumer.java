package user_service_spring.aston.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import user_service_spring.aston.repository.User;

@Service
public class UserKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(UserKafkaConsumer.class);

    @KafkaListener(topics = "user_service_topic")
    public void consumeUser(User user) {
        log.info("Received user {}", user);
    }
}
