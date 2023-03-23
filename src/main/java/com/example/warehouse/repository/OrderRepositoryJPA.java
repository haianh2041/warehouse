package com.example.warehouse.repository;

import com.example.warehouse.entity.Order;
import com.example.warehouse.entity.OrderDetail;
import com.example.warehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositoryJPA extends JpaRepository<Order, Integer> {

    @Query(value = "select COALESCE(MAX(id),0) + 1 FROM `Order` ", nativeQuery = true)
    Integer getOrderId();
}
