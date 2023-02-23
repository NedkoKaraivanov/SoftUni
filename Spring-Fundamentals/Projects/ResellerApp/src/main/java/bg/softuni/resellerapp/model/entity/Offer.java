package bg.softuni.resellerapp.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    private Condition condition;

    @ManyToOne
    private User creator;

    @ManyToOne
    private User buyer;

    public String getDescription() {
        return description;
    }

    public Offer setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Offer setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Condition getCondition() {
        return condition;
    }

    public Offer setCondition(Condition condition) {
        this.condition = condition;
        return this;
    }

    public User getCreator() {
        return creator;
    }

    public Offer setCreator(User creator) {
        this.creator = creator;
        return this;
    }

    public User getBuyer() {
        return buyer;
    }

    public Offer setBuyer(User buyer) {
        this.buyer = buyer;
        return this;
    }
}
