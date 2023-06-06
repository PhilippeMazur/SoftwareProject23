package edu.ap.backendspring.dataloader;

import edu.ap.backendspring.entity.User;
import edu.ap.backendspring.enums.Role;
import edu.ap.backendspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDataSeed implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }
    private void loadUserData() {
        if (userRepository.count() == 0) {
            User user1 = new User("user", "user", Role.DEVELOPER,"John","Doe");
            user1.setPassword(passwordEncoder.encode((user1.getPassword())));
            userRepository.save(user1);
        }
    }
}
