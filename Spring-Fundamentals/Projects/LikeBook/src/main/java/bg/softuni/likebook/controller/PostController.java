package bg.softuni.likebook.controller;

import bg.softuni.likebook.model.dto.PostDTO;
import bg.softuni.likebook.service.AuthService;
import bg.softuni.likebook.service.MoodService;
import bg.softuni.likebook.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController {

    private final AuthService authService;
    private final PostService postService;
    private final MoodService moodService;

    public PostController(AuthService authService, PostService postService, MoodService moodService) {
        this.authService = authService;
        this.postService = postService;
        this.moodService = moodService;
    }

    @ModelAttribute("postDTO")
    public PostDTO initPostDTO() {
        return new PostDTO();
    }

    @ModelAttribute("moodsList")
    public List<String> moodsList() {
        return this.moodService.findAllMoods();
    }

    @GetMapping("/posts/add")
    public String addPost() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "post-add";
    }

    @PostMapping("/posts/add")
    public String addPost(@Valid PostDTO postDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("postDTO", postDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postDTO", bindingResult);

            return "redirect:/posts/add";
        }

        this.postService.create(postDTO);

        return "redirect:/home";
    }

    @GetMapping("/posts/like/{id}")
    public String likePost(@PathVariable Long id) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.postService.likePost(id);

        return "redirect:/";
    }

    @GetMapping("/posts/remove/{id}")
    public String removePost(@PathVariable Long id) {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.postService.remove(id);

        return "redirect:/";
    }

}
