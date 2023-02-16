package bg.softuni.spotifyapp.models.dtos;

import bg.softuni.spotifyapp.models.Style;
import bg.softuni.spotifyapp.models.StyleEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class SongAddDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String performer;

    @NotBlank
    @Size(min = 2, max = 20)
    private String title;

    @NotNull
    @Positive
    private Integer duration;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StyleEnum style;

    public String getPerformer() {
        return performer;
    }

    public SongAddDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongAddDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public SongAddDTO setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public SongAddDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public SongAddDTO setStyle(StyleEnum style) {
        this.style = style;
        return this;
    }
}
