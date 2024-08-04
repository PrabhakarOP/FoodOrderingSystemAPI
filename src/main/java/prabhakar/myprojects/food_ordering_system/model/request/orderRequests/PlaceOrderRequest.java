package prabhakar.myprojects.food_ordering_system.model.request.orderRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import prabhakar.myprojects.food_ordering_system.model.FoodItem;

import java.util.List;
@Getter  @Setter @AllArgsConstructor
public class PlaceOrderRequest {
    private Integer customerId;
    private Integer restaurantId;
    private List<FoodItem> foodItems;
    private float totalPrice;
}
