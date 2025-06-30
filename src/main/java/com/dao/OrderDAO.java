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

		String sql = "SELECT o.*, p.pro_id, p.pro_name, p.pro_price " +
				"FROM order_list o " +
				"JOIN product p ON o.order_code = p.pro_id " +
				"WHERE o.user_id = ? ORDER BY o.order_id ASC ";
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
				p.setProPrice(rs.getDouble("pro_price")); // Đơn giá theo đơn vị (単価)

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
				o.setOrderMemo(rs.getString("order_memo"));
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

	public List<CartItem> getAllOrder() {
		List<CartItem> list = new ArrayList<>();

		String sql = """
			    SELECT o.*, p.pro_id, p.pro_name, p.pro_unit_num, p.pro_price
			    FROM order_list o
			    JOIN product p ON o.order_code = p.pro_id
			""";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
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
				o.setOrderMemo(rs.getString("order_memo"));
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

	//chinh sua order 
	public CartItem getOrderById(int orderId) {
		CartItem order = null;
		String sql = """
				    SELECT o.*, u.user_name, p.pro_name
				    FROM order_list o
				    JOIN app_user u ON o.user_name = u.company_name
				    JOIN product p ON o.order_code = p.pro_id
				    WHERE o.order_id = ?
				""";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				order = new CartItem();
				order.setOrderId(rs.getInt("order_id"));
				order.setUserId(rs.getString("user_id"));
				order.setUserName(rs.getString("user_name"));
				order.setOrderCode(rs.getString("order_code"));
				order.setOrderAmount(rs.getInt("order_amount"));
				order.setOrderDay(rs.getDate("order_day"));
				order.setOrderArrivedDay(rs.getDate("order_arrived_day"));
				order.setOrderMemo(rs.getString("order_memo"));
				order.setQuantity(rs.getInt("order_amount")); // Đồng bộ quantity

				// Set product
				Product p = new Product();
				p.setProId(rs.getString("order_code"));
				p.setProName(rs.getString("pro_name"));
				order.setProduct(p);

				order.setDeliveryDate(rs.getString("order_arrived_day"));
			}

			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return order;
	}

	//Lấy danh sách tất cả đơn hàng có phân trang
	public List<CartItem> getAllOrder(int offset, int limit) {
		List<CartItem> list = new ArrayList<>();
		 String sql = """
			        SELECT o.*, p.pro_id, p.pro_name, p.pro_unit_num, p.pro_price
			        FROM order_list o
			        JOIN product p ON o.order_code = p.pro_id
			        ORDER BY o.order_day DESC
			        LIMIT ? OFFSET ?
			    """;

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product p = new Product();
				p.setProId(rs.getString("pro_id"));
				p.setProName(rs.getString("pro_name"));
				 p.setProUnitNum(rs.getInt("pro_unit_num"));
				p.setProPrice(rs.getDouble("pro_price"));

				CartItem o = new CartItem();
				o.setOrderId(rs.getInt("order_id"));
				o.setUserId(rs.getString("user_id"));
				o.setUserName(rs.getString("user_name"));
				o.setOrderCode(rs.getString("order_code"));
				o.setOrderAmount(rs.getInt("order_amount"));
				o.setQuantity(rs.getInt("order_amount"));
				o.setOrderDay(rs.getDate("order_day"));
				o.setOrderArrivedDay(rs.getDate("order_arrived_day"));
				o.setProduct(p);
				o.setDeliveryDate(rs.getString("order_arrived_day"));
				o.setOrderMemo(rs.getString("order_memo"));
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

	//	Tìm kiếm theo tên khách hàng + phân trang
	public List<CartItem> searchOrdersByCustomerName(String keyword, int offset, int limit) {
		List<CartItem> list = new ArrayList<>();
		String sql = """
				    SELECT o.*, p.pro_id, p.pro_name, p.pro_unit_num
				    FROM order_list o
				    JOIN product p ON o.order_code = p.pro_id
				    WHERE o.user_name LIKE ?
				    ORDER BY o.order_day DESC
				    LIMIT ? OFFSET ?
				""";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setInt(2, limit);
			ps.setInt(3, offset);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product p = new Product();
				p.setProId(rs.getString("pro_id"));
				p.setProName(rs.getString("pro_name"));
				p.setProPrice(rs.getInt("pro_unit_num"));

				CartItem o = new CartItem();
				o.setOrderId(rs.getInt("order_id"));
				o.setUserId(rs.getString("user_id"));
				o.setUserName(rs.getString("user_name"));
				o.setOrderCode(rs.getString("order_code"));
				o.setOrderAmount(rs.getInt("order_amount"));
				o.setQuantity(rs.getInt("order_amount"));
				o.setOrderDay(rs.getDate("order_day"));
				o.setOrderArrivedDay(rs.getDate("order_arrived_day"));
				o.setProduct(p);
				o.setDeliveryDate(rs.getString("order_arrived_day"));
				o.setOrderMemo(rs.getString("order_memo"));
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
	
	
	//	Tìm kiếm theo tên khách hàng + phân trang
//	public List<OrderListDTO> searchOrdersByCustomerName(String keyword, int offset, int limit) {
//	    List<OrderListDTO> list = new ArrayList<>();
//	    String sql = """
//	        SELECT o.order_day,o.order_id, u.company_name,p.pro_id,p.pro_name,o.order_amount,p.pro_price,o.order_arrived_day,o.order_memo 
//	        FROM order_list o
//	        JOIN product p ON o.order_code = p.pro_id
//	        JOIN app_user u ON o.user_id = u.id
//	        WHERE o.user_name LIKE ?
//	        ORDER BY o.order_day DESC
//	        LIMIT ? OFFSET ?
//	    """;
//
//	    try {
//	        connect();
//	        PreparedStatement ps = getConnection().prepareStatement(sql);
//	        ps.setString(1, "%" + keyword + "%");
//	        ps.setInt(2, limit);
//	        ps.setInt(3, offset);
//	        ResultSet rs = ps.executeQuery();
//
//	        while (rs.next()) {
//	        	OrderListDTO order = new OrderListDTO();
//	        	order.setOrderDay(rs.getDate("order_day"));
//	        	order.setOrderId(rs.getInt("order_id"));
//	        	order.setCompanyName(rs.getString("company_name"));
//	        	order.setProId(rs.getString("pro_id"));
//	        	order.setProName(rs.getString("pro_name"));
//	        	order.setAmount(rs.getInt("order_amount"));
//	        	order.setProPrice(rs.getDouble("pro_price"));
//	        	order.setArrivedDay(rs.getDate("order_arrived_day"));
//	        	order.setMemo(rs.getString("order_memo"));
//	        	
//	
//	        }

	//Đếm tổng số đơn hàng
	public int getTotalOrderCount() {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM order_list";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return count;
	}

	//Đếm tổng đơn hàng theo tên khách hàng
	public int countOrdersByCustomerName(String keyword) {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM order_list WHERE user_name LIKE ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return count;
	}
	
	public boolean updateMemo(int id, String memo) {
		String sql = "UPDATE order_list SET order_memo = ? WHERE order_id = ?";
		
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,memo);
			ps.setInt(2,id);
			int row = ps.executeUpdate();
			
			ps.close();
			return row > 0;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}


