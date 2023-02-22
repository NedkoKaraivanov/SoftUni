package bg.softuni.resellerapp.service;

import bg.softuni.resellerapp.model.dto.AddOfferDTO;
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

        User user = this.userRepository.findById(this.userSession.getId()).get();
        user.getOffers().add(offer);
        this.userRepository.save(user);
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

        return this.offerRepository.findAllByCreator_IdNot(userSession.getId())
                .stream()
                .map(offer -> new OfferViewDTO()
                        .setId(offer.getId())
                        .setCreatorUsername(offer.getCreator().getUsername())
                        .setPrice(offer.getPrice())
                        .setCondition(offer.getCondition().getConditionName().name())
                        .setDescription(offer.getDescription()))
                .collect(Collectors.toSet());
    }
}
