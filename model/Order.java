package model;

import helper.IdGenerator;

import java.util.ArrayList;

public class Order {
    private String id= IdGenerator.generateOrderId();
    private String customerId;
    private String restaurantId;
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private float totalPrice=calculateTotalPrice();
    private String status="PENDING";

    //constructor

    public Order(String customerId, String restaurantId, ArrayList<FoodItem> foodItems) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.foodItems = foodItems;
    }

    //getter setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //behavior
    float calculateTotalPrice(){
        float totalPrice=0;
        for(FoodItem f: foodItems){
            totalPrice+=f.getPrice();
        }
        return totalPrice;
    }
}
