package bg.softuni.spotifyapp.controllers;

import bg.softuni.spotifyapp.models.dtos.PlaylistDurationViewDTO;
import bg.softuni.spotifyapp.models.dtos.SongViewDTO;
import bg.softuni.spotifyapp.services.AuthService;
import bg.softuni.spotifyapp.services.SongService;
import bg.softuni.spotifyapp.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final SongService songService;

    private final AuthService authService;
    private final UserService userService;

    public HomeController(SongService songService, AuthService authService, UserService userService) {
        this.songService = songService;
        this.authService = authService;
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

        List<SongViewDTO> rockSongs = this.songService.rockSongs();
        List<SongViewDTO> jazzSongs = this.songService.jazzSongs();
        List<SongViewDTO> popSongs = this.songService.popSongs();
        List<SongViewDTO> userPlaylist = this.userService.getUserPlaylist();
        PlaylistDurationViewDTO totalDurationOfPlaylist = this.userService.getTotalDurationOfPlaylist();


        model.addAttribute("rockSongs", rockSongs);
        model.addAttribute("jazzSongs", jazzSongs);
        model.addAttribute("popSongs", popSongs);
        model.addAttribute("userPlaylist", userPlaylist);
        model.addAttribute("totalDuration", totalDurationOfPlaylist);

        return "home";
    }

}
