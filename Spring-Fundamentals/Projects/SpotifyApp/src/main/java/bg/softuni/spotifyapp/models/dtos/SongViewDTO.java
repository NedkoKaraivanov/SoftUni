package bg.softuni.spotifyapp.models.dtos;

public class SongViewDTO {

    private Long id;
    private String performer;

    private String title;

    private Integer duration;

    public String getDurationFormatted() {
        return String.format("%d:%02d", duration / 60, duration % 60);
    }

    public Long getId() {
        return id;
    }

    public SongViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public SongViewDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongViewDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public SongViewDTO setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }
}
