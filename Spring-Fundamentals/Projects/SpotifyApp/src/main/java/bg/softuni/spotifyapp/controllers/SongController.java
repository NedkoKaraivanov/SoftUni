package bg.softuni.spotifyapp.controllers;

import bg.softuni.spotifyapp.models.dtos.SongAddDTO;
import bg.softuni.spotifyapp.services.AuthService;
import bg.softuni.spotifyapp.services.SongService;
import bg.softuni.spotifyapp.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SongController {

    private final SongService songService;

    private final AuthService authService;
    private final LoggedUser userSession;

    public SongController(SongService songService, AuthService authService, LoggedUser userSession) {
        this.songService = songService;
        this.authService = authService;
        this.userSession = userSession;
    }

    @ModelAttribute("songAddDTO")
    public SongAddDTO initSongAddDTO() {
        return new SongAddDTO();
    }

    @GetMapping("/songs/add")
    public String addSong() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "song-add";
    }

    @PostMapping("/songs/add")
    public String addSong(@Valid SongAddDTO songAddDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("songAddDTO", songAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.songAddDTO", bindingResult);

            return "redirect:/songs/add";
        }

        this.songService.addSong(songAddDTO);

        return "redirect:/home";
    }

    @GetMapping("/songs/addSongToPlaylist/{id}")
    public String addSongToPlaylist(@PathVariable Long id) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.songService.addSongToPlaylist(id);

        return "redirect:/home";
    }

    @GetMapping("/songs/removeAllFromPlaylist")
    public String removeAllFromPlaylist() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.songService.removeAllFromPlaylist();

        return "redirect:/home";
    }

}
