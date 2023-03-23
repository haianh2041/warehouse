package com.example.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_detail_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailHistory implements Serializable {
    @Id
    @Basic(optional = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private double price;

    @Column(name = "time")
    private String time;
}
