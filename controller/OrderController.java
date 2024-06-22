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
    public boolean updateOrderStatus(String orderId,String status){
        return orderService.updateOrderStatus(orderId,status);
    }
}
