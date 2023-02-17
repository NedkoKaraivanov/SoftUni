package bg.softuni.spotifyapp.services;

import bg.softuni.spotifyapp.models.Style;
import bg.softuni.spotifyapp.models.StyleEnum;
import bg.softuni.spotifyapp.repositories.StyleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeedService implements CommandLineRunner {

    private final StyleRepository styleRepository;

    public SeedService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.styleRepository.count() == 0) {

            Arrays.stream(StyleEnum.values())
                    .map(styleEnum -> new Style().setName(styleEnum))
                    .forEach(this.styleRepository::save);
        }
    }
}
