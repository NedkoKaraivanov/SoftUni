package bg.softuni.coffeeshop.controllers;

import bg.softuni.coffeeshop.models.dtos.OrderViewDTO;
import bg.softuni.coffeeshop.services.AuthService;
import bg.softuni.coffeeshop.services.OrderService;
import bg.softuni.coffeeshop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final AuthService authService;

    private final UserService userService;
    private final OrderService orderService;

    public HomeController(AuthService authService, UserService userService, OrderService orderService) {
        this.authService = authService;
        this.userService = userService;
        this.orderService = orderService;
    }


//    @ModelAttribute("orders")
//    public List<OrderViewDTO> orders() {
//        return this.orderService.findAllOrderOrderByPriceDesc();
//    }

    @GetMapping("/")
    public String loggedOutIndex() {

        if (authService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {

        if (!authService.isLoggedIn()) {
            return "redirect:/";
        }

        List<OrderViewDTO> orders = this.orderService.findAllOrderOrderByPriceDesc();

        model.addAttribute("orders", orders);
        model.addAttribute("totalTime", orders
                .stream()
                .map(orderViewDTO -> orderViewDTO.getCategory().getNeededTime())
                .reduce(Integer::sum)
                .orElse(0));

        model.addAttribute("users", this.userService.findAllUserAndCountOfOrdersOrderByCountDesc());

        return "home";
    }

}
