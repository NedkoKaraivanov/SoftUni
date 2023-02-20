package bg.softuni.likebook.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostDTO {

    @NotBlank()
    private String moodName;

    @Size(min = 2, max = 150)
    private String content;

    public String getMoodName() {
        return moodName;
    }

    public PostDTO setMoodName(String moodName) {
        this.moodName = moodName;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostDTO setContent(String content) {
        this.content = content;
        return this;
    }
}
