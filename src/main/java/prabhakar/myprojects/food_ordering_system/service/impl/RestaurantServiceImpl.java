package prabhakar.myprojects.food_ordering_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import prabhakar.myprojects.food_ordering_system.model.Restaurant;
import prabhakar.myprojects.food_ordering_system.model.request.RestaurantRequests.CreateRestaurantRequest;
import prabhakar.myprojects.food_ordering_system.model.request.RestaurantRequests.UpdateRestaurantRequest;
import prabhakar.myprojects.food_ordering_system.repository.RestaurantRepository;
import prabhakar.myprojects.food_ordering_system.service.RestaurantService;

import java.util.List;
import java.util.NoSuchElementException;

public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public boolean createRestaurant(CreateRestaurantRequest createRestaurantRequest) {
        //check if restaurant already exist by phoneNumber
        if(restaurantRepository.findByPhoneNumber(createRestaurantRequest.getPhoneNumber())!=null)
            return false;

        Restaurant restaurant=new Restaurant();
        restaurant.setOwnerId(createRestaurantRequest.getOwnerId());
        restaurant.setName(createRestaurantRequest.getName());
        restaurant.setAddress(createRestaurantRequest.getAddress());
        restaurant.setPhoneNumber(createRestaurantRequest.getPhoneNumber());
        return restaurantRepository.save(restaurant)!=null;
    }

    @Override
    public boolean updateRestaurant(String restaurantId, UpdateRestaurantRequest updateRestaurantRequest) {
        Restaurant restaurant;
        try{
            restaurant=restaurantRepository.findById(Integer.valueOf(restaurantId)).get();
        }catch (NoSuchElementException e){
            return false;
        }
        //check if same phone nummber already registered or not
        if(updateRestaurantRequest.getPhoneNumber()!=null && !updateRestaurantRequest.getPhoneNumber().equalsIgnoreCase(restaurant.getPhoneNumber())){
            if(restaurantRepository.findByPhoneNumber(updateRestaurantRequest.getPhoneNumber())!=null)
                return false;
            else
                restaurant.setPhoneNumber(updateRestaurantRequest.getPhoneNumber()); //update phoneNumber
        }
        //update the restaurant
        if(updateRestaurantRequest.getName()!=null)
            restaurant.setName(updateRestaurantRequest.getName());
        if(updateRestaurantRequest.getAddress()!=null)
            restaurant.setAddress(updateRestaurantRequest.getAddress());

        return restaurantRepository.save(restaurant)!=null;
    }

    @Override
    public boolean deleteRestaurant(String restaurantId) {
        //fetch restaurant
        Restaurant restaurant;
        try{
            restaurant=restaurantRepository.findById(Integer.valueOf(restaurantId)).get();
        }catch (NoSuchElementException e){
            return false;
        }

        restaurantRepository.delete(restaurant);
        return true;
    }

    @Override
    public List<Restaurant> getRestaurantsByOwnerId(String ownerId) {
        return restaurantRepository.findByOwnerId(Integer.valueOf(ownerId));
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantByRestaurantId(String restaurantId) {
        Restaurant restaurant;
        try{
            restaurant=restaurantRepository.findById(Integer.valueOf(restaurantId)).get();
        }catch (NoSuchElementException e){
            return null;
        }
        return  restaurant;
    }

    @Override
    public Restaurant getRestaurantByPhoneNumber(String phoneNumber) {
        return restaurantRepository.findByPhoneNumber(phoneNumber);
    }
}
