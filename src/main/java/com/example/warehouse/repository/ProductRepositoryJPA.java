package com.example.warehouse.repository;

import com.example.warehouse.entity.Product;
import com.example.warehouse.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryJPA extends JpaRepository<Product, Integer> {

    @Query("FROM Product p WHERE p.amount > :amount")
    List<Product> getProductsByAmount(@Param("amount") Integer amount);

    @Query(value = "select p FROM Product p WHERE p.amount = 1")
    List<Product> getProductsStatusOn();

    @Query(value = "select p FROM Product p WHERE p.name like 'R%'")
    List<Product> getProductsStatedNameWithR();

    @Query(value = "select COALESCE(MAX(id),0) + 1 FROM Product ")
    Integer Product();
}