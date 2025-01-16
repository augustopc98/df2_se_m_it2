package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    public CustomerOrder createOrder(String customerEmail, String customerAddress, List<OrderItem> items) {
        CustomerOrder order = new CustomerOrder();
        order.setCustomerEmail(customerEmail);
        order.setCustomerAddress(customerAddress);
        order.setItems(items);
        order.setOrderDate(new java.util.Date());
        order.setDeliveryStatus("Pending");
        return customerOrderRepository.save(order);
    }

    @Override
    public void addOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        item.setCustomerOrder(order);
        order.getItems().add(item);
        customerOrderRepository.save(order);
    }

    @Override
    public void removeOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.getItems().removeIf(orderItem -> orderItem.getId().equals(item.getId()));
        customerOrderRepository.save(order);
    }

    @Override
    public double getOrderTotal(Long orderId) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        return order.calculateTotal();
    }

    @Override
    public void processOrderForDelivery(Long orderId) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.updateDeliveryStatus("Processing");
        customerOrderRepository.save(order);
    }

    @Override
    public void changeOrderDeliveryStatus(Long orderId, String status) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.updateDeliveryStatus(status);
        customerOrderRepository.save(order);
    }
}
