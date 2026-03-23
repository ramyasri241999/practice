package com.epam.practice.service;

import java.util.List;
import java.util.Optional;

import com.epam.practice.dto.Order;

public interface OrderService {

    Order createOrder(Order order);
    
    Order createOrder2(Order order);

    Optional<Order> getOrderById(Long id);

    List<Order> getAllOrders();

    Order updateOrder(Long id, Order updated);

    void deleteOrder(Long id);
    
    List<Order> getOrdersByStatus(String status);
    
    List<Order> totalNumberGreaterThan(String totalAmount);
    
    String updateStatusById(Long id, String status);

}
