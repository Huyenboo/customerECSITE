package com;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DBAccess {
	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT * FROM product";
		
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
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
				p.setProPrice(rs.getBigDecimal("pro_price"));
				p.setProExe(rs.getString("pro_exe"));
				list.add(p);
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
	public boolean insertProduct(Product p) {
		String sql = "INSERT INTO product (pro_id, pro_name, pro_name_short, pro_en_name, pro_kana_name, pro_file, pro_seedling, pro_box, pro_code1, pro_code2, pro_stan, pro_en_stan, pro_sci_name, pro_unit_num, pro_unit, pro_discard, pro_memo, pro_price, pro_exe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, p.getProId());
			ps.setString(2, p.getProName());
			ps.setString(3, p.getProNameShort());
			ps.setString(4, p.getProEnName());
			ps.setString(5, p.getProKanaName());
			ps.setString(6, p.getProFile());
			ps.setString(7, p.getProSeedling());
			ps.setString(8, p.getProBox());
			ps.setString(9, p.getProCode1());
			ps.setString(10, p.getProCode2());
			ps.setString(11, p.getProStan());
			ps.setString(12, p.getProEnStan());
			ps.setString(13, p.getProSciName());
			ps.setInt(14, p.getProUnitNum());
			ps.setString(15, p.getProUnit());
			ps.setInt(16, p.getProDiscard());
			ps.setString(17, p.getProMemo());
			ps.setBigDecimal(18, p.getProPrice());
			ps.setString(19, p.getProExe());
			
			int result = ps.executeUpdate();
			ps.close();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		
		
	}
	public Product getProductById(String id) {
		Product p = null;
		String sql = "SELECT * FROM product WHERE pro_id = ?";
		
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				p = new Product();
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
				p.setProPrice(rs.getBigDecimal("pro_price"));
				p.setProExe(rs.getString("pro_exe"));
			}
			
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return p;
	}
	
}
