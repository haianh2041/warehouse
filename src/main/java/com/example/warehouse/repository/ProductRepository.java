package com.example.warehouse.repository;

import com.example.warehouse.entity.Product;
import com.example.warehouse.model.dto.ProductDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    Product getProductById(Integer id);
    List<Product> getProducts();
    Product createProduct(ProductDTO productDTO);
    void deleteProductById(Integer id);
    Product updateProduct(Integer id,ProductDTO productDTO);
    List<ProductDTO> searchProductByName(String name);
    List<ProductDTO> searchProductByCate(Integer cateId);
}
