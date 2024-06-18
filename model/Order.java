package model;

import java.util.ArrayList;

public class Order {
    private String id;
    private String customerId;
    private String rataurantId;
    private ArrayList<FoodItem> foodItems = new ArrayList<>();
    private float totalPrice;
    private String status;

    //constructor

    public Order(String customerId, String rataurantId, ArrayList<FoodItem> foodItems) {
        this.customerId = customerId;
        this.rataurantId = rataurantId;
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

    public String getRataurantId() {
        return rataurantId;
    }

    public void setRataurantId(String rataurantId) {
        this.rataurantId = rataurantId;
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
}
