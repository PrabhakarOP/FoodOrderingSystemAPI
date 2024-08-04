package prabhakar.myprojects.food_ordering_system.service;


import org.springframework.stereotype.Service;
import prabhakar.myprojects.food_ordering_system.model.FoodItem;
import prabhakar.myprojects.food_ordering_system.model.request.foodItemRequests.AddFoodItemRequest;
import prabhakar.myprojects.food_ordering_system.model.request.foodItemRequests.UpdateFoodItemRequest;

import java.util.List;

@Service
public interface FoodItemService {
    boolean addFoodItem(String  restaurantId, AddFoodItemRequest addFoodItemRequest);
    boolean updateFoodItem(String  foodItemId, UpdateFoodItemRequest updateFoodItemRequest);
    boolean deleteFoodItem(String  foodItemId);
    List<FoodItem> getFoodItemsByRestaurantId(String  restaurantId);
    List<FoodItem> getAvailableFoodItemsByRestaurantId(String  restaurantId);
    boolean updateFoodItemAvailability(String  foodItemId,boolean availability);
}
