package bg.softuni.resellerapp.service;

import bg.softuni.resellerapp.model.dto.AddOfferDTO;
import bg.softuni.resellerapp.model.entity.Condition;
import bg.softuni.resellerapp.model.entity.Offer;
import bg.softuni.resellerapp.model.entity.User;
import bg.softuni.resellerapp.model.enums.ConditionEnum;
import bg.softuni.resellerapp.model.viewDTO.OfferViewDTO;
import bg.softuni.resellerapp.repository.ConditionRepository;
import bg.softuni.resellerapp.repository.OfferRepository;
import bg.softuni.resellerapp.repository.UserRepository;
import bg.softuni.resellerapp.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    private final UserRepository userRepository;
    private final LoggedUser userSession;
    private final ConditionRepository conditionRepository;
    private final ModelMapper modelMapper;

    public OfferService(OfferRepository offerRepository, UserRepository userRepository, LoggedUser userSession, ConditionRepository conditionRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.conditionRepository = conditionRepository;
        this.modelMapper = modelMapper;
    }


    public void addOffer(AddOfferDTO addOfferDTO) {
        Offer offer = new Offer()
                .setDescription(addOfferDTO.getDescription())
                .setPrice(addOfferDTO.getPrice());

        Condition firstByConditionName = this.conditionRepository.findFirstByConditionName(ConditionEnum.valueOf(addOfferDTO.getCondition()));

        offer.setCondition(firstByConditionName);

        this.offerRepository.save(offer);

        User user = this.userRepository.findById(this.userSession.getId()).get();
        user.getOffers().add(offer);
        this.userRepository.save(user);
    }

    public List<OfferViewDTO> offersFromLoggedUser() {

        List<OfferViewDTO> offerList = new ArrayList<>();

        List<Offer> userOffers = this.userRepository.findById(userSession.getId())
                .get().getOffers();

        for (Offer userOffer : userOffers) {
            OfferViewDTO offerViewDTO = new OfferViewDTO()
                    .setCondition(userOffer.getCondition().getConditionName().name())
                            .setDescription(userOffer.getDescription())
                                    .setPrice(userOffer.getPrice());
            offerList.add(offerViewDTO);
        }

        return offerList;
    }

//    public List<OfferViewDTO> offersFromOtherUsers() {
//
//        List<OfferViewDTO> offerList = new ArrayList<>();
//
//        List<Offer> userOffers = this.offerRepository.findAllByUserIdNotLike();
//        return null;
//    }
}
