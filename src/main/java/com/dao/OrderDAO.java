package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.DBAccess;
import com.bean.CartItem;
import com.bean.Product;
import com.bean.SalesSummary;
import com.dto.OrderListDTO;

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
	//	public CartItem getOrderById(int orderId) {
	//		CartItem order = null;
	//		String sql = """
	//				    SELECT o.*, u.user_name, p.pro_name
	//				    FROM order_list o
	//				    JOIN app_user u ON  u.company_name = o.user_name
	//				    JOIN product p ON o.order_code = p.pro_id
	//				    WHERE o.order_id = ?
	//				""";
	//
	//		try {
	//			connect();
	//			PreparedStatement ps = getConnection().prepareStatement(sql);
	//			ps.setInt(1, orderId);
	//			ResultSet rs = ps.executeQuery();
	//
	//			if (rs.next()) {
	//				order = new CartItem();
	//				order.setOrderId(rs.getInt("order_id"));
	//				order.setUserId(rs.getString("user_id"));
	//				order.setUserName(rs.getString("user_name"));
	//				order.setOrderCode(rs.getString("order_code"));
	//				order.setOrderAmount(rs.getInt("order_amount"));
	//				order.setOrderDay(rs.getDate("order_day"));
	//				order.setOrderArrivedDay(rs.getDate("order_arrived_day"));
	//				order.setOrderMemo(rs.getString("order_memo"));
	//				order.setQuantity(rs.getInt("order_amount")); // Đồng bộ quantity
	//
	//				// Set product
	//				Product p = new Product();
	//				p.setProId(rs.getString("order_code"));
	//				p.setProName(rs.getString("pro_name"));
	//				order.setProduct(p);
	//
	//				order.setDeliveryDate(rs.getString("order_arrived_day"));
	//			}
	//
	//			rs.close();
	//			ps.close();
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		} finally {
	//			disconnect();
	//		}
	//
	//		return order;
	//	}

	public CartItem getOrderById(int orderId) {
		CartItem order = null;
		String sql = """
				    SELECT o.*, u.company_name, p.pro_name
				    FROM order_list o
				    JOIN app_user u ON u.company_name = o.user_name
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
				order.setUserName(rs.getString("company_name")); // Sửa ở đây
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

	public boolean updateMemo(int id, String memo) {
		String sql = "UPDATE order_list SET order_memo = ? WHERE order_id = ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, memo);
			ps.setInt(2, id);
			int row = ps.executeUpdate();

			ps.close();
			return row > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Lấy danh sách có phân trang
	public List<SalesSummary> getSalesSummaryByMonth(String month, int offset, int limit) {
		List<SalesSummary> list = new ArrayList<>();
		int totalRevenue = 0;

		String sql = """
				    SELECT o.order_day, u.company_name, p.pro_id, p.pro_name,
				           o.order_amount, p.pro_price, (o.order_amount * p.pro_price) AS subtotal
				    FROM order_list o
				    JOIN product p ON o.order_code = p.pro_id
				    JOIN app_user u ON o.user_id = u.company_id
				    WHERE DATE_FORMAT(o.order_day, '%Y-%m') = ?
				    ORDER BY o.order_day DESC, u.company_name, p.pro_id
				    LIMIT ? OFFSET ?
				""";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, month);
			ps.setInt(2, limit);
			ps.setInt(3, offset);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				SalesSummary s = new SalesSummary();
				s.setOrderDay(rs.getDate("order_day"));
				s.setCompanyName(rs.getString("company_name"));
				s.setProductId(rs.getString("pro_id"));
				s.setProductName(rs.getString("pro_name"));
				s.setOrderAmount(rs.getInt("order_amount"));
				s.setProductPrice(rs.getDouble("pro_price"));
				s.setSubtotal(rs.getInt("subtotal"));
				totalRevenue += rs.getInt("subtotal");
				list.add(s);
			}

			rs.close();
			ps.close();

			// Gán tổng doanh thu cho tất cả phần tử
			for (SalesSummary s : list) {
				s.setTotalRevenue(totalRevenue);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list;
	}

	public int countSalesSummaryByMonth(String month) {
		int count = 0;

		String sql = """
				    SELECT COUNT(*)
				    FROM order_list o
				    JOIN product p ON o.order_code = p.pro_id
				    JOIN app_user u ON o.user_id = u.company_id
				    WHERE DATE_FORMAT(o.order_day, '%Y-%m') = ?
				""";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, month);
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

	public List<OrderListDTO> getAllOrderList() {
		List<OrderListDTO> list = new ArrayList<>();

		String sql = """
				    SELECT o.order_day, o.order_id, u.company_name, p.pro_id, p.pro_name,
				           o.order_amount, p.pro_price, (o.order_amount * p.pro_price) AS subtotal,
				           o.order_arrived_day, o.order_memo
				    FROM order_list o
				    JOIN product p ON o.order_code = p.pro_id
				    JOIN app_user u ON o.user_id = u.company_id
				    ORDER BY o.order_day DESC
				""";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				OrderListDTO dto = new OrderListDTO();
				dto.setOrderDay(rs.getDate("order_day"));
				dto.setOrderId(rs.getInt("order_id"));
				dto.setCompanyName(rs.getString("company_name"));
				dto.setProId(rs.getString("pro_id"));
				dto.setProName(rs.getString("pro_name"));
				dto.setAmount(rs.getInt("order_amount"));
				dto.setProPrice(rs.getDouble("pro_price"));
				dto.setSubTotal(rs.getDouble("subtotal"));
				dto.setArrivedDay(rs.getDate("order_arrived_day"));
				dto.setMemo(rs.getString("order_memo"));

				list.add(dto);
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

	public List<OrderListDTO> getAllOrderList(int offset, int limit) {
		List<OrderListDTO> list = new ArrayList<>();
		String sql = """
				    SELECT o.order_day, o.order_id, u.company_name, p.pro_id, p.pro_name,
				           o.order_amount, p.pro_price, (o.order_amount * p.pro_price) AS subtotal,
				           o.order_arrived_day, o.order_memo
				    FROM order_list o
				    JOIN product p ON o.order_code = p.pro_id
				    JOIN app_user u ON o.user_id = u.company_id
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
				OrderListDTO dto = new OrderListDTO(
						rs.getDate("order_day"),
						rs.getInt("order_id"),
						rs.getString("company_name"),
						rs.getString("pro_id"),
						rs.getString("pro_name"),
						rs.getInt("order_amount"),
						rs.getDouble("pro_price"),
						rs.getDouble("subtotal"),
						rs.getDate("order_arrived_day"),
						rs.getString("order_memo"));
				list.add(dto);
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

	public List<OrderListDTO> searchOrdersByCustomerName(String keyword, int offset, int limit) {
		List<OrderListDTO> list = new ArrayList<>();

		String sql = """
				    SELECT o.order_day, o.order_id, u.company_name, p.pro_id, p.pro_name,
				           o.order_amount, p.pro_price, (o.order_amount * p.pro_price) AS subtotal,
				           o.order_arrived_day, o.order_memo
				    FROM order_list o
				    JOIN product p ON o.order_code = p.pro_id
				    JOIN app_user u ON o.user_id = u.company_id
				    WHERE u.company_name LIKE ?
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
				OrderListDTO dto = new OrderListDTO(
						rs.getDate("order_day"),
						rs.getInt("order_id"),
						rs.getString("company_name"),
						rs.getString("pro_id"),
						rs.getString("pro_name"),
						rs.getInt("order_amount"),
						rs.getDouble("pro_price"),
						rs.getDouble("subtotal"),
						rs.getDate("order_arrived_day"),
						rs.getString("order_memo"));
				list.add(dto);
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

	public int countOrdersByCustomerName(String keyword) {
		int count = 0;
		String sql = """
				    SELECT COUNT(*)
				    FROM order_list o
				    JOIN app_user u ON o.user_id = u.company_id
				    WHERE u.company_name LIKE ?
				""";

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
	public boolean updateOrder(int orderId, int quantity, String arrivedDay, String memo) {
	    String sql = """
	        UPDATE order_list 
	        SET order_amount = ?, order_arrived_day = ?, order_memo = ?
	        WHERE order_id = ?
	    """;

	    try {
	        connect();
	        PreparedStatement ps = getConnection().prepareStatement(sql);
	        ps.setInt(1, quantity);
	        ps.setDate(2, java.sql.Date.valueOf(arrivedDay));
	        ps.setString(3, memo);
	        ps.setInt(4, orderId);

	        int rows = ps.executeUpdate();
	        ps.close();

	        return rows > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        disconnect();
	    }
	}

}