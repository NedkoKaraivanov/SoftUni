package bg.softuni.resellerapp.model.viewDTO;

import java.math.BigDecimal;

public class OfferViewDTO {

    private Long id;
    private String description;

    private BigDecimal price;

    private String condition;

    private String creatorUsername;

    public Long getId() {
        return id;
    }

    public OfferViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferViewDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferViewDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public OfferViewDTO setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public OfferViewDTO setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
        return this;
    }
}
