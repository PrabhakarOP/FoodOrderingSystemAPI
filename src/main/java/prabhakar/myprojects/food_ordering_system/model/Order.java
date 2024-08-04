package prabhakar.myprojects.food_ordering_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Integer customerId;
    private Integer restaurantId;
    @ManyToMany(mappedBy = "orders")
    private List<FoodItem> foodItems = new ArrayList<>();
    private float totalPrice;
    private String status="PENDING";
}
