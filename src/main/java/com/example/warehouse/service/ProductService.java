package com.example.warehouse.service;

import com.example.warehouse.entity.*;
import com.example.warehouse.model.dto.OrderDTO;
import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.dto.ShippingDTO;
import com.example.warehouse.model.dto.ShippingInsertDTO;

import java.util.List;

public interface ProductService {

    public abstract void createProduct(ProductDTO product);
    public abstract void updateProduct(Integer id, ProductDTO product);
    public abstract void deleteProduct(Integer id);
    public abstract List<Product> getProducts();
    public abstract List<ProductDTO> getProductsDTO();
    public abstract Product getProductById(Integer id);
    public abstract List<ProductDTO> getProductsByAmount(Integer amount);
    public abstract List<ProductLog> getHistoryRecord();
    public abstract void addToCart(Integer id, Integer amount);
    public abstract void order(ShippingInsertDTO shippingInsertDTO);
    public abstract List<OrderDetailHistory> getHistoryOrder();
    public abstract List<OrderDetail> getCart();
    public abstract List<ShippingDTO> listShipping();
    public abstract void receiveShipping(Integer orderId, Integer shipperId);
    public abstract void updateShipping(Integer orderId, Integer status);
    public abstract List<Shipper> listShipper();
    public abstract List<OrderDTO> viewOrder(String phone);
}
