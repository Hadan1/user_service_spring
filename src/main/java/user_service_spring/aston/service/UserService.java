package user_service_spring.aston.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import user_service_spring.aston.dto.UserDto;
import user_service_spring.aston.dto.mapping.UserMapping;
import user_service_spring.aston.kafka.UserKafkaProducer;
import user_service_spring.aston.entity.User;
import user_service_spring.aston.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapping userMapping;
    private final UserKafkaProducer userKafkaProducer;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, UserMapping userMapping, UserKafkaProducer userKafkaProducer) {
        this.userRepository = userRepository;
        this.userMapping = userMapping;
        this.userKafkaProducer = userKafkaProducer;
    }

    public Optional<UserDto> getUserById(Long id) {
        try {
            if (id == null || id <= 0) {
                logger.warn("Invalid user id: {}", id);
                return Optional.empty();
            }
            return userRepository.findById(id)
                    .map(userMapping::toDto);
        } catch (Exception e) {
            logger.error("Error getting user by id: {}", id, e);
            return Optional.empty();
        }
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }


    public User updateUser(Long id, User userDetails) {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("Invalid user id");
            }
            if (userDetails == null) {
                throw new IllegalArgumentException("User details cannot be null");
            }

            User user = userRepository.findById(id).orElseThrow(() ->
                    new RuntimeException("User with id " + id + " not found"));

            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());

            return userRepository.save(user);
        } catch (Exception e) {
            logger.error("Error updating user with id {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to update user", e);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}