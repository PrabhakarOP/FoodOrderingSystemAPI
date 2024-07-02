package controller;

import model.Order;
import service.impl.OrderServiceImpl;

import java.util.ArrayList;

public class OrderController {
    OrderServiceImpl orderService= OrderServiceImpl.getInstance();

    //singleton design pattern
    private static OrderController instance;

    public static synchronized OrderController getInstance(){
        if(instance==null)
            instance=new OrderController();
        return instance;
    }

    public boolean placeOrder(Order order){
        return orderService.placeOrder(order);
    }
    public ArrayList<Order> getOrdersByCustomerId(String customerId){
        return orderService.getOrdersByCustomerId(customerId);
    }
    public ArrayList<Order> getOrdersByRestaurantId(String restaurantId){
        return orderService.getOrdersByRestaurantId(restaurantId);
    }
    public ArrayList<Order> getPendingOrdersByRestaurantId(String restaurantId){
        return orderService.getPendingOrdersByRestaurantId(restaurantId);
    }
    public ArrayList<Order> getInProgressOrdersByRestaurantId(String restaurantId){
        return orderService.getInProgressOrdersByRestaurantId(restaurantId);
    }
    public ArrayList<Order> getCompletedOrdersByRestaurantId(String restaurantId){
        return orderService.getCompletedOrdersByRestaurantId(restaurantId);
    }

    public boolean updateOrderStatus(String orderId,String status){
        return orderService.updateOrderStatus(orderId,status);
    }
}
