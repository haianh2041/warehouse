package com.example.warehouse.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO implements Serializable {
    private String name;
    private Integer cateId;
    private Integer amount;
    private double price;
    private Short status;

}
