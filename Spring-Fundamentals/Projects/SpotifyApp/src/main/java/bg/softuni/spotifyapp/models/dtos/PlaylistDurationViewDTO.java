package bg.softuni.spotifyapp.models.dtos;

public class PlaylistDurationViewDTO {

    private int hours;
    private int minutes;

    private int seconds;

    public int getHours() {
        return hours;
    }

    public PlaylistDurationViewDTO setHours(int hours) {
        this.hours = hours;
        return this;
    }

    public int getMinutes() {
        return minutes;
    }

    public PlaylistDurationViewDTO setMinutes(int minutes) {
        this.minutes = minutes;
        return this;
    }

    public int getSeconds() {
        return seconds;
    }

    public PlaylistDurationViewDTO setSeconds(int seconds) {
        this.seconds = seconds;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%d:%02d", minutes, seconds);
    }
}
