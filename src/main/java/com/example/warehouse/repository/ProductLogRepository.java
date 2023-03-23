package com.example.warehouse.repository;

import com.example.warehouse.entity.ProductLog;
import com.example.warehouse.model.dto.ProductLogDTO;

import java.util.List;

public interface ProductLogRepository {
    ProductLog newRecord(ProductLogDTO productLogDTO);

    List<ProductLog> getHistoryRecord();
}
