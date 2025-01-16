package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerOrderService {
    CustomerOrder createOrder(String customerEmail, String customerAddress, List<OrderItem> items);
    void addOrderItem(Long orderId, OrderItem item);
    void removeOrderItem(Long orderId, OrderItem item);
    double getOrderTotal(Long orderId);
    void processOrderForDelivery(Long orderId);
    void changeOrderDeliveryStatus(Long orderId, String status);
}
