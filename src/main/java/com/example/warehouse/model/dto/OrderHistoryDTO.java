package com.example.warehouse.model.dto;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderHistoryDTO implements Serializable {
    private Integer userId;
    private Integer shipperId;
    private double totalPayment;

}
