package bg.softuni.coffeeshop.services;

import bg.softuni.coffeeshop.models.User;
import bg.softuni.coffeeshop.models.dtos.LoginDTO;
import bg.softuni.coffeeshop.models.dtos.RegisterDTO;
import bg.softuni.coffeeshop.repositories.UserRepository;
import bg.softuni.coffeeshop.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final LoggedUser userSession;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userSession = userSession;
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

    public boolean register(RegisterDTO registerDTO) {

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            return false;
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registerDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }

        Optional<User> byUsername = this.userRepository.findByUsername(registerDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        User user = this.modelMapper.map(registerDTO, User.class);
        user.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));

        this.userRepository.save(user);

        return true;
    }

    public void logout() {
        this.userSession.logout();
    }

    public boolean isLoggedIn() {
        return this.userSession.getId() > 0;
    }

    public long getLoggedUserId() {
        return this.userSession.getId();
    }
}
