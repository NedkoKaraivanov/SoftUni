package bg.softuni.likebook.service;

import bg.softuni.likebook.model.dto.PostDTO;
import bg.softuni.likebook.model.entity.Mood;
import bg.softuni.likebook.model.entity.Post;
import bg.softuni.likebook.model.entity.User;
import bg.softuni.likebook.model.enums.MoodEnum;
import bg.softuni.likebook.model.viewModel.PostViewDTO;
import bg.softuni.likebook.repository.MoodRepository;
import bg.softuni.likebook.repository.PostRepository;
import bg.softuni.likebook.repository.UserRepository;
import bg.softuni.likebook.session.LoggedUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MoodRepository moodRepository;
    private final UserRepository userRepository;
    private final LoggedUser userSession;

    public PostService(PostRepository postRepository, MoodRepository moodRepository, UserRepository userRepository, LoggedUser userSession) {
        this.postRepository = postRepository;
        this.moodRepository = moodRepository;
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public List<PostViewDTO> getPostsByLoggedUser() {
        return this.postRepository.findAllByCreatorId(userSession.getId())
                .stream()
                .map(post -> new PostViewDTO()
                        .setId(post.getId())
                        .setContent(post.getContent())
                        .setMood(post.getMood().getMoodName().name())
                        .setCreator(post.getCreator().getUsername())
                        .setCountLikes(post.getUserLikes().size()))
                .collect(Collectors.toList());
    }

    public List<PostViewDTO> getAllPostsExceptLoggedUser() {
        return this.postRepository.findAllByCreatorIdIsNot(userSession.getId())
                .stream()
                .map(post -> new PostViewDTO()
                        .setId(post.getId())
                        .setContent(post.getContent())
                        .setMood(post.getMood().getMoodName().name())
                        .setCreator(post.getCreator().getUsername())
                        .setCountLikes(post.getUserLikes().size()))
                .collect(Collectors.toList());
    }

    public void create(PostDTO postDTO) {

        Post post = new Post();

        Mood mood = this.moodRepository
                .findMoodByMoodName(MoodEnum.valueOf(postDTO.getMoodName()));

        User creator = this.userRepository.findById(this.userSession.getId()).orElse(null);

        post.setCreator(creator)
                .setMood(mood)
                .setContent(postDTO.getContent());

        this.postRepository.save(post);
    }


    public void likePost(Long id) {

        User user = this.userRepository.findById(this.userSession.getId()).get();

        Post post = this.postRepository.findById(id).get();

        Set<User> userLikes = post.getUserLikes();

        boolean isMatch = false;

        User userWhoLiked = null;

        for (User userLike : userLikes) {
            if (Objects.equals(userLike.getId(), user.getId())) {
                userWhoLiked = userLike;
                isMatch = true;
                break;
            }
        }

        if (!isMatch) {
            userLikes.add(user);
            this.postRepository.save(post);
            System.out.println("user liked");
        } else {
            userLikes.remove(userWhoLiked);
            this.postRepository.save(post);
            System.out.println("user removed like");
        }
    }

    public void remove(Long id) {

        this.postRepository.deleteById(id);
    }
}
