package prabhakar.myprojects.food_ordering_system.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity @Getter @Setter
public class FoodItem {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    //private Integer restaurantId;
    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;
    private String name;
    private String description;
    private float price;
    private boolean availability=true;

    @ManyToMany
    @JsonIgnore
    private List<Order> orders;
}
