package user_service_spring.aston.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    String name;
    Integer age;
    String email;
}
