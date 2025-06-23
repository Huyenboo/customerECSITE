package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.DBAccess;
import com.bean.CartItem;

public class OrderDAO extends DBAccess { 
	
    // Ghi đơn hàng mới vào DB
	public boolean insertOrder(String userId, String userName, CartItem item) {
		String sql = "INSERT INTO order_list (product_id, quantity, order_amount, user_id, order_day, user_name) " +"VALUES (?, ?, ?, ?, CURRENT_DATE, ?)";
		
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, item.getProduct().getProId());
			ps.setInt(2, item.getQuantity());
			ps.setInt(3, item.getSubtotal());
			ps.setString(4, userId);
			ps.setString(5, userName);
			
			
			int rows = ps.executeUpdate();
			return rows > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
	}
	public List<CartItem> getAllOrderIdByUserId(String userId){
	List<CartItem> list = new ArrayList<>();
	String sql = "SELECT * FROM order_list WHERE user_Id = ?" ;
	
	try {
		connect();
		PreparedStatement ps = getConnection().prepareStatement(sql);
		
		ps.setString(1, userId);
		ResultSet rs = ps.executeQuery();

		
		while (rs.next()) {
			CartItem o = new CartItem();
			o.setOrderId(rs.getInt("order_id"));
			o.setUserId(rs.getInt("user_id"));
			o.setUserName(rs.getString("user_name"));
			o.setOrderCode(rs.getString("order_code"));
			o.setOrderAmount(rs.getInt("order_amount"));
			o.setOrderDay(rs.getDate("order_day"));
			o.setOrderArrivedDay(rs.getDate("order_arrived_day"));
			o.setOrderMemo(rs.getString("memo"));
			list.add(o);
		}
		
		rs.close();
		ps.close();
	}catch(Exception e) {
		e.printStackTrace();
	} finally {
		disconnect();
	}
	
	return list;
	}
}
