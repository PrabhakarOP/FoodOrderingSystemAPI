package repository.impl;

import model.Restaurant;
import repository.RestaurantRepository;

public class RestaurantRepositoryImpl implements RestaurantRepository {
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
}
