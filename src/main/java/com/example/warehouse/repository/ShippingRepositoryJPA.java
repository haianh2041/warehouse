package com.example.warehouse.repository;

import com.example.warehouse.entity.OrderDetailHistory;
import com.example.warehouse.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepositoryJPA extends JpaRepository<Shipping, Integer> {
}
