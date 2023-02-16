package com.softuni.battleships.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ships")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    private long health;

    private long power;

    private LocalDate created;

    @ManyToOne
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public long getId() {
        return id;
    }

    public Ship setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Ship setName(String name) {
        this.name = name;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public Ship setHealth(long health) {
        this.health = health;
        return this;
    }

    public long getPower() {
        return power;
    }

    public Ship setPower(long power) {
        this.power = power;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public Ship setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Ship setCategory(Category category) {
        this.category = category;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Ship setUser(User owner) {
        this.user = owner;
        return this;
    }
}
