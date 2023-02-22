package bg.softuni.resellerapp.service;

import bg.softuni.resellerapp.repository.UserRepository;
import bg.softuni.resellerapp.session.LoggedUser;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final LoggedUser userSession;

    public UserService(UserRepository userRepository, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public String findUsername() {
       return this.userSession.getUsername();
    }
}
