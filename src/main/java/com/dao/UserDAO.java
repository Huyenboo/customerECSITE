package com.dao;

import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.DBAccess;
import com.bean.User;

public class UserDAO extends DBAccess {
	public ArrayList<User> userList() {
		ArrayList<User> list = new ArrayList<>();

		String sql = "SELECT * FROM app_user";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setCompanyId(rs.getString("company_id"));
				user.setCompanyName(rs.getString("company_name"));
				user.setCompanyAddress(rs.getString("company_address"));
				user.setPresidentPhoneNum(rs.getString("president_phone_num"));
				user.setManagerName(rs.getString("manager_name"));
				user.setManagerPhoneNum(rs.getString("manager_phone_num"));
				user.setManagerEmail(rs.getString("manager_email"));
				user.setPassword(rs.getString("pass"));
				user.setStatus(rs.getString("status"));
				user.setRequestedAt(rs.getTimestamp("requested_at"));
				user.setApprovedAt(rs.getTimestamp("approved_at"));
				user.setApprovedBy(rs.getInt("approved_by"));
				user.setRejectionReason(rs.getString("rejection_reason"));
				list.add(user);
			}

			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return list.isEmpty() ? null : list;
	}

	// Mã hóa mật khẩu SHA-256
	public String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(password.getBytes());
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				hexString.append(String.format("%02x", b));
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean registerUser(User user) {
		String sql = "INSERT INTO app_user (company_id,company_name, company_address, president_phone_num, manager_name, manager_phone_num, manager_email, pass, status, requested_at) "
				+
				"VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, NOW())";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, user.getCompanyId());
			ps.setString(2, user.getCompanyName());
			ps.setString(3, user.getCompanyAddress());
			ps.setString(4, user.getPresidentPhoneNum());
			ps.setString(5, user.getManagerName());
			ps.setString(6, user.getManagerPhoneNum());
			ps.setString(7, user.getManagerEmail());
			ps.setString(8, hashPassword(user.getPassword()));
			ps.setString(9, "pending"); // status = pending
			int result = ps.executeUpdate();
			ps.close();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	public User loginUser(String phone, String rawPassword) {
		String sql = "SELECT * FROM app_user WHERE manager_phone_num = ? AND pass = ?"; // chỉ cho phép đã được duyệt (status = 1)

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, phone);
			ps.setString(2, hashPassword(rawPassword)); // hash để so sánh

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setCompanyId(rs.getString("company_id"));
				user.setCompanyName(rs.getString("company_name"));
				user.setCompanyAddress(rs.getString("company_address"));
				user.setManagerName(rs.getString("manager_name"));
				user.setManagerPhoneNum(rs.getString("manager_phone_num"));
				user.setPassword(rs.getString("pass"));
				user.setStatus(rs.getString("status"));
				rs.close();
				ps.close();
				return user;
			}

			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return null;
	}

	// Kiểm tra mật khẩu hiện tại
	public boolean checkCurrentPassword(String phone, String rawPassword) {
		String sql = "SELECT * FROM app_user WHERE manager_phone_num = ? AND pass = ? AND status = 'accept'";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, phone);
			ps.setString(2, hashPassword(rawPassword));
			ResultSet rs = ps.executeQuery();

			boolean exists = rs.next();
			rs.close();
			ps.close();
			return exists;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return false;
	}

	// Cập nhật mật khẩu mới
	public boolean updatePassword(String phone, String newPassword) {
		String sql = "UPDATE app_user SET pass = ? WHERE manager_phone_num = ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, hashPassword(newPassword));
			ps.setString(2, phone);
			int result = ps.executeUpdate();
			ps.close();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return false;
	}

	public List<User> getUserPending() {
		String sql = "SELECT * FROM app_user where status = ?";
		List<User> listUserPending = new ArrayList<>();

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);

			ps.setString(1, "pending");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setCompanyId(rs.getString("company_id"));
				user.setCompanyName(rs.getString("company_name"));
				user.setCompanyAddress(rs.getString("company_address"));
				user.setPresidentPhoneNum(rs.getString("president_phone_num"));
				user.setManagerName(rs.getString("manager_name"));
				user.setManagerPhoneNum(rs.getString("manager_phone_num"));
				user.setManagerEmail(rs.getString("manager_email"));
				user.setPassword(rs.getString("pass"));
				user.setStatus(rs.getString("status"));
				user.setRequestedAt(rs.getTimestamp("requested_at"));
				user.setApprovedAt(rs.getTimestamp("approved_at"));
				user.setApprovedBy(rs.getInt("approved_by"));
				user.setRejectionReason(rs.getString("rejection_reason"));
				listUserPending.add(user);
			}

			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return listUserPending;

	}

	//	public boolean userUpdateStatus(int userId) {
	//
	//		String sql = "UPDATE app_user set status=? where id=? ";
	//
	//		try {
	//			connect();
	//			PreparedStatement ps = getConnection().prepareStatement(sql);
	//			ps.setString(1, "pending");
	//			ps.setInt(2, userId);
	//			int result = ps.executeUpdate();
	//			ps.close();
	//			return result > 0;
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		} finally {
	//			disconnect();
	//		}
	//		return false;
	//
	//	}
	public boolean userUpdateStatus(int userId, String status) {

		String sql = "UPDATE app_user SET status = ? WHERE id = ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, status); // Cho phép truyền vào bất kỳ trạng thái nào
			ps.setInt(2, userId);
			int result = ps.executeUpdate();
			ps.close();
			return result > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	// Tìm kiếm theo tên công ty + phân trang
	public List<User> searchByCompanyName(String keyword, int offset, int limit) {
		List<User> list = new ArrayList<>();
		String sql = "SELECT * FROM app_user WHERE company_name LIKE ? LIMIT ? OFFSET ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setInt(2, limit);
			ps.setInt(3, offset);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setCompanyId(rs.getString("company_id"));
				user.setCompanyName(rs.getString("company_name"));
				user.setCompanyAddress(rs.getString("company_address"));
				user.setPresidentPhoneNum(rs.getString("president_phone_num"));
				user.setManagerName(rs.getString("manager_name"));
				user.setManagerPhoneNum(rs.getString("manager_phone_num"));
				user.setManagerEmail(rs.getString("manager_email"));
				user.setPassword(rs.getString("pass"));
				user.setStatus(rs.getString("status"));
				user.setRequestedAt(rs.getTimestamp("requested_at"));
				user.setApprovedAt(rs.getTimestamp("approved_at"));
				user.setApprovedBy(rs.getInt("approved_by"));
				user.setRejectionReason(rs.getString("rejection_reason"));
				list.add(user);
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

	// Đếm tổng số bản ghi phù hợp điều kiện tìm kiếm
	public int countSearch(String keyword) {
		String sql = "SELECT COUNT(*) FROM app_user WHERE company_name LIKE ?";
		int count = 0;

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

}
