package bg.softuni.likebook.service;

import bg.softuni.likebook.model.dto.LoginDTO;
import bg.softuni.likebook.model.dto.RegisterDTO;
import bg.softuni.likebook.model.entity.User;
import bg.softuni.likebook.repository.UserRepository;
import bg.softuni.likebook.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final LoggedUser userSession;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public AuthService(LoggedUser userSession, PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper) {
        this.userSession = userSession;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        this.userRepository.save(user);
        return true;
    }

    public boolean login(LoginDTO loginDTO) {

        Optional<User> byUsername = this.userRepository.findByUsername(loginDTO.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }

        String rawPassword = loginDTO.getPassword();
        String encodedPassword = byUsername.get().getPassword();

        boolean isPasswordMatching = this.passwordEncoder.matches(rawPassword, encodedPassword);

        if (!isPasswordMatching) {
            return false;
        }

        this.userSession.login(byUsername.get());

        return true;
    }

    public boolean isLoggedIn() {
        return this.userSession.getId() > 0;
    }
}
