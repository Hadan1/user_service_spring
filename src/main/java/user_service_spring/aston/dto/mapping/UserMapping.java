package user_service_spring.aston.dto.mapping;

import org.mapstruct.Mapper;
import user_service_spring.aston.dto.UserDto;
import user_service_spring.aston.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapping {
    UserDto toDto (User user);
}
