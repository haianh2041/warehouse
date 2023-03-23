package com.example.warehouse.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailHistoryDTO implements Serializable {

    private Integer id;

    private Integer orderId;

    private String productName;

    private Integer amount;

    private double price;

    private String time;
}
