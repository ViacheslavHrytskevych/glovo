package com.example.glovo.controller;

import com.example.glovo.model.Order;
import com.example.glovo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public boolean save(@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Integer id) {
        return orderService.getById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public boolean removeById(@PathVariable Integer id) {
        return orderService.removeById(id);
    }

    @PostMapping("/{orderId}/product/{productId}")
    public ResponseEntity<String> addProductById(
            @PathVariable Integer orderId,
            @PathVariable Integer productId) {
        boolean result = orderService.addProductById(orderId, productId);
        if (result) {
            return ResponseEntity.ok("Product added successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add product.");
        }
    }
}
