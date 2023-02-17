package bg.softuni.likebook.init;

import bg.softuni.likebook.model.entity.Mood;
import bg.softuni.likebook.model.enums.MoodEnum;
import bg.softuni.likebook.repository.MoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MoodInit implements CommandLineRunner {

    private final MoodRepository moodRepository;

    public MoodInit(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.moodRepository.count() == 0) {
            List<Mood> moods = Arrays.stream(MoodEnum.values())
                    .map(moodEnum -> new Mood().setMoodName(moodEnum))
                    .collect(Collectors.toList());

            this.moodRepository.saveAll(moods);
        }
    }
}
