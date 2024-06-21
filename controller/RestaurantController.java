package controller;

import model.Restaurant;
import service.impl.RestaurantServiceImpl;

import java.util.ArrayList;

public class RestaurantController {
    RestaurantServiceImpl restaurantService= RestaurantServiceImpl.getInstance();

    boolean createRestaurant(Restaurant restaurant){
        return restaurantService.createRestaurant(restaurant);
    }
    boolean updateRestaurant(String restaurantId,Restaurant restaurant){
        return restaurantService.updateRestaurant(restaurantId,restaurant);
    }
    boolean deleteRestaurant(String restaurantId){
        return restaurantService.deleteRestaurant(restaurantId);
    }
    ArrayList<Restaurant> getRestaurantsByOwnerId(String ownerId){
        return restaurantService.getRestaurantsByOwnerId(ownerId);
    }
}
