package repository;

import model.FoodItem;

import java.util.ArrayList;

public interface FoodItemRepository {
    ArrayList<FoodItem> foodItemList=new ArrayList<>();

    void save(FoodItem foodItem);
    ArrayList<FoodItem> findByRestaurantId(String restaurantId);

    //***********extra functions not mentioned in assignment***********

    FoodItem findByFoodItemId(String foodItemId);
    ArrayList<FoodItem> findAvailableFoodItemByRestaurantId(String restaurantId);
    void updateFoodItem(String foodItemId,FoodItem foodItem);
    void deleteFoodItem(FoodItem foodItem);
}
