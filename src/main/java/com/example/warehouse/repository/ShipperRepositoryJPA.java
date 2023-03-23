package com.example.warehouse.repository;

import com.example.warehouse.entity.Shipper;
import com.example.warehouse.entity.Shipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepositoryJPA extends JpaRepository<Shipper, Integer> {
}
