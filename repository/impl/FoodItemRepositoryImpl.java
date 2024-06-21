package repository.impl;

import model.FoodItem;
import repository.FoodItemRepository;

import java.util.ArrayList;

public class FoodItemRepositoryImpl implements FoodItemRepository {
    public void save(FoodItem foodItem) {
        foodItemList.add(foodItem);
    }

    public FoodItem findByRestaurantId(String restaurantId) {
        for (FoodItem foodItem : foodItemList) {
            if (foodItem.getRestaurantId().equals(restaurantId))
                return foodItem;
        }
        return null;
    }

    public FoodItem findByFoodItemId(String foodItemId) {
        for(FoodItem foodItem: foodItemList){
            if(foodItem.getId().equals(foodItemId))
                return foodItem;
        }
        return null;
    }
}
