package bg.softuni.resellerapp.service;

import bg.softuni.resellerapp.model.dto.AddOfferDTO;
import bg.softuni.resellerapp.model.entity.Offer;
import bg.softuni.resellerapp.model.entity.User;
import bg.softuni.resellerapp.model.enums.ConditionEnum;
import bg.softuni.resellerapp.model.dto.viewDTO.OfferViewDTO;
import bg.softuni.resellerapp.repository.ConditionRepository;
import bg.softuni.resellerapp.repository.OfferRepository;
import bg.softuni.resellerapp.repository.UserRepository;
import bg.softuni.resellerapp.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    private final UserRepository userRepository;
    private final LoggedUser userSession;
    private final ConditionRepository conditionRepository;

    public OfferService(OfferRepository offerRepository, UserRepository userRepository, LoggedUser userSession, ConditionRepository conditionRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.conditionRepository = conditionRepository;
    }


    public void addOffer(AddOfferDTO addOfferDTO) {
        Offer offer = new Offer()
                .setDescription(addOfferDTO.getDescription())
                .setPrice(addOfferDTO.getPrice())
                .setCondition(this.conditionRepository.findByConditionName(ConditionEnum.valueOf(addOfferDTO.getCondition())))
                .setCreator(this.userRepository.findById(userSession.getId()).get());

        this.offerRepository.save(offer);

    }

    public Set<OfferViewDTO> offersFromLoggedUser() {

        return this.userRepository.findById(userSession.getId())
                .get().getOffers()
                .stream()
                .map(userOffer -> new OfferViewDTO()
                        .setId(userOffer.getId())
                        .setCreatorUsername(userSession.getUsername())
                        .setPrice(userOffer.getPrice())
                        .setCondition(userOffer.getCondition().getConditionName().name())
                        .setDescription(userOffer.getDescription()))
                .collect(Collectors.toSet());
    }

    public Set<OfferViewDTO> offersFromOtherUsers() {

        return this.offerRepository.findAllByCreator_IdNotAndBuyerIsNull(userSession.getId())
                .stream()
                .map(offer -> new OfferViewDTO()
                        .setId(offer.getId())
                        .setCreatorUsername(offer.getCreator().getUsername())
                        .setPrice(offer.getPrice())
                        .setCondition(offer.getCondition().getConditionName().name())
                        .setDescription(offer.getDescription()))
                .collect(Collectors.toSet());
    }

    public Set<OfferViewDTO> boughtOffers() {

        return this.userRepository.findById(userSession.getId())
                .get().getBoughtOffers()
                .stream()
                .map(offer -> new OfferViewDTO()
                        .setId(offer.getId())
                        .setCreatorUsername(offer.getCreator().getUsername())
                        .setPrice(offer.getPrice())
                        .setCondition(offer.getCondition().getConditionName().name())
                        .setDescription(offer.getDescription()))
                .collect(Collectors.toSet());
    }

    public void buyOffer(Long id) {

        Offer offerById = this.offerRepository.findById(id).get();

        User user = this.userRepository.findById(userSession.getId()).get();

        offerById.setBuyer(user);

        this.offerRepository.save(offerById);
    }

    public void removeOffer(Long id) {

        this.offerRepository.deleteById(id);
    }

    public void initOffers() {
        User user1 = this.userRepository.findById(1L).orElse(null);
        User user2 = this.userRepository.findById(2L).orElse(null);

        List<Offer> testOffers = new ArrayList<>();

        Offer offer1 = new Offer()
                .setCreator(user1)
                .setDescription("Iphone X")
                .setCondition(this.conditionRepository.findByConditionName(ConditionEnum.EXCELLENT))
                .setPrice(BigDecimal.valueOf(800));

        Offer offer2 = new Offer()
                .setCreator(user1)
                .setDescription("Bmw 330xd")
                .setCondition(this.conditionRepository.findByConditionName(ConditionEnum.GOOD))
                .setPrice(BigDecimal.valueOf(13000));

        Offer offer3 = new Offer()
                .setCreator(user2)
                .setDescription("Logitech Keyboard")
                .setCondition(this.conditionRepository.findByConditionName(ConditionEnum.ACCEPTABLE))
                .setPrice(BigDecimal.valueOf(70));

        Offer offer4 = new Offer()
                .setCreator(user2)
                .setDescription("Yamaha R1")
                .setCondition(this.conditionRepository.findByConditionName(ConditionEnum.EXCELLENT))
                .setPrice(BigDecimal.valueOf(8000));

        testOffers.add(offer1);
        testOffers.add(offer2);
        testOffers.add(offer3);
        testOffers.add(offer4);

        this.offerRepository.saveAllAndFlush(testOffers);
    }
}
