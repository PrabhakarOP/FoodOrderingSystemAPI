package service.impl;

import helper.Message;
import model.Restaurant;
import service.RestaurantService;

import java.util.ArrayList;

public class RestaurantServiceImpl implements RestaurantService {
    //singleton design pattern
    private static RestaurantServiceImpl instance;

    public static synchronized RestaurantServiceImpl getInstance(){
        if(instance==null)
            instance=new RestaurantServiceImpl();
        return instance;
    }

    public boolean createRestaurant(Restaurant restaurant) {
        if(restaurantRepo.findByPhoneNumber(restaurant.getPhone())!=null){
            Message.message= "A restaurant with this Phone Number already exist";
            return false;
        }
        restaurantRepo.save(restaurant);
        return true;
    }

    public boolean updateRestaurant(String restaurantId, Restaurant restaurant) {
        Restaurant restaurant1=restaurantRepo.findByRestaurantId(restaurantId);
        if(restaurant1==null)
            return false;
        else{
           restaurantRepo.updateRestaurant(restaurantId,restaurant);
           return true;
        }
    }

    public boolean deleteRestaurant(String restaurantId) {
        Restaurant restaurant=restaurantRepo.findByRestaurantId(restaurantId);
        if(restaurant==null)
            return false;

        restaurantRepo.deleteRestaurant(restaurant);
        return true;
    }

    public ArrayList<Restaurant> getRestaurantsByOwnerId(String ownerId) {
        return restaurantRepo.findByOwnerId(ownerId);
    }


    public ArrayList<Restaurant> getAllRestaurants() {
        return restaurantRepo.getAllRestaurants();
    }


    public Restaurant getRestaurantByRestaurantId(String restaurantId) {
        return restaurantRepo.findByRestaurantId(restaurantId);
    }


    public Restaurant getRestaurantByPhoneNumber(String phoneNumber) {
        return restaurantRepo.findByPhoneNumber(phoneNumber);
    }
}
