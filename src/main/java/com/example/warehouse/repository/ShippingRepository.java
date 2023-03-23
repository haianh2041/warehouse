package com.example.warehouse.repository;

import com.example.warehouse.entity.Shipping;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingRepository {
    Shipping getShippingByOrderId(Integer orderId);

    List<Shipping> getListShipping();
}
