package bg.softuni.resellerapp.web;

import bg.softuni.resellerapp.model.viewDTO.OfferViewDTO;
import bg.softuni.resellerapp.service.AuthService;
import bg.softuni.resellerapp.service.OfferService;
import bg.softuni.resellerapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final AuthService authService;

    private final OfferService offerService;
    private final UserService userService;

    public HomeController(AuthService authService, OfferService offerService, UserService userService) {
        this.authService = authService;
        this.offerService = offerService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String loggedOutIndex() {

        if (authService.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        String username = this.userService.findUsername();
        model.addAttribute("username", username);
        List<OfferViewDTO> offersFromLoggedUser = this.offerService.offersFromLoggedUser();
        model.addAttribute("offersFromLoggedUser", offersFromLoggedUser);

//        List<OfferViewDTO> offersFromOtherUsers = this.offerService.offersFromOtherUsers();

        return "home";
    }
}
