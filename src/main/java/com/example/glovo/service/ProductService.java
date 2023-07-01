package com.example.glovo.service;

import com.example.glovo.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    private final Random random = new Random();

    public boolean save(Product product) {
        product.setId(this.random.nextInt());
        products.add(product);
        return true;
    }

    public Optional<Product> getById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id).findFirst();
    }

    public List<Product> geALL() {
        return this.products;
    }

    public boolean removeById(int id) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}




