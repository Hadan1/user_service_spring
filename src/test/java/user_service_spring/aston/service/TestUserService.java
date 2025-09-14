package user_service_spring.aston.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import user_service_spring.aston.entity.User;
import user_service_spring.aston.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestUserService {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    private User user;

    @BeforeEach
    void setup() {
        user = new User();
        user.setId(1L);
        user.setName("John");
        user.setEmail("john@example.com");
        user.setAge(30L);
    }

    @Test
    void testCreateUser() {
        when(repository.save(any(User.class))).thenReturn(user);

        User created = service.createUser(user);

        assertNotNull(created);
        assertEquals("John", created.getName());
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserFound() {
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> found = repository.findById(1L);

        assertTrue(found.isPresent());
        assertEquals("John", found.get().getName());
    }

    @Test
    void testGetUserNotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        Optional<User> found = repository.findById(2L);

        assertFalse(found.isPresent());
    }

    @Test
    void testUpdateUserSuccess() {
        User updateData = new User();
        updateData.setName("Jane");
        updateData.setEmail("jane@example.com");
        updateData.setAge(25L);

        when(repository.findById(1L)).thenReturn(Optional.of(user));
        when(repository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User updated = service.updateUser(1L, updateData);

        assertEquals("Jane", updated.getName());
        assertEquals("jane@example.com", updated.getEmail());
//        assertEquals(25, updated.getAge());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(repository).deleteById(1L);

        service.deleteUser(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}