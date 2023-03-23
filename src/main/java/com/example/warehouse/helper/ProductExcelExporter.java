package com.example.warehouse.helper;

import com.example.warehouse.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Product> listProducts;

    public ProductExcelExporter(List<Product> listProducts) {
        this.listProducts = listProducts;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Products");
    }

    private  void writeHeaderRow(){
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Product ID");
    }

    private void writeDataRows(){

    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
