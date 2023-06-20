package com.example.glovo.controller;

import com.example.glovo.model.Product;
import com.example.glovo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public boolean save(@RequestBody Product product) {
        return this.productService.save(product);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.geALL();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Integer id) {
        return (Product) this.productService.getById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public boolean removeById(@PathVariable Integer id) {
        return productService.removeById(id);
    }

}


