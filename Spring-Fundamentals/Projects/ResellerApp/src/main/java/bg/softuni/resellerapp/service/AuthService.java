package bg.softuni.resellerapp.service;

import bg.softuni.resellerapp.model.dto.LoginDTO;
import bg.softuni.resellerapp.model.dto.RegisterDTO;
import bg.softuni.resellerapp.model.entity.User;
import bg.softuni.resellerapp.repository.UserRepository;
import bg.softuni.resellerapp.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final LoggedUser userSession;

    private final ModelMapper modelMapper;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, LoggedUser userSession, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
        this.modelMapper = modelMapper;
    }

    public boolean register(RegisterDTO registerDTO) {

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            return false;
        }

        Optional<User> optionalUserByEmail = this.userRepository.findByEmail(registerDTO.getEmail());
        if (optionalUserByEmail.isPresent()) {
            return false;
        }

        Optional<User> optionalUserByUsername = this.userRepository.findByUsername(registerDTO.getUsername());
        if (optionalUserByUsername.isPresent()) {
            return false;
        }

        User user = new User()
                .setUsername(registerDTO.getUsername())
                .setEmail(registerDTO.getEmail())
                .setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));
        this.userRepository.save(user);

        return true;
    }

    public boolean isLoggedIn() {
        return this.userSession.getId() > 0;
    }

    public long getLoggedUserId() {
        return this.userSession.getId();
    }

    public boolean login(LoginDTO loginDTO) {

        Optional<User> optionalUser = this.userRepository.findByUsername(loginDTO.getUsername());

        if (optionalUser.isEmpty()) {
            return false;
        }

        String rawPassword = loginDTO.getPassword();
        String encodedPassword = optionalUser.get().getPassword();

        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);

        if (!matches) {
            return false;
        }

        this.userSession.login(optionalUser.get());

        return true;
    }

    public void logout() {
        this.userSession.logout();
    }

}
