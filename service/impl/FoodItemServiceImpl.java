package service.impl;

import model.FoodItem;
import service.FoodItemService;

import java.util.ArrayList;

public class FoodItemServiceImpl implements FoodItemService {
    //singleton design pattern
    private static FoodItemServiceImpl instance;

    public static synchronized FoodItemServiceImpl getInstance(){
        if(instance==null)
            instance=new FoodItemServiceImpl();
        return instance;
    }

    public boolean addFoodItem(String restaurantId, FoodItem foodItem) {
        foodItemRepo.save(foodItem);
        return true;
    }

    public boolean updateFoodItem(String foodItemId, FoodItem foodItem) {
        FoodItem foodItem1=foodItemRepo.findByFoodItemId(foodItemId);
        if(foodItem1==null)
            return false;
        else{
            foodItemRepo.updateFoodItem(foodItemId,foodItem);
            return true;
        }
    }

    public boolean deleteFoodItem(String foodItemId) {
        FoodItem foodItem=foodItemRepo.findByFoodItemId(foodItemId);
        if(foodItem==null)
            return false;
        foodItemRepo.deleteFoodItem(foodItem);
        return true;
    }

    public ArrayList<FoodItem> getFoodItemsByRestaurantId(String restaurantId) {
        ArrayList<FoodItem> theRestaurantFoodItems=foodItemRepo.findByRestaurantId(restaurantId);
        if(theRestaurantFoodItems.isEmpty())
            return null;
        return theRestaurantFoodItems;
    }
}
