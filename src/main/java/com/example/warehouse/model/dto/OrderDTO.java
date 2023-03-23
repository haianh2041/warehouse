package com.example.warehouse.model.dto;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO implements Serializable {
    private double totalPayment;
    private Integer shipperId;
    private Integer status;

}
