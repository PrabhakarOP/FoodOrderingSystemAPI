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
