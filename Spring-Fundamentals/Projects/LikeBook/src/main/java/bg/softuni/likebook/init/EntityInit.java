package bg.softuni.likebook.init;

import bg.softuni.likebook.service.MoodService;
import bg.softuni.likebook.service.PostService;
import bg.softuni.likebook.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class EntityInit implements CommandLineRunner {

    private final MoodService moodService;
    private final UserService userService;
    private final PostService postService;

    public EntityInit(MoodService moodService, UserService userService, PostService postService) {
        this.moodService = moodService;
        this.userService = userService;
        this.postService = postService;
    }


    @Override
    public void run(String... args) throws Exception {

        this.moodService.initMoods();
        this.userService.initAdmin();
        this.userService.initTestUser();
        this.postService.addTestPosts();
    }
}
