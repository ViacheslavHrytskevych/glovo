package com.example.glodo_lection36.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Builder
public class OrderDto {

    private int id;
    private Date date;
    private double cost;
    private List<ProductDto> products;


}
