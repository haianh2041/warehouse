package com.example.warehouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShippingDTO implements Serializable {
    private Integer orderId;
    private String userAddress;
    private String userPhone;
    private Integer status;
}
