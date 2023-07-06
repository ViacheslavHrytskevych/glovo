package com.example.glodo_lection36.convertor;

import com.example.glodo_lection36.dto.OrderDto;
import com.example.glodo_lection36.dto.ProductDto;
import com.example.glodo_lection36.entity.Order;
import com.example.glodo_lection36.entity.Product;

import java.util.List;

public class OrderConvertor {

    public static OrderDto toOrderDto(Order order, List<Product> products) {
        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .cost(product.getCost())
                        .build())
                .toList();

        double sum = productDtos.stream()
                .mapToDouble(ProductDto::getCost)
                .sum();

        return OrderDto.builder()
                .id(order.getId())
                .date(order.getDate())
                .cost(sum)
                .products(productDtos)
                .build();
    }
}
