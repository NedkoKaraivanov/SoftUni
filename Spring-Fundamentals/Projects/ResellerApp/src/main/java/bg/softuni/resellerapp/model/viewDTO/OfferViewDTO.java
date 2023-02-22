package bg.softuni.resellerapp.model.viewDTO;

import java.math.BigDecimal;

public class OfferViewDTO {

    private String description;

    private BigDecimal price;

    private String condition;

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
}
