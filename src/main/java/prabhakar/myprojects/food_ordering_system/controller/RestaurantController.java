package prabhakar.myprojects.food_ordering_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prabhakar.myprojects.food_ordering_system.model.Restaurant;
import prabhakar.myprojects.food_ordering_system.model.request.RestaurantRequests.CreateRestaurantRequest;
import prabhakar.myprojects.food_ordering_system.model.request.RestaurantRequests.UpdateRestaurantRequest;
import prabhakar.myprojects.food_ordering_system.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurant/v1/api")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/createRestaurant")
    public boolean createRestaurant(@RequestBody CreateRestaurantRequest createRestaurantRequest){
        return restaurantService.createRestaurant(createRestaurantRequest);
    }

    @PutMapping("/updateRestaurant")
    public boolean updateRestaurant(@RequestParam String restaurantId,@RequestBody UpdateRestaurantRequest updateRestaurantRequest){
        return restaurantService.updateRestaurant(restaurantId,updateRestaurantRequest);
    }

    @DeleteMapping("/deleteRestaurant")
    public boolean deleteRestaurant(@RequestParam String restaurantId){
        return restaurantService.deleteRestaurant(restaurantId);
    }

    @GetMapping("/getRestaurantsByOwnerId")
    public List<Restaurant> getRestaurantsByOwnerId(String ownerId){
        return restaurantService.getRestaurantsByOwnerId(ownerId);
    }

    @GetMapping("/getAllRestaurant")
    public List<Restaurant> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/getRestaurantByRestaurantId")
    public Restaurant getRestaurantByRestaurantId(@RequestParam String restaurantId){
        return restaurantService.getRestaurantByRestaurantId(restaurantId);
    }

    @GetMapping("/getRestaurantByPhoneNumber")
    public Restaurant getRestaurantByPhoneNumber(@RequestParam String phoneNumber){
        return restaurantService.getRestaurantByPhoneNumber(phoneNumber);
    }
}
