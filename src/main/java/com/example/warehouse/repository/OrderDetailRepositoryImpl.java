package com.example.warehouse.repository;

import com.example.warehouse.entity.Order;
import com.example.warehouse.entity.OrderDetail;
import com.example.warehouse.model.dto.OrderDTO;
import com.example.warehouse.model.dto.OrderDetailDTO;
import com.example.warehouse.model.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class OrderDetailRepositoryImpl implements OrderDetailRepository{

    @Autowired
    private final ProductMapper mapper;

    @Autowired
    OrderDetailRepositoryJPA orderDetailRepositoryJPA;

    @Autowired
    ProductRepositoryJPA productRepositoryJPA;

    @Override
    public void addToCart(Integer productId, Integer orderId, Integer amount, double price) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO(productId,orderId,amount,price);
        OrderDetail orderDetail = mapper.modelMapper().map(orderDetailDTO,OrderDetail.class);
        orderDetailRepositoryJPA.save(orderDetail);
    }
}
