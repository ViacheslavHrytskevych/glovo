package com.example.glodo_lection36.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    private int id;
    private String name;
    private double cost;
}
