package com.example.warehouse.repository;

import com.example.warehouse.entity.Order;
import com.example.warehouse.model.dto.OrderDTO;
import com.example.warehouse.model.dto.OrderDetailDTO;
import com.example.warehouse.model.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository{

    @Autowired
    private final ProductMapper mapper;
    @Autowired
    private OrderRepositoryJPA orderRepositoryJPA;



}
