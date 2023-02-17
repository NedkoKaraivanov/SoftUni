package bg.softuni.spotifyapp.repositories;

import bg.softuni.spotifyapp.models.Song;
import bg.softuni.spotifyapp.models.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByStyleName(StyleEnum name);

    Song findSongById(long id);
}
