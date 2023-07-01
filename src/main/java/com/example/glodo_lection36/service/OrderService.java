package com.example.glodo_lection36.service;

import com.example.glodo_lection36.convertor.OrderConvertor;
import com.example.glodo_lection36.dto.OrderDto;
import com.example.glodo_lection36.entity.Order;
import com.example.glodo_lection36.entity.Product;
import com.example.glodo_lection36.repository.OrderRepository;
import com.example.glodo_lection36.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderDto getById(int id) {
        return orderRepository.findById(id)
                .map(order -> OrderConvertor.toOrderDto(order, productRepository.findAllByOrderId(id)))
                .orElseThrow();
    }

    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .map(order -> OrderConvertor.toOrderDto(order, productRepository.findAllByOrderId(order.getId())))
                .toList();
    }

    public OrderDto save(OrderDto orderDto) {
        Order order = Order.builder()
                .date(Date.valueOf(LocalDate.now()))
                .build();
        Order savedOrder = orderRepository.save(order);
        List<Product> products = orderDto.getProducts().stream()
                .map(productDto -> Product.builder()
                        .cost(productDto.getCost())
                        .name(productDto.getName())
                        .orderId(savedOrder.getId())
                        .build())
                .toList();
        products = (List<Product>) productRepository.saveAll(products);
        return OrderConvertor.toOrderDto(savedOrder, products);
    }

}
