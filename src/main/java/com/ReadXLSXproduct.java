package com;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXLSXproduct {

    public static void main(String[] args) {
        readXLSXFile("C:\\kenshu\\ECSITE\\商品マスタ.xlsx"); // cập nhật đường dẫn
    }

    private static void readXLSXFile(String file) {
        String url = "jdbc:mysql://localhost:3306/company";
        String user = "root";
        String password = "";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis)
        ) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();

            String sql = "INSERT INTO products (product_code, product_name, product_name_detail, product_name_en, scientific_name, quantity, note) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 1; i < rows; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) continue;

                try {
                    String code = getCellValueAsString(row.getCell(0));
                    String name = getCellValueAsString(row.getCell(1));
                    String detail = getCellValueAsString(row.getCell(2));
                    String nameEn = getCellValueAsString(row.getCell(3));
                    String scientific = getCellValueAsString(row.getCell(14)); // cột 学名
                    String quantityStr = getCellValueAsString(row.getCell(15)); // cột 入数
                    String note = getCellValueAsString(row.getCell(17));        // cột 備考欄

                    int quantity = quantityStr.isEmpty() ? 0 : Integer.parseInt(quantityStr);

                    ps.setString(1, code);
                    ps.setString(2, name);
                    ps.setString(3, detail);
                    ps.setString(4, nameEn);
                    ps.setString(5, scientific);
                    ps.setInt(6, quantity);
                    ps.setString(7, note);

                    ps.executeUpdate();

                } catch (Exception e) {
                    System.out.println("Lỗi ở dòng " + (i + 1) + ": " + e.getMessage());
                }
            }

            System.out.println("Nhập dữ liệu thành công!");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new java.text.SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return cell.getStringCellValue();
                } catch (IllegalStateException e) {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BLANK: return "";
            default: return "UNKNOWN";
        }
    }
}
