package service;

import model.FoodItem;
import repository.impl.FoodItemRepositoryImpl;

import java.util.ArrayList;

public interface FoodItemService {
    FoodItemRepositoryImpl foodItemRepo=FoodItemRepositoryImpl.getInstance();

    boolean addFoodItem(String restaurantId, FoodItem foodItem);
    boolean updateFoodItem(String foodItemId,FoodItem foodItem);
    boolean deleteFoodItem(String foodItemId);
    ArrayList<FoodItem> getFoodItemsByRestaurantId(String restaurantId);
}
