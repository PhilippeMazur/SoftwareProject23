package edu.ap.backendspring.controller;

import edu.ap.backendspring.dto.AuthResponseDTO;
import edu.ap.backendspring.dto.LoginDTO;
import edu.ap.backendspring.entity.User;
import edu.ap.backendspring.enums.Role;
import edu.ap.backendspring.repository.UserRepository;
import edu.ap.backendspring.security.JWTGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JWTGenerator jwtGenerator;

    @Test
    void findAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("addUser@test.com", "Test123!", Role.INITIATIEFNEMER, "Joh", "Doe"));
        users.add(new User("addUser2@test.com", "Test123!", Role.INITIATIEFNEMER, "Jane", "Doe"));

        when(userRepository.findAll()).thenReturn(users);

        ResponseEntity<Iterable<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void findExistingUserById() {
        User user = new User("addUser3@test.com", "Test123!", Role.INITIATIEFNEMER, "Joh", "Doe");
        user.setId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        ResponseEntity<Optional<User>> response = userController.getUserById(1);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(user, response.getBody().get());
    }

    @Test
    void findNotExistingUserById() {
        when(userRepository.findById(999)).thenReturn(Optional.empty());

        ResponseEntity<Optional<User>> response = userController.getUserById(999);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(Optional.empty(), response.getBody());
    }

    @Test
    void findExistingUserByEmail() {
        User user = new User("user@test.com", "Test123!", Role.INITIATIEFNEMER, "John", "Doe");

        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.getUserByEmail("user@test.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void findNotExistingUserByEmail() {
        when(userRepository.findByEmail("test999@test.com")).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.getUserByEmail("test999@test.com");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    void addNewUser() {
        // Arrange
        User user = new User("addUser4@test.com", "Test123!", Role.INITIATIEFNEMER, "Bob", "Smos");

        when(userRepository.save(user)).thenReturn(user);

        ResponseEntity<User> response = userController.register(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }
    @Test
    void login() {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setEmail("addUser4@test.com");
        loginDTO.setPassword("Test123!");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        String token = "mocked_token";
        when(jwtGenerator.generateToken(authentication)).thenReturn(token);

        ResponseEntity<AuthResponseDTO> response = userController.login(loginDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(token, response.getBody().getAccessToken());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtGenerator, times(1)).generateToken(authentication);
    }
    @Test
    void deleteExistingUser() {
        User user = new User("test@test.com", "Test123!", Role.BESLUITNEMER, "John", "Doe");
        user.setId(1);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.deleteUserByEmail(user.getEmail());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userRepository, times(1)).deleteUserByEmail("test@test.com");
    }


    @Test
    void ChangeExistingUser() {
        User existingUser = new User("phil", "Test123!", Role.DEVELOPER, "Phil", "Dupont");
        User newUser = new User("phil2", "Test123!", Role.DEVELOPER, "Phil", "Dupont");

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        ResponseEntity<User> response = userController.updateUser( newUser,1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newUser.getPassword(), response.getBody().getPassword());
    }
}
