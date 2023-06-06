package edu.ap.backendspring.service;

import edu.ap.backendspring.entity.User;
import edu.ap.backendspring.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllUsers() {
        userService.findAllUsers();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testFindUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.of(new User()));
        Optional<User> user = userService.findUserById(1);
        Assertions.assertTrue(user.isPresent());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testFindUserByEmail() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(new User()));
        Optional<User> user = userService.findUserByEmail("test@example.com");
        Assertions.assertTrue(user.isPresent());
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testCreateUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("test123");
        user.setRole("DEVELOPER");
        when(userRepository.save(any())).thenReturn(user);

        User savedUser = userService.createUser(user);

        Assertions.assertEquals("test@example.com", savedUser.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUserById() {
        int userId = 1;
        User userToDelete = new User();
        userToDelete.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userToDelete));

        userService.deleteUserById(userId);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(userToDelete);
    }

    @Test
    void testDeleteUserByEmail() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(new User()));
        userService.deleteUserByEmail("test@example.com");
        verify(userRepository, times(1)).deleteUserByEmail("test@example.com");
    }

    @Test
    void testUpdateUser() {
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setEmail("test@example.com");
        existingUser.setPassword("test123");
        existingUser.setRole("DEVELOPER");

        User updatedUser = new User();
        updatedUser.setEmail("updated@example.com");
        updatedUser.setPassword("updated123");
        updatedUser.setRole("ADVISEUR");

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any())).thenReturn(updatedUser);

        User savedUser = userService.updateUser(1, updatedUser);

        Assertions.assertEquals("updated@example.com", savedUser.getEmail());
        Assertions.assertEquals("updated123", savedUser.getPassword());
        Assertions.assertEquals("ADVISEUR", savedUser.getRole());
        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).save(any());
    }
}