package com.example.warehouse.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDTO implements Serializable {
    private Integer productId;

    private Integer orderId;

    private Integer amount;

    private double price;
}
