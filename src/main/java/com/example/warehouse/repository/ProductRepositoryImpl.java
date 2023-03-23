package com.example.warehouse.repository;

import com.example.warehouse.entity.Product;
import com.example.warehouse.model.dto.ProductDTO;
import com.example.warehouse.model.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private final ProductMapper mapper;

    @Autowired
    private ProductRepositoryJPA productRepositoryJPA;

    @Override
    public Product getProductById(Integer id) {
        return productRepositoryJPA.findById(id).get();
    }

    @Override
    public List<Product> getProducts() {
        return productRepositoryJPA.findAll();
    }

    @Override
    public void deleteProductById(Integer id) {
        Product p = productRepositoryJPA.findById(id).get();
        productRepositoryJPA.delete(p);
    }

    @Override
    public Product updateProduct(Integer id, @NotNull ProductDTO productDTO) {
        Product p = productRepositoryJPA.findById(id).get();
        p.setName(productDTO.getName());
        p.setCateId(productDTO.getCateId());
        p.setAmount(productDTO.getAmount());
        p.setPrice(productDTO.getPrice());
        p.setStatus(productDTO.getStatus());
        return productRepositoryJPA.save(p);
    }

    @Override
    public List<ProductDTO> searchProductByName(String name) {
        List<Product> products = productRepositoryJPA.findAll();
        List<ProductDTO> result = new ArrayList<>();
        for(Product p :products){
            if(p.getName().contains(name)){
                result.add(mapper.modelMapper().map(p,ProductDTO.class));
            }
        }
        if(result.size()==0){
            System.out.println("Products not found");
        }
        else {
            System.out.println("Search products successfully");
        }
        return result;
    }

    @Override
    public List<ProductDTO> searchProductByCate(Integer cateId) {
        List<Product> products = productRepositoryJPA.findAll();
        List<ProductDTO> result = new ArrayList<>();
        for(Product p :products){
            if(p.getCateId()==cateId){
                result.add(mapper.modelMapper().map(p,ProductDTO.class));
            }
        }
        if(result.size()==0){
            System.out.println("Products not found");
        }
        else {
            System.out.println("Search products successfully");
        }
        return result;
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = mapper.modelMapper().map(productDTO, Product.class);
        return productRepositoryJPA.save(product);
    }
}
