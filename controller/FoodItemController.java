package controller;

import model.FoodItem;
import service.impl.FoodItemServiceImpl;

import java.util.ArrayList;

public class FoodItemController {
    FoodItemServiceImpl foodItemService=FoodItemServiceImpl.getInstance();

    boolean addFoodItem(String restaurantId, FoodItem foodItem){
        return foodItemService.addFoodItem(restaurantId,foodItem);
    }
    boolean updateFoodItem(String foodItemId,FoodItem foodItem){
        return foodItemService.updateFoodItem(foodItemId,foodItem);
    }
    boolean deleteFoodItem(String foodItemId){
        return foodItemService.deleteFoodItem(foodItemId);
    }
    ArrayList<FoodItem> getFoodItemsByRestaurantId(String restaurantId){
        return foodItemService.getFoodItemsByRestaurantId(restaurantId);
    }
}
