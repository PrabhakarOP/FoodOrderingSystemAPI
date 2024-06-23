package controller;

import model.Restaurant;
import service.impl.RestaurantServiceImpl;

import java.util.ArrayList;

public class RestaurantController {
    RestaurantServiceImpl restaurantService= RestaurantServiceImpl.getInstance();

    //singleton design pattern
    private static RestaurantController instance;

    public static synchronized RestaurantController getInstance(){
        if(instance==null)
            instance=new RestaurantController();
        return instance;
    }

    public boolean createRestaurant(Restaurant restaurant){
        return restaurantService.createRestaurant(restaurant);
    }
    public boolean updateRestaurant(String restaurantId,Restaurant restaurant){
        return restaurantService.updateRestaurant(restaurantId,restaurant);
    }
    public boolean deleteRestaurant(String restaurantId){
        return restaurantService.deleteRestaurant(restaurantId);
    }
    public ArrayList<Restaurant> getRestaurantsByOwnerId(String ownerId){
        return restaurantService.getRestaurantsByOwnerId(ownerId);
    }
    public ArrayList<Restaurant> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }

    public Restaurant getRestaurantByRestaurantId(String restaurantId){
        return restaurantService.getRestaurantByRestaurantId(restaurantId);
    }
}
