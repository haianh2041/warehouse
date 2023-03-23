package com.example.warehouse.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;




@Entity
@Table(name = "history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductLog implements Serializable {
    @Id
    @Basic(optional = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cateId")
    private Integer cateId;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private double price;

    @Column(name = "status")
    private Short status;

    @Column(name = "time")
    private String time;

    @Column(name = "action")
    private String action;
}
