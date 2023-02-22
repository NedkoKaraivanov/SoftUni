package bg.softuni.resellerapp.repository;

import bg.softuni.resellerapp.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {


//    List<Offer> findAllByUserIdNotLike(Long id);
}
