package bg.softuni.resellerapp.service;

import bg.softuni.resellerapp.model.entity.Condition;
import bg.softuni.resellerapp.model.enums.ConditionEnum;
import bg.softuni.resellerapp.repository.ConditionRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ConditionService {

    private final ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public void initConditions() {
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
