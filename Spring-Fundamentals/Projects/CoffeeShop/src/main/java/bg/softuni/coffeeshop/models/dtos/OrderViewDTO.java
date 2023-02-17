package bg.softuni.coffeeshop.models.dtos;

import bg.softuni.coffeeshop.models.Category;

import java.math.BigDecimal;

public class OrderViewDTO {

    private Long id;

    private String name;

    private BigDecimal price;

    private Category category;

    public Long getId() {
        return id;
    }

    public OrderViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderViewDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderViewDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public OrderViewDTO setCategory(Category category) {
        this.category = category;
        return this;
    }
}
