package com.example.warehouse.repository;

import com.example.warehouse.entity.ProductLog;
import com.example.warehouse.model.dto.ProductLogDTO;
import com.example.warehouse.model.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class ProductLogRepositoryImpl implements ProductLogRepository{

    @Autowired
    private ProductLogRepositoryJPA productLogRepositoryJPA;

    @Autowired
    private final ProductMapper mapper;
    @Override
    public ProductLog newRecord(ProductLogDTO productLogDTO) {
        ProductLog productLog = mapper.modelMapper().map(productLogDTO,ProductLog.class);
        return productLogRepositoryJPA.save(productLog);
    }

    @Override
    public List<ProductLog> getHistoryRecord() {
        return productLogRepositoryJPA.findAll();
    }
}
