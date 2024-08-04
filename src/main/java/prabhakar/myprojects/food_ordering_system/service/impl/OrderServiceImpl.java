package prabhakar.myprojects.food_ordering_system.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import prabhakar.myprojects.food_ordering_system.model.Order;
import prabhakar.myprojects.food_ordering_system.model.request.orderRequests.PlaceOrderRequest;
import prabhakar.myprojects.food_ordering_system.repository.OrderRepository;
import prabhakar.myprojects.food_ordering_system.service.OrderService;

import java.util.List;
import java.util.NoSuchElementException;

public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public boolean placeOrder(PlaceOrderRequest placeOrderRequest) {
        Order order=new Order();
        order.setCustomerId(placeOrderRequest.getCustomerId());
        order.setRestaurantId(placeOrderRequest.getRestaurantId());
        order.getFoodItems().addAll(placeOrderRequest.getFoodItems());
        order.setTotalPrice(placeOrderRequest.getTotalPrice());

        orderRepository.save(order);
        return true;
    }

    @Override
    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(Integer.valueOf(customerId));
    }

    @Override
    public List<Order> getOrdersByRestaurantId(String restaurantId) {
        return orderRepository.findByRestaurantId(Integer.valueOf(restaurantId));
    }

    @Override
    public List<Order> getPendingOrdersByRestaurantId(String restaurantId) {
        return orderRepository.findByRestaurantIdAndStatus(Integer.valueOf(restaurantId),"PENDING");
    }

    @Override
    public List<Order> getInProgressOrdersByRestaurantId(String restaurantId) {
        return orderRepository.findByRestaurantIdAndStatus(Integer.valueOf(restaurantId),"IN_PROGRESS");
    }

    @Override
    public List<Order> getCompletedOrdersByRestaurantId(String restaurantId) {
        return orderRepository.findByRestaurantIdAndStatus(Integer.valueOf(restaurantId),"COMPLETED");
    }

    @Override
    public boolean updateOrderStatus(String orderId, String status) {
        Order order;
        try{
            order=orderRepository.findById(Integer.valueOf(orderId)).get();
        }catch (NoSuchElementException e){
            return false;
        }

        order.setStatus(status);
        orderRepository.save(order);
        return true;
    }
}
