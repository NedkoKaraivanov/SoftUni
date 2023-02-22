package bg.softuni.resellerapp.model.dto;

import bg.softuni.resellerapp.model.enums.ConditionEnum;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class AddOfferDTO {

    @Size(min = 2, max = 50)
    private String description;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotBlank
    private String condition;

    public String getDescription() {
        return description;
    }

    public AddOfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public AddOfferDTO setCondition(String condition) {
        this.condition = condition;
        return this;
    }
}
