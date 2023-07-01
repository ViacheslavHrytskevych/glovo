package com.example.glodo_lection36.repository;

import com.example.glodo_lection36.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

  List<Product> findAllByOrderId(int orderId);

 // List<Product> saveAll(List<Product> products);


}
