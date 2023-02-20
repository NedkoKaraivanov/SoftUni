package bg.softuni.likebook.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "creator")
    private List<Post> postList;

    @ManyToMany(mappedBy = "userLikes",fetch = FetchType.EAGER)
    private Set<Post> likedPosts;

    public Set<Post> getLikedPosts() {
        return likedPosts;
    }

    public User setLikedPosts(Set<Post> likedPosts) {
        this.likedPosts = likedPosts;
        return this;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public User setPostList(List<Post> postList) {
        this.postList = postList;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
}
