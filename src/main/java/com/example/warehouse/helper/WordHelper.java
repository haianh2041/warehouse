package com.example.warehouse.helper;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordHelper {
    public static ByteArrayInputStream generateWord()
            throws FileNotFoundException, IOException,
            InvalidFormatException {
        try (XWPFDocument doc = new XWPFDocument()) {

            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);
            // Set Text to Bold and font size to 22 for first paragraph
            XWPFRun r1 = p1.createRun();
            r1.setBold(true);
            r1.setItalic(true);
            r1.setFontSize(22);
            r1.setText("Spring Boot + Apach e POI Example");
            r1.setFontFamily("Courier");
            r1.setColor("008000");
            r1.addBreak();

            XWPFParagraph p2 = doc.createParagraph();
            // Set color for second paragraph
            XWPFRun r2 = p2.createRun();
            r2.setText("Spring Boot + Apache POI Example");
            r2.setColor("FF5733");
            r2.setEmbossed(true);
            r2.setStrikeThrough(true);
            r2.addBreak();
            r2.addBreak();

            XWPFParagraph p3 = doc.createParagraph();
            p3.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r3 = p3.createRun();
            r3.setBold(true);
            r3.setItalic(true);
            r3.setFontSize(22);
            r3.setText("Table");
            r3.setFontFamily("Arial");

            XWPFTable table = doc.createTable();
            // Creating first Row
            XWPFTableRow row1 = table.getRow(0);
            row1.getCell(0).setText("Java, Scala");
            row1.addNewTableCell().setText("PHP, Flask");
            row1.addNewTableCell().setText("Ruby, Rails");

            // Creating second Row
            XWPFTableRow row2 = table.createRow();
            row2.getCell(0).setText("C, C ++");
            row2.getCell(1).setText("Python, Kotlin");
            row2.getCell(2).setText("Android, React");

            ByteArrayOutputStream b = new ByteArrayOutputStream();
            doc.write(b);
            return new ByteArrayInputStream(b.toByteArray());
        }
    }
}
