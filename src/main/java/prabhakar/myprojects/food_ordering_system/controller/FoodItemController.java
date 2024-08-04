package prabhakar.myprojects.food_ordering_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prabhakar.myprojects.food_ordering_system.model.FoodItem;
import prabhakar.myprojects.food_ordering_system.model.request.foodItemRequests.AddFoodItemRequest;
import prabhakar.myprojects.food_ordering_system.model.request.foodItemRequests.UpdateFoodItemRequest;
import prabhakar.myprojects.food_ordering_system.service.FoodItemService;

import java.util.List;

@RestController
@RequestMapping("/foodItem/v1/api")
public class FoodItemController {
    @Autowired
    FoodItemService foodItemService;

    @PostMapping("/addFoodItem")
    public boolean addFoodItem(@RequestParam String restaurantId,@RequestBody AddFoodItemRequest addFoodItemRequest){
        return foodItemService.addFoodItem(restaurantId,addFoodItemRequest);
    }

    @PutMapping("/updateFoodItem")
    public boolean updateFoodItem(@RequestParam String foodItemId,@RequestBody UpdateFoodItemRequest updateFoodItemRequest){
        return foodItemService.updateFoodItem(foodItemId,updateFoodItemRequest);
    }

    @DeleteMapping("/deleteFoodItem")
    public boolean deleteFoodItem(@RequestParam String foodItemId){
        return foodItemService.deleteFoodItem(foodItemId);
    }

    @GetMapping("/getFoodItemsByRestaurantId")
    List<FoodItem> getFoodItemsByRestaurantId(@RequestParam String  restaurantId){
        return foodItemService.getFoodItemsByRestaurantId(restaurantId);
    }

    @PutMapping("/updateFoodItemAvailability")
    public boolean updateFoodItemAvailability(@RequestParam String foodItemId,@RequestParam boolean availability){
        return foodItemService.updateFoodItemAvailability(foodItemId,availability);
    }

    @GetMapping("/getAvailableFoodItemsByRestaurantId")
    public List<FoodItem> getAvailableFoodItemsByRestaurantId(@RequestParam String restaurantId){
        return foodItemService.getAvailableFoodItemsByRestaurantId(restaurantId);
    }
}
