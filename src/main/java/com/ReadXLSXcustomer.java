package com;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXLSXcustomer {
	
	public static void main(String[] args) {
		readXLSXFile("C:\\kenshu\\ECSITE\\顧客データ.xlsx"); // Đường dẫn tới file Excel
	}
	
	private static void readXLSXFile(String file) {
		String url = "jdbc:mysql://localhost:3306/company?useSSL=false&serverTimezone=UTC";
		String user = "root";
		String password = "";
		
		try (
			Connection conn = DriverManager.getConnection(url, user, password);
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis)
			) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			
			String sql = "INSERT INTO companies (company_name, address, phone, contact_person, contact_mobile) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			for (int i = 1; i < rows; i++) {
				XSSFRow row = sheet.getRow(i);
				if (row == null) continue;
				
				try {
					String companyName = getCellValueAsString(row.getCell(0));
					String address = getCellValueAsString(row.getCell(1));
					String phone = getCellValueAsString(row.getCell(2));
					String contactPerson = getCellValueAsString(row.getCell(3));
					String contactMobile = getCellValueAsString(row.getCell(4));
					
					ps.setString(1, companyName);
					ps.setString(2, address);
					ps.setString(3, phone);
					ps.setString(4, contactPerson);
					ps.setString(5, contactMobile);
					
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
			case NUMERIC: return String.valueOf((long) cell.getNumericCellValue());
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
