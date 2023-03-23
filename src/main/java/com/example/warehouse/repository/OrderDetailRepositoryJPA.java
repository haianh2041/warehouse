package com.example.warehouse.repository;

import com.example.warehouse.entity.Order;
import com.example.warehouse.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepositoryJPA extends JpaRepository<OrderDetail, Integer> {


}
