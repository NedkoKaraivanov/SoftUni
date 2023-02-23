package bg.softuni.resellerapp.service;

import bg.softuni.resellerapp.model.entity.User;
import bg.softuni.resellerapp.repository.UserRepository;
import bg.softuni.resellerapp.session.LoggedUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final LoggedUser userSession;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }

    public String findUsername() {
       return this.userSession.getUsername();
    }

    public void initUsers() {
        User test1 = new User()
                .setUsername("test1")
                .setPassword(this.passwordEncoder.encode("test1"))
                .setEmail("test1@abv.bg");

        User test2 = new User()
                .setUsername("test2")
                .setPassword(this.passwordEncoder.encode("test2"))
                .setEmail("test2@abv.bg");

        this.userRepository.save(test1);
        this.userRepository.save(test2);
    }
}
