package com.example.glodo_lection36.сontroller;

import com.example.glodo_lection36.dto.OrderDto;
import com.example.glodo_lection36.dto.ProductDto;
import com.example.glodo_lection36.service.OrderService;
import com.example.glodo_lection36.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable int id) {
        return orderService.getById(id);
    }

    @GetMapping
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public OrderDto add(@RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @PostMapping("/{id}/product")
    public ProductDto addProductToOrder(@PathVariable int id, @RequestBody ProductDto productDto) {
        return productService.addToOrder(productDto, id);
    }

}
