package bg.softuni.likebook.service;

import bg.softuni.likebook.repository.MoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoodService {

    private final MoodRepository moodRepository;

    public MoodService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    public List<String> findAllMoods() {
        return this.moodRepository.findAll()
                .stream()
                .map(mood -> mood.getMoodName().name())
                .collect(Collectors.toList());
    }
}
