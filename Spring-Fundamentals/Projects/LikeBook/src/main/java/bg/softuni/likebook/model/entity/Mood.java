package bg.softuni.likebook.model.entity;

import bg.softuni.likebook.model.enums.MoodEnum;

import javax.persistence.*;

@Entity
@Table(name = "moods")
public class Mood extends BaseEntity {

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private MoodEnum moodName;

    @Column(columnDefinition = "TEXT")
    private String description;

    public MoodEnum getMoodName() {
        return moodName;
    }

    public Mood setMoodName(MoodEnum moodName) {
        this.moodName = moodName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Mood setDescription(String description) {
        this.description = description;
        return this;
    }
}
