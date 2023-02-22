package bg.softuni.resellerapp.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creator")
    private Set<Offer> offers;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Offer> boughtOffers;

    public User() {
        this.offers = new HashSet<>();
        this.boughtOffers = new HashSet<>();
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

    public Set<Offer> getOffers() {
        return offers;
    }

    public User setOffers(Set<Offer> offers) {
        this.offers = offers;
        return this;
    }

    public Set<Offer> getBoughtOffers() {
        return boughtOffers;
    }

    public User setBoughtOffers(Set<Offer> boughtOffers) {
        this.boughtOffers = boughtOffers;
        return this;
    }
}
