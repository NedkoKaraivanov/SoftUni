package bg.softuni.spotifyapp.models;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private StyleEnum name;

    private String description;

    public StyleEnum getName() {
        return name;
    }

    public Style setName(StyleEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }
}
