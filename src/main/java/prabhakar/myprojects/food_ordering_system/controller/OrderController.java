package prabhakar.myprojects.food_ordering_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prabhakar.myprojects.food_ordering_system.model.Order;
import prabhakar.myprojects.food_ordering_system.model.request.orderRequests.PlaceOrderRequest;
import prabhakar.myprojects.food_ordering_system.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order/v1/api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/placeOrder")
    public boolean placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest){
        return orderService.placeOrder(placeOrderRequest);
    }

    @GetMapping("/getOrdersByCustomerId")
    public List<Order> getOrdersByCustomerId(@RequestParam String customerId){
        return orderService.getOrdersByCustomerId(customerId);
    }

    @GetMapping("/getOrdersByRestaurantId")
    public List<Order> getOrdersByRestaurantId(@RequestParam String restaurantId){
        return orderService.getOrdersByRestaurantId(restaurantId);
    }

    @GetMapping("/getPendingOrdersByRestaurantId")
    public List<Order> getPendingOrdersByRestaurantId(@RequestParam String restaurantId){
        return orderService.getPendingOrdersByRestaurantId(restaurantId);
    }

    @GetMapping("/getInProgressOrdersByRestaurantId")
    public List<Order> getInProgressOrdersByRestaurantId(@RequestParam String restaurantId){
        return orderService.getInProgressOrdersByRestaurantId(restaurantId);
    }

    @GetMapping("/getCompletedOrdersByRestaurantId")
    public List<Order> getCompletedOrdersByRestaurantId(@RequestParam String restaurantId){
        return orderService.getCompletedOrdersByRestaurantId(restaurantId);
    }

    @PutMapping("/updateOrderStatus")
    public boolean updateOrderStatus(@RequestParam String orderId,@RequestParam String status){
        return orderService.updateOrderStatus(orderId,status);
    }
}
