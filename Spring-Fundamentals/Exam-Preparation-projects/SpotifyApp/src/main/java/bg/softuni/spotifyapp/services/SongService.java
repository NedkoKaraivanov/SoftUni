package bg.softuni.spotifyapp.services;

import bg.softuni.spotifyapp.models.Song;
import bg.softuni.spotifyapp.models.Style;
import bg.softuni.spotifyapp.models.StyleEnum;
import bg.softuni.spotifyapp.models.User;
import bg.softuni.spotifyapp.models.dtos.SongViewDTO;
import bg.softuni.spotifyapp.models.dtos.SongAddDTO;
import bg.softuni.spotifyapp.repositories.SongRepository;
import bg.softuni.spotifyapp.repositories.StyleRepository;
import bg.softuni.spotifyapp.repositories.UserRepository;
import bg.softuni.spotifyapp.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {

    private final SongRepository songRepository;

    private final StyleRepository styleRepository;

    private final UserRepository userRepository;
    private final LoggedUser userSession;
    private final ModelMapper modelMapper;

    public SongService(SongRepository songRepository, StyleRepository styleRepository, UserRepository userRepository, LoggedUser userSession, ModelMapper modelMapper) {
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.modelMapper = modelMapper;
    }

    public void addSong(SongAddDTO songAddDTO) {

        Song song = this.modelMapper.map(songAddDTO, Song.class);

        StyleEnum name = songAddDTO.getStyle();
        Style styleByName = this.styleRepository.findStyleByName(name);

        song.setStyle(styleByName);

        this.songRepository.save(song);
    }

    public List<SongViewDTO> rockSongs() {
        return this.songRepository.findAllByStyleName(StyleEnum.ROCK)
                .stream()
                .map(song -> modelMapper.map(song, SongViewDTO.class))
                .collect(Collectors.toList());
    }

    public List<SongViewDTO> popSongs() {
        return this.songRepository.findAllByStyleName(StyleEnum.POP)
                .stream()
                .map(song -> modelMapper.map(song, SongViewDTO.class))
                .collect(Collectors.toList());
    }

    public List<SongViewDTO> jazzSongs() {
        return this.songRepository.findAllByStyleName(StyleEnum.JAZZ)
                .stream()
                .map(song -> modelMapper.map(song, SongViewDTO.class))
                .collect(Collectors.toList());
    }

    public void addSongToPlaylist(Long id) {

        Song song = this.songRepository.findSongById(id);

        long loggedUserId = this.userSession.getId();

        User user = this.userRepository.findById(loggedUserId);

        user.getPlaylist().add(song);

        this.userRepository.save(user);
    }

    public void removeAllFromPlaylist() {
        long loggedUserId = this.userSession.getId();

        User user = this.userRepository.findById(loggedUserId);

        user.getPlaylist().clear();

        this.userRepository.save(user);
    }
}
