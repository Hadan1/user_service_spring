package user_service_spring.aston.service;

import org.springframework.stereotype.Service;
import user_service_spring.aston.dto.UserDto;
import user_service_spring.aston.dto.mapping.UserMapping;
import user_service_spring.aston.notification.service.UserKafkaProducer;
import user_service_spring.aston.repository.User;
import user_service_spring.aston.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapping userMapping;
    private final UserKafkaProducer userKafkaProducer;

    public UserService(UserRepository userRepository, UserMapping userMapping, UserKafkaProducer userKafkaProducer) {
        this.userRepository = userRepository;
        this.userMapping = userMapping;
        this.userKafkaProducer = userKafkaProducer;
    }

    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapping::toDto);
    }

    public User createUser(User user) {
        userKafkaProducer.sendUserToKafka(user);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}