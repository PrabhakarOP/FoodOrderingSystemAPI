package repository.impl;

import model.Restaurant;
import repository.RestaurantRepository;

import java.util.ArrayList;

public class RestaurantRepositoryImpl implements RestaurantRepository {
    //singleton design pattern
    private static RestaurantRepositoryImpl instance;

    public static synchronized RestaurantRepositoryImpl getInstance(){
        if(instance==null)
            instance=new RestaurantRepositoryImpl();
        return instance;
    }

    public void save(Restaurant restaurant) {
        restaurantList.add(restaurant);
    }

    public ArrayList<Restaurant> findByOwnerId(String ownerId) {
        ArrayList<Restaurant> theOwnerRestaurants=new ArrayList<>();

        for(Restaurant restaurant: restaurantList){
            if(restaurant.getOwnerId().equals(ownerId))
                theOwnerRestaurants.add(restaurant);
        }

        return theOwnerRestaurants;
    }

    public Restaurant findByRestaurantId(String restaurantId) {
        for(Restaurant restaurant: restaurantList){
            if(restaurant.getId().equals(restaurantId))
                return restaurant;
        }
        return null;
    }

    public void deleteRestaurant(Restaurant restaurant) {
        restaurantList.remove(restaurant);
    }


    public void updateRestaurant(String restaurantId, Restaurant restaurant) {
        int index=0;
        for(Restaurant x: restaurantList){
            if(x.getId().equals(restaurantId))
                break;
            index++;
        }
        restaurantList.set(index,restaurant);
    }


    public ArrayList<Restaurant> getAllRestaurants() {
        return restaurantList;
    }


    public Restaurant findByPhoneNumber(String phoneNumber) {
        for(Restaurant restaurant: restaurantList){
            if(restaurant.getPhone().equals(phoneNumber))
                return restaurant;
        }
        return null;
    }
}
