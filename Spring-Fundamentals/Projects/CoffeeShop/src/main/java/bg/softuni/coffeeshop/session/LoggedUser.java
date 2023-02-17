package bg.softuni.coffeeshop.session;

import bg.softuni.coffeeshop.models.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {

    private long id;

    private String firstName;

    public void login(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
    }

    public void logout() {
        this.id = 0;
        this.firstName = null;
    }

    public long getId() {
        return id;
    }

    public LoggedUser setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public LoggedUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
}
