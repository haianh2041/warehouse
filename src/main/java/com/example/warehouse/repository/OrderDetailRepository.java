package com.example.warehouse.repository;


import com.example.warehouse.entity.OrderDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository {

    void addToCart(Integer productId,Integer orderId ,Integer amount,double price);
}
