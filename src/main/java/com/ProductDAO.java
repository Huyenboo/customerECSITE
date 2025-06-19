package com;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DBAccess {
	
	// Lấy toàn bộ sản phẩm
	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT * FROM product";
		
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				list.add(mapResultSetToProduct(rs));
			}
			
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
	}
	
	// Tìm kiếm theo từ khóa (tiếng Nhật hoặc tiếng Anh)
	public List<Product> searchByKeyword(String keyword) {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT * FROM product WHERE pro_name LIKE ? OR pro_en_name LIKE ?";
		
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(mapResultSetToProduct(rs));
			}
			
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
	}
	
	// Lấy 1 sản phẩm theo ID
	public Product getProductById(String id) {
		Product product = null;
		String sql = "SELECT * FROM product WHERE pro_id = ?";
		
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product = mapResultSetToProduct(rs);
			}
			
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return product;
	}
	
	// Hàm dùng chung để map từ ResultSet sang Product object
	private Product mapResultSetToProduct(ResultSet rs) throws Exception {
		Product p = new Product();
		p.setProId(rs.getString("pro_id"));
		p.setProName(rs.getString("pro_name"));
		p.setProNameShort(rs.getString("pro_name_short"));
		p.setProEnName(rs.getString("pro_en_name"));
		p.setProKanaName(rs.getString("pro_kana_name"));
		p.setProFile(rs.getString("pro_file"));
		p.setProSeedling(rs.getString("pro_seedling"));
		p.setProBox(rs.getString("pro_box"));
		p.setProCode1(rs.getString("pro_code1"));
		p.setProCode2(rs.getString("pro_code2"));
		p.setProStan(rs.getString("pro_stan"));
		p.setProEnStan(rs.getString("pro_en_stan"));
		p.setProSciName(rs.getString("pro_sci_name"));
		p.setProUnitNum(rs.getInt("pro_unit_num"));
		p.setProUnit(rs.getString("pro_unit"));
		p.setProDiscard(rs.getInt("pro_discard"));
		p.setProMemo(rs.getString("pro_memo"));
		return p;
	}
} 