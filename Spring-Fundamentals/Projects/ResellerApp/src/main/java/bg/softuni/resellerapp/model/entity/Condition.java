package bg.softuni.resellerapp.model.entity;

import bg.softuni.resellerapp.model.enums.ConditionEnum;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ConditionEnum conditionName;

    @Column(nullable = false)
    private String description;

    public ConditionEnum getConditionName() {
        return conditionName;
    }

    public Condition setConditionName(ConditionEnum conditionName) {
        this.conditionName = conditionName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Condition setDescription(String description) {
        this.description = description;
        return this;
    }
}
