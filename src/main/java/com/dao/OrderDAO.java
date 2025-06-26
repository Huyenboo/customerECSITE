
package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.DBAccess;
import com.bean.CartItem;
import com.bean.Product;

public class OrderDAO extends DBAccess {

	// Ghi đơn hàng mới vào DB
	public boolean insertOrder(String userId, String userName, CartItem order) {
		String sql = "INSERT INTO order_list (user_id, user_name, order_code, order_amount, order_day, order_arrived_day, order_memo) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		java.sql.Date newDay = new java.sql.Date(System.currentTimeMillis());

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);

			ps.setString(1, userId);
			ps.setString(2, userName);
			ps.setString(3, order.getProduct().getProId());
			ps.setInt(4, order.getQuantity());
			ps.setDate(5, new java.sql.Date(newDay.getTime()));
			ps.setDate(6, order.getOrderArrivedDay());
			ps.setString(7, order.getOrderMemo());

			int rows = ps.executeUpdate();

			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("catch");
			return false;

		} finally {
			disconnect();
		}
	}

	public List<CartItem> getAllOrderIdByUserId(String userId) {
		List<CartItem> list = new ArrayList<>();

		String sql = "SELECT o.*, p.pro_id, p.pro_name, p.pro_unit_num " +
				"FROM order_list o " +
				"JOIN product p ON o.order_code = p.pro_id " +
				"WHERE o.user_id = ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// Lấy dữ liệu sản phẩm
				Product p = new Product();
				p.setProId(rs.getString("pro_id"));
				p.setProName(rs.getString("pro_name"));
				p.setProPrice(rs.getInt("pro_unit_num")); // Đơn giá theo đơn vị (単価)

				// Tạo CartItem
				CartItem o = new CartItem();
				o.setOrderId(rs.getInt("order_id"));
				o.setUserId(rs.getString("user_id"));
				o.setUserName(rs.getString("user_name"));
				o.setOrderCode(rs.getString("order_code"));
				o.setOrderAmount(rs.getInt("order_amount"));
				o.setQuantity(rs.getInt("order_amount")); // Đồng bộ số lượng
				o.setOrderDay(rs.getDate("order_day"));
				o.setOrderArrivedDay(rs.getDate("order_arrived_day"));
				o.setProduct(p);
				o.setDeliveryDate(rs.getString("order_arrived_day"));
//				o.setOrderMemo(rs.getString);
				list.add(o);
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
	
}
