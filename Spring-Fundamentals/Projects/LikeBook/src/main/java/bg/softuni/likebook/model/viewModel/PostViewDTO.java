package bg.softuni.likebook.model.viewModel;

import java.util.HashSet;
import java.util.Set;

public class PostViewDTO {

    private Long id;
    private String creator;
    private String mood;
    private int countLikes;
    private String content;

    public Long getId() {
        return id;
    }

    public PostViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public PostViewDTO setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public String getMood() {
        return mood;
    }

    public PostViewDTO setMood(String mood) {
        this.mood = mood;
        return this;
    }

    public int getCountLikes() {
        return countLikes;
    }

    public PostViewDTO setCountLikes(int countLikes) {
        this.countLikes = countLikes;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostViewDTO setContent(String content) {
        this.content = content;
        return this;
    }
}
