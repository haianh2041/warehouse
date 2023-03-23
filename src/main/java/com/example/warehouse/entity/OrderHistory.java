package com.example.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "`order_history`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistory implements Serializable {
    @Id
    @Basic(optional = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "shipper_id")
    private Integer shipperId;

    @Column(name = "total_payment")
    private double totalPayment;
}
