package com.example.warehouse.service;

import com.example.warehouse.common.Contrains;
import com.example.warehouse.entity.*;
import com.example.warehouse.model.dto.*;
import com.example.warehouse.model.mapper.ProductMapper;
import com.example.warehouse.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductLogRepository productLogRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailRepositoryJPA orderDetailRepositoryJPA;

    @Autowired
    private ProductRepositoryJPA productRepositoryJPA;

    @Autowired
    private OrderRepositoryJPA orderRepositoryJPA;

    @Autowired
    private OrderDetailHistoryJPA orderDetailHistoryJPA;

    @Autowired
    private ShippingRepositoryJPA shippingRepositoryJPA;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private ShipperRepositoryJPA shipperRepositoryJPA;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

    @Override
    public void createProduct(ProductDTO productDTO) {
        try {
            List<Product> products = productRepository.getProducts();
            for (Product p : products) {
                if (productDTO.getName().equals(p.getName())) {
                    throw new Exception("Not found");
                }
            }
            productRepository.createProduct(productDTO);

            String time =  formatter.format(date);

            ProductLogDTO productLogDTO = new ProductLogDTO(productDTO.getName(),productDTO.getCateId(),productDTO.getAmount(),productDTO.getPrice(),productDTO.getStatus(),
                    time, Contrains.action1);
            productLogRepository.newRecord(productLogDTO);
            System.out.println("create successfully");
        } catch (Exception e) {
            System.out.println("Product existed");
        }

    }

    @Override
    public void updateProduct(Integer id, ProductDTO productDTO) {
        try {
            int count =0;
            Product product = productRepository.getProductById(id);
            if (product == null) {
                throw new Exception("Not found");
            } else {
                List<Product> products = productRepository.getProducts();
                for (Product p : products) {
                    if(p.getId()!=id) {
                        if (productDTO.getName().equals(p.getName())) {
                            System.out.println("Product existed");
                            count++;
                            break;
                        }
                    }
                }
                if(count==0) {
                    System.out.println("Update successfully");
                    productRepository.updateProduct(id, productDTO);
                    String time =  formatter.format(date);
                    String action = "update";
                    ProductLogDTO productLogDTO = new ProductLogDTO(productDTO.getName(),productDTO.getCateId(),productDTO.getAmount(),productDTO.getPrice(),productDTO.getStatus(),
                            time,action);
                    productLogRepository.newRecord(productLogDTO);
                }
            }
        } catch (Exception e) {
            System.out.println("Product not found");
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        try {
            short a = 0;
            Product product = productRepository.getProductById(id);
            if (product == null) {
                throw new Exception("Not found");
            } else {
                System.out.println("Delete successfully");
//                productRepository.deleteProductById(id);
                product.setStatus(a);
                productRepositoryJPA.save(product);
                String time =  formatter.format(date);
                String action = "delete";
                ProductLogDTO productLogDTO = new ProductLogDTO(product.getName(),product.getCateId(),product.getAmount(),product.getPrice(),product.getStatus(),
                        time,action);
                productLogRepository.newRecord(productLogDTO);
                System.out.println(product.getStatus());
            }
        } catch (Exception e) {
            System.out.println("Product not found");
        }

    }

    @Override
    public List<ProductDTO> getProductsDTO() {
        List<Product> products = productRepository.getProducts();
        List<ProductDTO> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getStatus()==1) {
                ProductDTO d = ProductDTO.builder()
                        .name(p.getName())
                        .cateId(p.getCateId())
                        .amount(p.getAmount())
                        .price(p.getPrice())
                        .status(p.getStatus()).build();
                result.add(d);
            }
        }
        return result;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.getProducts();
        return products;
    }

    @Override
    public Product getProductById(Integer id) {
        try {
            Product result = productRepository.getProductById(id);
            if (result == null) {
                throw new Exception("Not found");
            } else {
                return result;
            }
        } catch (Exception e) {
            System.out.println("Product not found");
            return new Product();
        }
    }

    @Override
    public List<ProductDTO> getProductsByAmount(Integer amount) {
        List<Product> products = productRepositoryJPA.getProductsByAmount(amount);
        List<ProductDTO> result = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (Product product : products) {
            ProductDTO productDTO = mapper.map(product, ProductDTO.class);
            result.add(productDTO);
        }
        return result;
    }

    @Override
    public List<ProductLog> getHistoryRecord() {
        return productLogRepository.getHistoryRecord();
    }

    @Override
    public void addToCart(Integer id, Integer amount) {
        Product product = productRepository.getProductById(id);
        double price = 0;
        int count =0;
        List<OrderDetail> cart = getCart();
        if(product.getAmount()<amount){
            System.out.println("Not enough product");
        } else {
            if (product.getAmount()==amount) {
                product.setAmount(0);
                product.setStatus((short) 0);
            } else {
                product.setAmount(product.getAmount()-amount);
            }
            price += product.getPrice() * amount;
            for(OrderDetail orderDetail : cart){
                if(orderDetail.getProductId().equals(id)){
                    orderDetail.setPrice(orderDetail.getPrice()+price);
                    orderDetail.setAmount(orderDetail.getAmount()+amount);
                    orderDetailRepositoryJPA.save(orderDetail);
                    count++;
                    break;
                }
            }
            if(count==0) {
                orderDetailRepository.addToCart(id, orderRepositoryJPA.getOrderId(), amount, price);
                System.out.println("Add to cart successfully");
            }
        }
        productRepositoryJPA.save(product);
    }

    @Override
    public void order(ShippingInsertDTO shippingInsertDTO){
        List<OrderDetail> cart = orderDetailRepositoryJPA.findAll();
        Double total = 0D;
        String time =  formatter.format(date);
        ModelMapper mapper = new ModelMapper();
        List<OrderDetailHistoryDTO> listToSave = new ArrayList<>();
        OrderDetailHistoryDTO orderDetailHistoryDTO = null;
        Product product = null;
        for(OrderDetail orderDetail : cart){
            total += orderDetail.getPrice();
            product = productRepository.getProductById(orderDetail.getProductId());
            orderDetailHistoryDTO = new OrderDetailHistoryDTO();
            orderDetailHistoryDTO.setOrderId(orderDetail.getOrderId());
            orderDetailHistoryDTO.setProductName(product.getName());
            orderDetailHistoryDTO.setAmount(orderDetail.getAmount());
            orderDetailHistoryDTO.setPrice(orderDetail.getPrice());
            orderDetailHistoryDTO.setTime(time);
            listToSave.add(orderDetailHistoryDTO);
        }

        for (OrderDetailHistoryDTO i : listToSave){
            orderDetailHistoryJPA.save(mapper.map(i, OrderDetailHistory.class));
        }
        orderDetailRepositoryJPA.deleteAll();
        ShippingDTO shippingDTO = new ShippingDTO(orderRepositoryJPA.getOrderId(),shippingInsertDTO.getUserAddress(),shippingInsertDTO.getUserPhone(),0);
        shippingRepositoryJPA.save(mapper.map(shippingDTO,Shipping.class));
        OrderDTO orderDTO = new OrderDTO(total,null,0);
        Order p_order = mapper.map(orderDTO,Order.class);
        orderRepositoryJPA.save(p_order);
    }

    @Override
    public List<OrderDetailHistory> getHistoryOrder() {
        return orderDetailHistoryJPA.findAll();
    }

    @Override
    public List<OrderDetail> getCart() {
        return orderDetailRepositoryJPA.findAll();
    }

    @Override
    public List<ShippingDTO> listShipping() {
        ModelMapper mapper = new ModelMapper();
        List<Shipping> shippingList = shippingRepository.getListShipping();
        List<ShippingDTO> shippingDTOS = new ArrayList<>();
        for (Shipping shipping: shippingList) {
            ShippingDTO shippingDTO = mapper.map(shipping, ShippingDTO.class);
            shippingDTOS.add(shippingDTO);
        }
        return shippingDTOS;
    }

    @Override
    public void receiveShipping(Integer orderId, Integer shipperId) {
        Shipping shipping = shippingRepository.getShippingByOrderId(orderId);
        shipping.setStatus(1);
        shippingRepositoryJPA.save(shipping);
        Order order = orderRepositoryJPA.findById(orderId).get();
        order.setStatus((int) 1);
        order.setShipperId(shipperId);
        orderRepositoryJPA.save(order);
    }

    @Override
    public void updateShipping(Integer orderId, Integer status) {
        Shipping shipping = shippingRepository.getShippingByOrderId(orderId);
        shipping.setStatus(status);
        Order order = orderRepositoryJPA.findById(orderId).get();
        order.setStatus(status);
        orderRepositoryJPA.save(order);
        shippingRepositoryJPA.save(shipping);
    }

    @Override
    public List<Shipper> listShipper() {
        return shipperRepositoryJPA.findAll();
    }

    @Override
    public List<OrderDTO> viewOrder(String phone){
        List<Shipping> shippingList = shippingRepositoryJPA.findAll();
        Integer orderId = null;
        System.out.println(phone);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (Shipping shipping: shippingList) {
            if (shipping.getUserPhone().equals(phone)){
                orderId = shipping.getOrderId();
                Order order = orderRepositoryJPA.findById(orderId).get();
                OrderDTO orderDTO = mapper.map(order,OrderDTO.class);
                orderDTOList.add(orderDTO);
            }
        }
        return orderDTOList;
    }
}
