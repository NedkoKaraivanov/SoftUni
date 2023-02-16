package bg.softuni.coffeeshop.services;

import bg.softuni.coffeeshop.models.Category;
import bg.softuni.coffeeshop.models.CategoryEnum;
import bg.softuni.coffeeshop.models.Order;
import bg.softuni.coffeeshop.models.User;
import bg.softuni.coffeeshop.models.dtos.OrderDTO;
import bg.softuni.coffeeshop.models.dtos.OrderViewDTO;
import bg.softuni.coffeeshop.repositories.CategoryRepository;
import bg.softuni.coffeeshop.repositories.OrderRepository;
import bg.softuni.coffeeshop.repositories.UserRepository;
import bg.softuni.coffeeshop.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final LoggedUser userSession;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, CategoryRepository categoryRepository, UserRepository userRepository, LoggedUser userSession, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.modelMapper = modelMapper;
    }

    public void createOrder(OrderDTO orderDTO) {

//        Order order = new Order();
//
//        Category category = orderDTO.getCategory();
//        order.setName(order.getName());
//        order.setPrice(order.getPrice());
//        order.setOrderTime(orderDTO.getOrderTime());
//        order.setDescription(order.getDescription());
        Order order = modelMapper.map(orderDTO, Order.class);

        long id = userSession.getId();

        User employee = userRepository.findById(id).get();

        order.setEmployee(employee);

        Category category = this.categoryRepository.findByName(CategoryEnum.valueOf(orderDTO.getCategory().name())).get();

        order.setCategory(category);

        this.orderRepository.save(order);
    }

    public List<OrderViewDTO> findAllOrderOrderByPriceDesc() {
            return this.orderRepository
                    .findAllByOrderByPriceDesc()
                    .stream()
                    .map(order -> modelMapper.map(order, OrderViewDTO.class))
                    .collect(Collectors.toList());
    }

    public void readyOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
