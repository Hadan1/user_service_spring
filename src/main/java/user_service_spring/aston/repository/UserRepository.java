package user_service_spring.aston.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user_service_spring.aston.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}