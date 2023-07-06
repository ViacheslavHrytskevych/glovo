package com.example.glodo_lection36.service;

import com.example.glodo_lection36.dto.ProductDto;
import com.example.glodo_lection36.entity.Product;
import com.example.glodo_lection36.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void addToOrderTest () {

        ProductDto productDto = ProductDto.builder().id(1).name("Tomato").cost(10).build();

        Product savedProduct = Product.builder().id(1).name("Tomato").cost(10).orderId(1).build();

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        ProductDto result = productService.addToOrder(productDto, 1);

        verify(productRepository).save(any(Product.class));

        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("Tomato", result.getName());
        Assertions.assertEquals(10, result.getCost());
    }
}
