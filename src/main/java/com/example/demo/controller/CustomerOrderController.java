package com.example.demo.controller;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-orders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping
    public CustomerOrder createOrder(@RequestBody CustomerOrder order) {
        return customerOrderService.createOrder(order.getCustomerEmail(), order.getCustomerAddress(), order.getItems());
    }

    @GetMapping("/{id}/total")
    public double getOrderTotal(@PathVariable Long id) {
        return customerOrderService.getOrderTotal(id);
    }

    @PostMapping("/{id}/add-item")
    public void addOrderItem(@PathVariable Long id, @RequestBody OrderItem item) {
        customerOrderService.addOrderItem(id, item);
    }

    @PutMapping("/{id}/status")
    public void updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        customerOrderService.changeOrderDeliveryStatus(id, status);
    }
}
