package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("Test User");
    }

    @Test
    void getUserByIdTest() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        User foundUser = userService.getUserById(1L);
        assertEquals("Test User", foundUser.getUsername());
    }

    @Test
    void createUserTest() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);
        assertNotNull(createdUser);
        assertEquals("Test User", createdUser.getUsername());
    }

    @Test
    void deleteUserTest() {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        userService.deleteUser(1L);
        verify(userRepository, times(1)).delete(user);
    }
}
