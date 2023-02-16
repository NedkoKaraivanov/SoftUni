package bg.softuni.coffeeshop.services;

import bg.softuni.coffeeshop.models.User;
import bg.softuni.coffeeshop.models.dtos.LoginDTO;
import bg.softuni.coffeeshop.models.dtos.RegisterDTO;
import bg.softuni.coffeeshop.repositories.UserRepository;
import bg.softuni.coffeeshop.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;
    private LoggedUser userSession;

    public AuthService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userSession = userSession;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> user = this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (user.isEmpty()) {
            return false;
        }

        this.userSession.login(user.get());

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

        //User user = new User();
        User user = this.modelMapper.map(registerDTO, User.class);

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
