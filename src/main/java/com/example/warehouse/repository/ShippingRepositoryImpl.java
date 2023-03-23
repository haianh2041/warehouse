package com.example.warehouse.repository;

import com.example.warehouse.entity.Shipping;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class ShippingRepositoryImpl implements ShippingRepository{
    @Autowired
    ShippingRepositoryJPA shippingRepositoryJPA;

    @Override
    public Shipping getShippingByOrderId(Integer orderId) {
        List<Shipping> shippingList = shippingRepositoryJPA.findAll();
        for (Shipping shipping:shippingList) {
            if(shipping.getOrderId().equals(orderId)){
                return shipping;
            }
        }
        return null;
    }

    @Override
    public List<Shipping> getListShipping() {
        List<Shipping> shippingList = shippingRepositoryJPA.findAll();
        List<Shipping> listResult = new ArrayList<>();
        for (Shipping shipping:shippingList) {
            if(shipping.getStatus()!=2){
                listResult.add(shipping);
            }
        }
        return listResult;
    }
}
