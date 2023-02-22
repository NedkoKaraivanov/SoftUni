package bg.softuni.resellerapp.init;

import bg.softuni.resellerapp.model.entity.Condition;
import bg.softuni.resellerapp.model.enums.ConditionEnum;
import bg.softuni.resellerapp.repository.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InitCondition implements CommandLineRunner {

    private final ConditionRepository conditionRepository;

    public InitCondition(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.conditionRepository.count() == 0) {
            Arrays.stream(ConditionEnum.values())
                    .map(condEnum -> {
                        Condition condition = new Condition().setConditionName(condEnum);

                        switch (condEnum.name()) {
                            case "EXCELLENT":
                                condition.setDescription("In perfect condition");
                                break;
                            case "GOOD":
                                condition.setDescription("Some signs of wear and tear or minor defects");
                                break;
                            case "ACCEPTABLE":
                                condition.setDescription("The item is fairly worn but continues to function properly");
                                break;
                        }

                        return condition;
                    })
                    .forEach(this.conditionRepository::save);
        }
    }
}
