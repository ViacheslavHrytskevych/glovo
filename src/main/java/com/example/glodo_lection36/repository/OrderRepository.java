package com.example.glodo_lection36.repository;

import com.example.glodo_lection36.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository <Order, Integer> {

    List<Order> findAll();
}
