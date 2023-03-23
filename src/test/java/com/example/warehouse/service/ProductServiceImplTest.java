package com.example.warehouse.service;

import com.example.warehouse.entity.Order;
import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.ProductLog;
import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.dto.ProductLogDTO;
import com.example.warehouse.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ProductServiceImplTest {

    @Mock
    private ProductLogRepository mockProductLogRepository;
    @Mock
    private ProductRepository mockProductRepository;
    @Mock
    private OrderRepository mockOrderRepository;
    @Mock
    private ProductRepositoryJPA mockProductRepositoryJPA;
    @Mock
    private OrderRepositoryJPA mockOrderRepositoryJPA;

    @InjectMocks
    private ProductServiceImpl productServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testCreateProduct() {
        // Setup
        final ProductDTO productDTO = new ProductDTO("name", 0, 0, 0.0, (short) 0);

        // Configure ProductRepository.getProducts(...).
        final List<Product> products = Arrays.asList(new Product(0, "name", 0, 0, 0.0, (short) 0));
        when(mockProductRepository.getProducts()).thenReturn(products);

        // Configure ProductRepository.createProduct(...).
        final Product product = new Product(0, "name", 0, 0, 0.0, (short) 0);
        when(mockProductRepository.createProduct(new ProductDTO("name", 0, 0, 0.0, (short) 0))).thenReturn(product);

        // Configure ProductLogRepository.newRecord(...).
        final ProductLog productLog = new ProductLog(0, "name", 0, 0, 0.0, (short) 0, "time", "action");
        when(mockProductLogRepository.newRecord(
                new ProductLogDTO("name", 0, 0, 0.0, (short) 0, "time", "add"))).thenReturn(productLog);

        // Run the test
        productServiceImplUnderTest.createProduct(productDTO);

        // Verify the results
        verify(mockProductRepository).createProduct(new ProductDTO("name", 0, 0, 0.0, (short) 0));
        verify(mockProductLogRepository).newRecord(new ProductLogDTO("name", 0, 0, 0.0, (short) 0, "time", "add"));
    }

    @Test
    void testCreateProduct_ProductRepositoryGetProductsReturnsNoItems() {
        // Setup
        final ProductDTO productDTO = new ProductDTO("name", 0, 0, 0.0, (short) 0);
        when(mockProductRepository.getProducts()).thenReturn(Collections.emptyList());

        // Configure ProductRepository.createProduct(...).
        final Product product = new Product(0, "name", 0, 0, 0.0, (short) 0);
        when(mockProductRepository.createProduct(new ProductDTO("name", 0, 0, 0.0, (short) 0))).thenReturn(product);

        // Configure ProductLogRepository.newRecord(...).
        final ProductLog productLog = new ProductLog(0, "name", 0, 0, 0.0, (short) 0, "time", "action");
        when(mockProductLogRepository.newRecord(
                new ProductLogDTO("name", 0, 0, 0.0, (short) 0, "time", "add"))).thenReturn(productLog);

        // Run the test
        productServiceImplUnderTest.createProduct(productDTO);

        // Verify the results
        verify(mockProductRepository).createProduct(new ProductDTO("name", 0, 0, 0.0, (short) 0));
        verify(mockProductLogRepository).newRecord(new ProductLogDTO("name", 0, 0, 0.0, (short) 0, "time", "add"));
    }

    @Test
    void testUpdateProduct() {
        // Setup
        final ProductDTO productDTO = new ProductDTO("name", 0, 0, 0.0, (short) 0);

        // Configure ProductRepository.getProductById(...).
        final Product product = new Product(0, "name", 0, 0, 0.0, (short) 0);
        when(mockProductRepository.getProductById(0)).thenReturn(product);

        // Configure ProductRepository.getProducts(...).
        final List<Product> products = Arrays.asList(new Product(0, "name", 0, 0, 0.0, (short) 0));
        when(mockProductRepository.getProducts()).thenReturn(products);

        // Configure ProductRepository.updateProduct(...).
        final Product product1 = new Product(0, "name", 0, 0, 0.0, (short) 0);
        when(mockProductRepository.updateProduct(0, new ProductDTO("name", 0, 0, 0.0, (short) 0))).thenReturn(product1);

        // Configure ProductLogRepository.newRecord(...).
        final ProductLog productLog = new ProductLog(0, "name", 0, 0, 0.0, (short) 0, "time", "action");
        when(mockProductLogRepository.newRecord(
                new ProductLogDTO("name", 0, 0, 0.0, (short) 0, "time", "add"))).thenReturn(productLog);

        // Run the test
        productServiceImplUnderTest.updateProduct(0, productDTO);

        // Verify the results
        verify(mockProductRepository).updateProduct(0, new ProductDTO("name", 0, 0, 0.0, (short) 0));
        verify(mockProductLogRepository).newRecord(new ProductLogDTO("name", 0, 0, 0.0, (short) 0, "time", "add"));
    }

    @Test
    void testUpdateProduct_ProductRepositoryGetProductByIdReturnsNull() {
        // Setup
        final ProductDTO productDTO = new ProductDTO("name", 0, 0, 0.0, (short) 0);
        when(mockProductRepository.getProductById(0)).thenReturn(null);

        // Run the test
        productServiceImplUnderTest.updateProduct(0, productDTO);

        // Verify the results
    }

    @Test
    void testUpdateProduct_ProductRepositoryGetProductsReturnsNoItems() {
        // Setup
        final ProductDTO productDTO = new ProductDTO("name", 0, 0, 0.0, (short) 0);

        // Configure ProductRepository.getProductById(...).
        final Product product = new Product(0, "name", 0, 0, 0.0, (short) 0);
        when(mockProductRepository.getProductById(0)).thenReturn(product);

        when(mockProductRepository.getProducts()).thenReturn(Collections.emptyList());

        // Configure ProductRepository.updateProduct(...).
        final Product product1 = new Product(0, "name", 0, 0, 0.0, (short) 0);
        when(mockProductRepository.updateProduct(0, new ProductDTO("name", 0, 0, 0.0, (short) 0))).thenReturn(product1);

        // Configure ProductLogRepository.newRecord(...).
        final ProductLog productLog = new ProductLog(0, "name", 0, 0, 0.0, (short) 0, "time", "action");
        when(mockProductLogRepository.newRecord(
                new ProductLogDTO("name", 0, 0, 0.0, (short) 0, "time", "add"))).thenReturn(productLog);

        // Run the test
        productServiceImplUnderTest.updateProduct(0, productDTO);

        // Verify the results
        verify(mockProductRepository).updateProduct(0, new ProductDTO("name", 0, 0, 0.0, (short) 0));
        verify(mockProductLogRepository).newRecord(new ProductLogDTO("name", 0, 0, 0.0, (short) 0, "time", "add"));
    }

    @Test
    void testDeleteProduct() {
        // Setup
        // Configure ProductRepository.getProductById(...).
        final Product product = new Product(0, "name", 0, 0, 0.0, (short) 0);
        when(mockProductRepository.getProductById(0)).thenReturn(product);

        // Configure ProductRepositoryJPA.save(...).
        final Product product1 = new Product(0, "name", 0, 0, 0.0, (short) 0);
        when(mockProductRepositoryJPA.save(new Product(0, "name", 0, 0, 0.0, (short) 0))).thenReturn(product1);

        // Configure ProductLogRepository.newRecord(...).
        final ProductLog productLog = new ProductLog(0, "name", 0, 0, 0.0, (short) 0, "time", "action");
        when(mockProductLogRepository.newRecord(
                new ProductLogDTO("name", 0, 0, 0.0, (short) 0, "time", "add"))).thenReturn(productLog);

        // Run the test
        productServiceImplUnderTest.deleteProduct(0);

        // Verify the results
        verify(mockProductRepositoryJPA).save(new Product(0, "name", 0, 0, 0.0, (short) 0));
        verify(mockProductLogRepository).newRecord(new ProductLogDTO("name", 0, 0, 0.0, (short) 0, "time", "add"));
    }

    @Test
    void testDeleteProduct_ProductRepositoryReturnsNull() {
        // Setup
        when(mockProductRepository.getProductById(0)).thenReturn(null);

        // Run the test
        productServiceImplUnderTest.deleteProduct(0);

        // Verify the results
    }

    @Test
    void testGetProductsDTO() {
        // Setup
        final List<ProductDTO> expectedResult = Arrays.asList(new ProductDTO("name", 0, 0, 0.0, (short) 0));

        // Configure ProductRepository.getProducts(...).
        final List<Product> products = Arrays.asList(new Product(0, "name", 0, 0, 0.0, (short) 0));
        when(mockProductRepository.getProducts()).thenReturn(products);

        // Run the test
        final List<ProductDTO> result = productServiceImplUnderTest.getProductsDTO();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetProductsDTO_ProductRepositoryReturnsNoItems() {
        // Setup
        when(mockProductRepository.getProducts()).thenReturn(Collections.emptyList());

        // Run the test
        final List<ProductDTO> result = productServiceImplUnderTest.getProductsDTO();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetProducts() {
        // Setup
        final List<Product> expectedResult = Arrays.asList(new Product(0, "name", 0, 0, 0.0, (short) 0));

        // Configure ProductRepository.getProducts(...).
        final List<Product> products = Arrays.asList(new Product(0, "name", 0, 0, 0.0, (short) 0));
        when(mockProductRepository.getProducts()).thenReturn(products);

        // Run the test
        final List<Product> result = productServiceImplUnderTest.getProducts();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetProducts_ProductRepositoryReturnsNoItems() {
        // Setup
        when(mockProductRepository.getProducts()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Product> result = productServiceImplUnderTest.getProducts();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetProductById() {
        // Setup
        final Product expectedResult = new Product(0, "name", 0, 0, 0.0, (short) 0);

        // Configure ProductRepository.getProductById(...).
        final Product product = new Product(0, "name", 0, 0, 0.0, (short) 0);
        when(mockProductRepository.getProductById(0)).thenReturn(product);

        // Run the test
        final Product result = productServiceImplUnderTest.getProductById(0);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetProductById_ProductRepositoryReturnsNull() {
        // Setup
        final Product expectedResult = new Product(0, "name", 0, 0, 0.0, (short) 0);
        when(mockProductRepository.getProductById(0)).thenReturn(null);

        // Run the test
        final Product result = productServiceImplUnderTest.getProductById(0);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetProductsByAmount() {
        // Setup
        final List<ProductDTO> expectedResult = Arrays.asList(new ProductDTO("name", 0, 0, 0.0, (short) 0));

        // Configure ProductRepositoryJPA.getProductsByAmount(...).
        final List<Product> products = Arrays.asList(new Product(0, "name", 0, 0, 0.0, (short) 0));
        when(mockProductRepositoryJPA.getProductsByAmount(0)).thenReturn(products);

        // Run the test
        final List<ProductDTO> result = productServiceImplUnderTest.getProductsByAmount(0);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetProductsByAmount_ProductRepositoryJPAReturnsNoItems() {
        // Setup
        when(mockProductRepositoryJPA.getProductsByAmount(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<ProductDTO> result = productServiceImplUnderTest.getProductsByAmount(0);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetHistoryRecord() {
        // Setup
        final List<ProductLog> expectedResult = Arrays.asList(
                new ProductLog(0, "name", 0, 0, 0.0, (short) 0, "time", "action"));

        // Configure ProductLogRepository.getHistoryRecord(...).
        final List<ProductLog> productLogs = Arrays.asList(
                new ProductLog(0, "name", 0, 0, 0.0, (short) 0, "time", "action"));
        when(mockProductLogRepository.getHistoryRecord()).thenReturn(productLogs);

        // Run the test
        final List<ProductLog> result = productServiceImplUnderTest.getHistoryRecord();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetHistoryRecord_ProductLogRepositoryReturnsNoItems() {
        // Setup
        when(mockProductLogRepository.getHistoryRecord()).thenReturn(Collections.emptyList());

        // Run the test
        final List<ProductLog> result = productServiceImplUnderTest.getHistoryRecord();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

//    @Test
//    void testOrderProduct() {
//        // Setup
//        // Configure ProductRepository.getProductById(...).
//        final Product product = new Product(0, "name", 0, 0, 0.0, (short) 0);
//        when(mockProductRepository.getProductById(0)).thenReturn(product);
//
//        // Configure ProductRepositoryJPA.save(...).
//        final Product product1 = new Product(0, "name", 0, 0, 0.0, (short) 0);
//        when(mockProductRepositoryJPA.save(new Product(0, "name", 0, 0, 0.0, (short) 0))).thenReturn(product1);
//
//        // Run the test
//        productServiceImplUnderTest.orderProduct(0, 0);
//
//        // Verify the results
//        verify(mockOrderRepository).order("name", 0, 0.0);
//        verify(mockProductRepositoryJPA).save(new Product(0, "name", 0, 0, 0.0, (short) 0));
//    }

//    @Test
//    void testGetHistoryOrder() {
//        // Setup
//        final List<Order> expectedResult = Arrays.asList(new Order(0, "productName", 0, 0.0));
//
//        // Configure OrderRepositoryJPA.findAll(...).
//        final List<Order> orders = Arrays.asList(new Order(0, "productName", 0, 0.0));
//        when(mockOrderRepositoryJPA.findAll()).thenReturn(orders);
//
//        // Run the test
//        final List<Order> result = productServiceImplUnderTest.getHistoryOrder();
//
//        // Verify the results
//        assertEquals(expectedResult, result);
//    }

//    @Test
//    void testGetHistoryOrder_OrderRepositoryJPAReturnsNoItems() {
//        // Setup
//        when(mockOrderRepositoryJPA.findAll()).thenReturn(Collections.emptyList());
//
//        // Run the test
//        final List<Order> result = productServiceImplUnderTest.getHistoryOrder();
//
//        // Verify the results
//        assertEquals(Collections.emptyList(), result);
//    }
}
