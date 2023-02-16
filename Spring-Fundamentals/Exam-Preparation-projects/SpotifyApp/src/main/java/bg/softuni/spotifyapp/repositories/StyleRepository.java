package bg.softuni.spotifyapp.repositories;

import bg.softuni.spotifyapp.models.Style;
import bg.softuni.spotifyapp.models.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    Style findStyleByName(StyleEnum name);
}
