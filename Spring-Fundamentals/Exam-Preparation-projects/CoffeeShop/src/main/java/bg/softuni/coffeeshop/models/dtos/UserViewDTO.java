package bg.softuni.coffeeshop.models.dtos;

public class UserViewDTO {

    private String username;

    private Integer countOfOrders;

    public String getUsername() {
        return username;
    }

    public UserViewDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getCountOfOrders() {
        return countOfOrders;
    }

    public UserViewDTO setCountOfOrders(Integer countOfOrders) {
        this.countOfOrders = countOfOrders;
        return this;
    }
}
