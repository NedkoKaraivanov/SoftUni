package bg.softuni.coffeeshop.services;

import bg.softuni.coffeeshop.models.dtos.UserViewDTO;
import bg.softuni.coffeeshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public List<UserViewDTO> findAllUserAndCountOfOrdersOrderByCountDesc() {
        return this.userRepository.findAllUsersOrderByOrdersDesc()
                .stream()
                .map(user -> new UserViewDTO()
                       .setUsername(user.getUsername())
                       .setCountOfOrders(user.getOrders().size()))
                .collect(Collectors.toList());
    }
}
