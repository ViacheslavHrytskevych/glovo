package com.example.glovo.service;

import com.example.glovo.model.Order;
import com.example.glovo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderService {

    private final ProductService productService;

    @Autowired
    public OrderService(ProductService productService) {
        this.productService = productService;
    }

    private final List<Order> orders = new ArrayList<>();
    private final Random random = new Random();

    public boolean save(Order order) {
        order.setId(random.nextInt());
        order.setDate(LocalDate.now());
        orders.add(order);
        return true;
    }

    public Optional<Order> getById(int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst();
    }

    public List<Order> getAll() {
        return orders;
    }

    public boolean removeById(int id) {
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean addProductById(int orderId, int productId) {
        Optional<Order> optionalOrder = getById(orderId);
        Optional<Product> optionalProduct = productService.getById(productId);

        if (optionalOrder.isPresent() && optionalProduct.isPresent()) {
            Order order = optionalOrder.get();
            Product product = optionalProduct.get();
            order.getProducts().add(product);
            return true;
        }

        return false;
    }
}
