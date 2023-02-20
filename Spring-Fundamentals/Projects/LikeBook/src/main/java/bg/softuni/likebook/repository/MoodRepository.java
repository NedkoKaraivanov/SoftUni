package bg.softuni.likebook.repository;

import bg.softuni.likebook.model.entity.Mood;
import bg.softuni.likebook.model.enums.MoodEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodRepository extends JpaRepository<Mood, Long> {

    Mood findMoodByMoodName(MoodEnum moodName);
}
