package model;

import helper.IdGenerator;

public class FoodItem {
    private String id;
    private String restaurantId;
    private String name;
    private String description;
    private float price;
    private boolean availability=true;
    //constructor
    public FoodItem(String restaurantId, String name, String description, float price) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.id= IdGenerator.generateFoodItemId();
    }

    public FoodItem(FoodItem foodItem){
        this.id=foodItem.getId();
        this.restaurantId= foodItem.getRestaurantId();
        this.name= foodItem.getName();
        this.description= foodItem.getDescription();
        this.price= foodItem.getPrice();
        this.availability= foodItem.isAvailabel();
    }

    //getter setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isAvailabel() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
