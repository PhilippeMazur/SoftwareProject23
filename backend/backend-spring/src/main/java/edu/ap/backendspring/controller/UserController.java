package edu.ap.backendspring.controller;

import edu.ap.backendspring.dto.AuthResponseDTO;
import edu.ap.backendspring.dto.LoginDTO;
import edu.ap.backendspring.dto.RegisterDTO;
import edu.ap.backendspring.dto.UserDTO;
import edu.ap.backendspring.entity.User;
import edu.ap.backendspring.repository.UserRepository;
import edu.ap.backendspring.security.JWTGenerator;
import edu.ap.backendspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTGenerator jwtGenerator;

    @GetMapping("")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        Iterable<User> users = userService.findAllUsers();
        if(users.iterator().hasNext()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") int id) {
        Optional<User> user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @GetMapping("/email={email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        Optional<User> user = userService.findUserByEmail(email);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserByEmail(@PathVariable("id") int id) {
        Optional<User> user = userService.findUserById(id);
        if(user.isPresent()) {
            userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping("/email={email}")
    public ResponseEntity<User> deleteUserByEmail(@PathVariable("email") String email) {
        Optional<User> user = userService.findUserByEmail(email);


        userService.deleteUserByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<User> register(@RequestBody User user){

        user.setPassword(passwordEncoder.encode((user.getPassword())));
        userService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") int id) {
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        userService.updateUser(id, user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}