package bg.softuni.spotifyapp.services;

import bg.softuni.spotifyapp.models.Song;
import bg.softuni.spotifyapp.models.dtos.PlaylistDurationViewDTO;
import bg.softuni.spotifyapp.models.dtos.SongViewDTO;
import bg.softuni.spotifyapp.repositories.UserRepository;
import bg.softuni.spotifyapp.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    private final LoggedUser userSession;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userSession = userSession;
    }

    public List<SongViewDTO> getUserPlaylist() {

        return this.userRepository.findById(userSession.getId())
                .getPlaylist()
                .stream()
                .map(song -> modelMapper.map(song, SongViewDTO.class))
                .collect(Collectors.toList());
    }

    public PlaylistDurationViewDTO getTotalDurationOfPlaylist() {

        int totalDurationInSeconds = this.userRepository.findById(userSession.getId())
                .getPlaylist()
                .stream()
                .map(Song::getDuration)
                .mapToInt(s -> s)
                .sum();

        int minutes = 0;
        int seconds = 0;
        if (totalDurationInSeconds != 0) {
            minutes = totalDurationInSeconds / 60;
            seconds = totalDurationInSeconds % minutes;
        }

        PlaylistDurationViewDTO playlistDurationViewDTO = new PlaylistDurationViewDTO();
        playlistDurationViewDTO.setMinutes(minutes);
        playlistDurationViewDTO.setSeconds(seconds);

        return playlistDurationViewDTO;
    }
}
