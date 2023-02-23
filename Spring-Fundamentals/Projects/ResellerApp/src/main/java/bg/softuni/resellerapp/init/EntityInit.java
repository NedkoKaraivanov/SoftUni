package bg.softuni.resellerapp.init;

import bg.softuni.resellerapp.service.ConditionService;
import bg.softuni.resellerapp.service.OfferService;
import bg.softuni.resellerapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EntityInit implements CommandLineRunner {

    private final ConditionService conditionService;

    private final OfferService offerService;

    private final UserService userService;

    public EntityInit(ConditionService conditionService, OfferService offerService, UserService userService) {
        this.conditionService = conditionService;
        this.offerService = offerService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.conditionService.initConditions();
        this.userService.initUsers();
        this.offerService.initOffers();
    }
}
