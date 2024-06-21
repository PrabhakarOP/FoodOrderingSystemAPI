package repository.impl;

import model.FoodItem;
import repository.FoodItemRepository;

import java.util.ArrayList;

public class FoodItemRepositoryImpl implements FoodItemRepository {
    public void save(FoodItem foodItem) {
        foodItemList.add(foodItem);
    }

    public ArrayList<FoodItem> findByRestaurantId(String restaurantId) {

        ArrayList<FoodItem> theRestaurantFoodItems=new ArrayList<>();

        for(FoodItem x: foodItemList){
            if(x.getRestaurantId().equals(restaurantId))
                theRestaurantFoodItems.add(x);
        }

        if(theRestaurantFoodItems.isEmpty())
            return null;
        return theRestaurantFoodItems;
    }

    public FoodItem findByFoodItemId(String foodItemId) {
        for(FoodItem foodItem: foodItemList){
            if(foodItem.getId().equals(foodItemId))
                return foodItem;
        }
        return null;
    }


    public void updateFoodItem(String foodItemId, FoodItem foodItem) {
        int index=0;
        for(FoodItem x: foodItemList){
            if(x.getId().equals(foodItemId))
                break;
            index++;
        }
        foodItemList.set(index,foodItem);
    }


    public void deleteFoodItem(FoodItem foodItem) {
        foodItemList.remove(foodItem);
    }
}
