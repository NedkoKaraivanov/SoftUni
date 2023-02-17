package bg.softuni.coffeeshop.repositories;

import bg.softuni.coffeeshop.models.Category;
import bg.softuni.coffeeshop.models.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(CategoryEnum name);
}
