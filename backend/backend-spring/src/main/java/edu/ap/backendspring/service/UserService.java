package edu.ap.backendspring.service;

import edu.ap.backendspring.entity.User;
import edu.ap.backendspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserByEmail (String email){
        return userRepository.findByEmail(email);
    }

    public User createUser(User user){
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (user.getRole() == null) {
            throw new IllegalArgumentException("Role is required");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is taken");
        }

        return userRepository.save(user);
    }
    public void deleteUserById(int id){
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> userRepository.delete(value));
    }

    public void deleteUserByEmail(String email){
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isEmpty())
            throw new IllegalArgumentException("User doesn't exist");
        userRepository.deleteUserByEmail(email);
    }

    public User updateUser(int id,User user ){
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isEmpty())
            throw new IllegalArgumentException("User doesn't exist");

        User updatedUser = existingUser.get();
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setRole(user.getRole());

        return userRepository.save(updatedUser);
    }

}