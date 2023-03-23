package com.example.warehouse.controller;

import com.example.warehouse.entity.*;
import com.example.warehouse.entity.Product;
import com.example.warehouse.helper.ProductExcelExporter;
import com.example.warehouse.helper.WordHelper;
import com.example.warehouse.model.dto.OrderDTO;
import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.dto.ShippingDTO;
import com.example.warehouse.model.dto.ShippingInsertDTO;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.repository.ProductRepositoryImpl;
import com.example.warehouse.service.ProductService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {

//    @Autowired
//    private ProductRepositoryImpl productRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping(value = "product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @GetMapping(value = "admin/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(value = "user/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> getProductsDTO() {
        return productService.getProductsDTO();
    }

    @GetMapping(value = "productByAmount/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> getProductsByAmount(@PathVariable Integer amount) {
        return productService.getProductsByAmount(amount);
    }

    @GetMapping(value = "historyRecord", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductLog> getHistoryRecord() {
        return productService.getHistoryRecord();
    }

    @GetMapping(value = "historyOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetailHistory> getHistoryOrder() {
        return productService.getHistoryOrder();
    }

    @GetMapping(value = "getCart", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetail> getCart() {
        return productService.getCart();
    }

    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createNewProduct(@RequestBody ProductDTO productDTO) {
        productService.createProduct(productDTO);
    }

    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct( @PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @PutMapping (value = "update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void UpdateProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
    }

    @GetMapping (value = "user/addToCart/{id}/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addToCart(@PathVariable Integer id, @PathVariable Integer amount) {
        productService.addToCart(id,amount);
        String res = "Add to cart successfully";
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping  (value = "user/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> order(@RequestBody ShippingInsertDTO shippingInsertDTO) {
        productService.order(shippingInsertDTO);
        String res = "Order successfully";
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(value = "shipper/listShipping", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ShippingDTO> getListShipping() {
        return productService.listShipping();
    }

    @GetMapping(value = "listShipper", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Shipper> getListShipper() {
        return productService.listShipper();
    }

    @GetMapping(value = "searchProductByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> searchProductByName(@PathVariable String name) {
        return productRepository.searchProductByName(name);
    }

    @GetMapping(value = "searchProductByCate/{cateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> searchProductByCate(@PathVariable Integer cateId) {
        return productRepository.searchProductByCate(cateId);
    }

    @GetMapping(value = "shipper/receiveShipping/{shipperId}/{orderId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void receiveShipping(@PathVariable Integer shipperId,@PathVariable Integer orderId){
        productService.receiveShipping(orderId,shipperId);
    }

    @PostMapping(value = "viewOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> viewOrder(@RequestBody String userPhone) {
        return productService.viewOrder(userPhone);
    }

    @PostMapping(value = "shipper/updateShipping/{orderId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateShipping(@PathVariable Integer orderId,@RequestBody Integer status){
        productService.updateShipping(orderId,status);
    }

    @GetMapping(value = "/word",
            produces = "application/vnd.openxmlformats-"
                    + "officedocument.wordprocessingml.document")
    public ResponseEntity<InputStreamResource> word()
            throws IOException, InvalidFormatException {

        ByteArrayInputStream bis = WordHelper.generateWord();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                "inline; filename= mydoc.docx");
        return ResponseEntity.ok().headers(headers).
                body(new InputStreamResource(bis));
    }

    @GetMapping(value = "user/export")
    public void exportToExcel(@NotNull HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=product.xlsx";

        response.setHeader(headerKey,headerValue);

        List<Product> listProducts = productService.getProducts();

        ProductExcelExporter excelExporter = new ProductExcelExporter(listProducts);
        excelExporter.export(response);
    }
}
