package bg.softuni.coffeeshop.seeders;

import bg.softuni.coffeeshop.models.Category;
import bg.softuni.coffeeshop.models.CategoryEnum;
import bg.softuni.coffeeshop.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;


    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (this.categoryRepository.count() == 0) {
            List<Category> categoryList = Arrays.stream(CategoryEnum.values())
                    .map(name -> new Category(name))
                    .collect(Collectors.toList());

            for (Category category : categoryList) {
                CategoryEnum name = category.getName();
                switch (name) {
                    case Drink -> category.setNeededTime(1);
                    case Coffee -> category.setNeededTime(2);
                    case Cake -> category.setNeededTime(10);
                    case Other -> category.setNeededTime(5);
                }
            }

            this.categoryRepository.saveAll(categoryList);
        }

    }
}
