package service.impl;

import helper.Message;
import model.FoodItem;
import model.Restaurant;
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
        RestaurantServiceImpl restaurantService=RestaurantServiceImpl.getInstance();
        Restaurant selectedRestaurant=restaurantService.getRestaurantByRestaurantId(restaurantId);
        //check if foodItem already exist
        for(FoodItem x: selectedRestaurant.getFoodItems()){
            if(x.getName().equalsIgnoreCase(foodItem.getName())){
                Message.message="A food item with this name already exist";
                return false;
            }
        }

        //add foodItem to foodItem repository
        foodItemRepo.save(foodItem);
        //add this food item to the restaurant
        selectedRestaurant.getFoodItems().add(foodItem);
        return true;
    }

    public boolean updateFoodItem(String foodItemId, FoodItem updatedFoodItem) {

        RestaurantServiceImpl restaurantService=RestaurantServiceImpl.getInstance();
        FoodItem oldFoodItem=foodItemRepo.findByFoodItemId(foodItemId);

        if(oldFoodItem==null){
            Message.message="Selected food item not found";
            return false;
        }
        //get list of foodItems in the restaurant
        ArrayList<FoodItem> foodItemsInTheRestaurant=restaurantService.getRestaurantByRestaurantId(oldFoodItem.getRestaurantId()).getFoodItems();
        //check for same name food item
        for(FoodItem f: foodItemsInTheRestaurant){
            if(f.getName().equalsIgnoreCase(updatedFoodItem.getName())){
                Message.message="A food item with this name already exist in the restaurant";
                return false;
            }
        }


        //update foodItem to foodItem repository
        foodItemRepo.updateFoodItem(foodItemId,updatedFoodItem);
        //update this food item to the restaurant
        Restaurant selectedRestaurant=restaurantService.getRestaurantByRestaurantId(oldFoodItem.getRestaurantId());
        int index=0;
        for(FoodItem f: selectedRestaurant.getFoodItems()){
            if(f.getId().equalsIgnoreCase(updatedFoodItem.getId()))
                break;
            index++;
        }
        selectedRestaurant.getFoodItems().set(index,updatedFoodItem);
        return true;

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
        return theRestaurantFoodItems;
    }


    public boolean updateFoodItemAvailability(String foodItemId, boolean availability) {
        //fetch foodItem
        FoodItem foodItem=foodItemRepo.findByFoodItemId(foodItemId);
        if(foodItem==null){
            Message.message="No such food Item found";
            return false;
        }

        //update availability
        foodItem.setAvailability(availability);

        //update foodItem
        updateFoodItem(foodItemId,foodItem);

        return true;
    }
}
