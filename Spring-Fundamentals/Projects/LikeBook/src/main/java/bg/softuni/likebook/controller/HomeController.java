package bg.softuni.likebook.controller;

import bg.softuni.likebook.model.viewModel.PostViewDTO;
import bg.softuni.likebook.service.AuthService;
import bg.softuni.likebook.service.PostService;
import bg.softuni.likebook.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {


    private final AuthService authService;

    private final LoggedUser userSession;
    private final PostService postService;

    public HomeController(AuthService authService, LoggedUser userSession, PostService postService) {
        this.authService = authService;
        this.userSession = userSession;
        this.postService = postService;
    }

    @GetMapping("/")
    public String loggedOutIndex() {

        if (this.authService.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {

        if (!this.authService.isLoggedIn()) {

            return "redirect:/";
        }

        List<PostViewDTO> postsByLoggedUser = this.postService.getPostsByLoggedUser();
        List<PostViewDTO> postsExceptLoggedUser = this.postService.getAllPostsExceptLoggedUser();
        int countOtherPosts = postsExceptLoggedUser.size();

        model.addAttribute("username", this.userSession.getUsername());
        model.addAttribute("postsByLoggedUser", postsByLoggedUser);
        model.addAttribute("postsExceptLoggedUser", postsExceptLoggedUser);
        model.addAttribute("countOtherPosts", countOtherPosts);

        return "home";
    }

    @GetMapping("posts/like/{id}")
    public String likePost(@PathVariable Long id) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.postService.likePost(id);

        return "redirect:/home";
    }


}
