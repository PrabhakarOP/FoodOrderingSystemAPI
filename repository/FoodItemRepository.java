package repository;

import model.FoodItem;

import java.util.ArrayList;

public interface FoodItemRepository {
    ArrayList<FoodItem> foodItemList=new ArrayList<>();

    void save(FoodItem foodItem);
    FoodItem findByRestaurantId(String restaurantId);
}
