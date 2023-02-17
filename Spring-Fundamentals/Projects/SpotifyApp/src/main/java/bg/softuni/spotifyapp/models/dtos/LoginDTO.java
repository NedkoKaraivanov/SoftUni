package bg.softuni.spotifyapp.models.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDTO {

    @NotBlank(message = "Field should not be blank.")
    @Size(min = 3, max = 20, message = "Size should be between 3 and 20 characters.")
    private String username;

    @NotBlank(message = "Field should not be blank.")
    @Size(min = 3, max = 20, message = "Size should be between 3 and 20 characters.")
    private String password;

    public String getUsername() {
        return username;
    }

    public LoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
