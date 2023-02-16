package bg.softuni.likebook.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(optional = false)
    private User creator;

    @ManyToMany
    @Column(name = "user_likes")
    private List<User> userLikes = new ArrayList<>();

    @ManyToOne
    private Mood mood;

    public String getContent() {
        return content;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public User getCreator() {
        return creator;
    }

    public Post setCreator(User creator) {
        this.creator = creator;
        return this;
    }

    public List<User> getUserLikes() {
        return userLikes;
    }

    public Post setUserLikes(List<User> userLikes) {
        this.userLikes = userLikes;
        return this;
    }

    public Mood getMood() {
        return mood;
    }

    public Post setMood(Mood mood) {
        this.mood = mood;
        return this;
    }
}
