package repository.impl;

import model.Restaurant;
import repository.RestaurantRepository;

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

    public Restaurant findByOwnerId(String ownerId) {
        for(Restaurant restaurant: restaurantList){
            if(restaurant.getOwnerId().equals(ownerId))
                return restaurant;
        }
        return null;
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
}
