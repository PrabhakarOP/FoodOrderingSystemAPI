package model;

import helper.IdGenerator;

import java.util.ArrayList;

public class Restaurant {
    private String id= IdGenerator.generateRestaurantId();
    private String ownerId;
    private String name;
    private String address;
    private String phone;
    private ArrayList<FoodItem>  foodItems = new ArrayList<>();

    //constructor

    public Restaurant(String ownerId, String name, String address, String phone) {
        this.ownerId = ownerId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    //getter setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}
