package bg.softuni.likebook.repository;

import bg.softuni.likebook.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query(value = "SELECT p from Post as p where p.creator.id not in :id ")
    List<Post> findAllByCreatorIdIsNot(Long id);

    @Query(value = "SELECT p from Post as p where p.creator.id in :id ")
    List<Post> findAllByCreatorId(Long id);

}
