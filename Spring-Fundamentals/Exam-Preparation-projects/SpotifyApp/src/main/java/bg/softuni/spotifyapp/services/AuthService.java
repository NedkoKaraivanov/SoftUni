package bg.softuni.spotifyapp.services;

import bg.softuni.spotifyapp.models.User;
import bg.softuni.spotifyapp.models.dtos.LoginDTO;
import bg.softuni.spotifyapp.models.dtos.RegisterDTO;
import bg.softuni.spotifyapp.repositories.UserRepository;
import bg.softuni.spotifyapp.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final LoggedUser userSession;

    private final ModelMapper modelMapper;

    public AuthService(UserRepository userRepository, LoggedUser userSession, ModelMapper modelMapper) {
        this.userRepository = userRepository;
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

        User user = this.modelMapper.map(registerDTO, User.class);
        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> optionalUser = this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (optionalUser.isEmpty()) {
            return false;
        }

        this.userSession.login(optionalUser.get());

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
