package prabhakar.myprojects.food_ordering_system.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import prabhakar.myprojects.food_ordering_system.model.FoodItem;
import prabhakar.myprojects.food_ordering_system.model.Restaurant;
import prabhakar.myprojects.food_ordering_system.model.request.foodItemRequests.AddFoodItemRequest;
import prabhakar.myprojects.food_ordering_system.model.request.foodItemRequests.UpdateFoodItemRequest;
import prabhakar.myprojects.food_ordering_system.repository.FoodItemRepository;
import prabhakar.myprojects.food_ordering_system.service.FoodItemService;
import prabhakar.myprojects.food_ordering_system.service.RestaurantService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class FoodItemServiceImpl implements FoodItemService {
    @Autowired
    FoodItemRepository foodItemRepository;
    @Autowired
    RestaurantService restaurantService;

    @Override
    public boolean addFoodItem(String restaurnatId, AddFoodItemRequest addFoodItemRequest) {
        //fetch restaurant
        Restaurant selectedRestaurant = restaurantService.getRestaurantByRestaurantId(restaurnatId);

        //check if foodItem already exist
        for(FoodItem x: selectedRestaurant.getFoodItems()){
            if(x.getName().equalsIgnoreCase(addFoodItemRequest.getName()))
                return false;
        }

        //copy request to model
        FoodItem foodItem=new FoodItem();
       // foodItem.setRestaurantId(addFoodItemRequest.getRestaurantId());
        foodItem.setRestaurant(selectedRestaurant);
        foodItem.setName(addFoodItemRequest.getName());
        foodItem.setDescription(addFoodItemRequest.getDescription());
        foodItem.setPrice(addFoodItemRequest.getPrice());

        //add foodItem to foodItem repository
        return foodItemRepository.save(foodItem)!=null;
    }

    @Override
    public boolean updateFoodItem(String  foodItemId, UpdateFoodItemRequest updateFoodItemRequest) {
        //fetch old foodItem
         FoodItem foodItem=foodItemRepository.findById(Integer.valueOf(foodItemId)).get();

        //update old foodItem
        if(updateFoodItemRequest.getName()!=null) {
            foodItem.setName(updateFoodItemRequest.getName());

            //get list of foodItems in the restaurant
            List<FoodItem> foodItemInTheRestaurant=foodItemRepository.findByRestaurant(foodItem.getRestaurant());
            //check for the same name foodItem
            for(FoodItem x: foodItemInTheRestaurant){
                if(x.getName().equalsIgnoreCase(foodItem.getName()))
                    return false;
            }
        }

        if(updateFoodItemRequest.getPrice()!=0)
            foodItem.setPrice(updateFoodItemRequest.getPrice());

        if(updateFoodItemRequest.getDescription()!=null)
            foodItem.setDescription(updateFoodItemRequest.getDescription());

        //save updated foodItem
        return foodItemRepository.save(foodItem)!=null;
    }

    @Override
    public boolean deleteFoodItem(String  foodItemId) {
        FoodItem foodItem;
        try {
            foodItem = foodItemRepository.findById(Integer.valueOf(foodItemId)).get();
        }
        catch (NoSuchElementException e){
            return false;
        }
        foodItemRepository.delete(foodItem);
        return true;
    }

    @Override
    public List<FoodItem> getFoodItemsByRestaurantId(String  restaurantId) {
        return foodItemRepository.findByRestaurant(restaurantService.getRestaurantByRestaurantId(restaurantId));
    }

    @Override
    public List<FoodItem> getAvailableFoodItemsByRestaurantId(String  restaurantId) {
        return foodItemRepository.findByRestaurantIdAndAvailability(Integer.valueOf(restaurantId),true);
    }

    @Override
    public boolean updateFoodItemAvailability(String  foodItemId, boolean availability) {
        //fetch foodItem
        FoodItem foodItem;
        try {
            foodItem = foodItemRepository.findById(Integer.valueOf(foodItemId)).get();
        }
        catch (NoSuchElementException e){
            return false;
        }
        //update availability
        foodItem.setAvailability(availability);

        return foodItemRepository.save(foodItem)!=null;
    }
}
