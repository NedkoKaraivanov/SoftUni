package bg.softuni.coffeeshop.controllers;

import bg.softuni.coffeeshop.models.dtos.OrderDTO;
import bg.softuni.coffeeshop.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ModelAttribute("orderDTO")
    public OrderDTO initOrderDTO() {
        return new OrderDTO();
    }

    @GetMapping("/orders/add")
    public String order() {

        return "order-add";
    }

    @PostMapping("/orders/add")
    public String addOrder(@Valid OrderDTO orderDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("orderDTO", orderDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDTO", bindingResult);

            return "redirect:/orders/add";
        }

        orderService.createOrder(orderDTO);

        return "redirect:/home";
    }

    @GetMapping("/orders/ready/{id}")
    public String ready(@PathVariable Long id) {
        orderService.readyOrder(id);

        return "redirect:/";
    }

}

