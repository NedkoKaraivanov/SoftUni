package bg.softuni.likebook.service;

import bg.softuni.likebook.model.entity.User;
import bg.softuni.likebook.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void initAdmin() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(this.passwordEncoder.encode("admin"));
        admin.setEmail("admin@abv.bg");
        this.userRepository.saveAndFlush(admin);
    }


    public void initTestUser() {
        User test = new User();
        test.setUsername("test");
        test.setPassword(this.passwordEncoder.encode("test"));
        test.setEmail("test@abv.bg");
        this.userRepository.saveAndFlush(test);
    }
}
