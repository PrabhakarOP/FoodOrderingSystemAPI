package prabhakar.myprojects.food_ordering_system.model.request.foodItemRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class AddFoodItemRequest {
    Integer restaurantId;
    String name;
    String description;
    float price;
}
