package controller;

import model.FoodItem;
import service.impl.FoodItemServiceImpl;

import java.util.ArrayList;

public class FoodItemController {
    FoodItemServiceImpl foodItemService=FoodItemServiceImpl.getInstance();

    //singleton design pattern
    private static FoodItemController instance;

    public static synchronized FoodItemController getInstance(){
        if(instance==null)
            instance=new FoodItemController();
        return instance;
    }

    public boolean addFoodItem(String restaurantId, FoodItem foodItem){
        return foodItemService.addFoodItem(restaurantId,foodItem);
    }
    public boolean updateFoodItem(String foodItemId,FoodItem foodItem){
        return foodItemService.updateFoodItem(foodItemId,foodItem);
    }
    public boolean deleteFoodItem(String foodItemId){
        return foodItemService.deleteFoodItem(foodItemId);
    }
    ArrayList<FoodItem> getFoodItemsByRestaurantId(String restaurantId){
        return foodItemService.getFoodItemsByRestaurantId(restaurantId);
    }

    public boolean updateFoodItemAvailability(String foodItemId,boolean availability){
        return foodItemService.updateFoodItemAvailability(foodItemId,availability);
    }
}
