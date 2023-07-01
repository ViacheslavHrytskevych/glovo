package com.example.glodo_lection36.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Data
@Builder
@Table("orders")
public class Order {

    @Id
    private int id;
    private Date date;

}
