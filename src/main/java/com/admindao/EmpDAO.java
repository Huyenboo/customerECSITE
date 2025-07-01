package com.admindao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.DBAccess;
import com.adminbean.AdminUserBean;

public class EmpDAO extends DBAccess {

	// Tổng số nhân viên toàn bộ
	public int getTotalEmployeeCount() {
		int total = 0;
		String sql = "SELECT COUNT(*) FROM emp";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return total;
	}

	// Tổng số theo từ khóa
	public int getTotalEmployeeCount(String keyword) {
		int total = 0;
		String sql = "SELECT COUNT(*) FROM emp e LEFT JOIN role r ON e.role_id = r.id WHERE e.emp_id LIKE ? OR e.emp_name LIKE ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return total;
	}

	// Hiển thị toàn bộ có phân trang
	public List<AdminUserBean> getAllEmpPaging(int start, int limit) {
		List<AdminUserBean> empList = new ArrayList<>();
		String sql = "SELECT e.*, r.role_name FROM emp e LEFT JOIN role r ON e.role_id = r.id ORDER BY e.id LIMIT ?, ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, limit);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				empList.add(getEmpFromResultSet(rs));
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return empList;
	}

	// Tìm kiếm có phân trang
	public List<AdminUserBean> searchEmployeeByKeywordPaging(String keyword, int start, int limit) {
		List<AdminUserBean> list = new ArrayList<>();
		String sql = "SELECT e.*, r.role_name FROM emp e LEFT JOIN role r ON e.role_id = r.id " +
				"WHERE e.emp_id LIKE ? OR e.emp_name LIKE ? ORDER BY e.id LIMIT ?, ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			ps.setInt(3, start);
			ps.setInt(4, limit);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(getEmpFromResultSet(rs));
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

	// Lấy chi tiết nhân viên theo ID
	public AdminUserBean getEmplById(String id) {
		AdminUserBean employee = null;
		String sql = "SELECT e.*, r.role_name FROM emp e LEFT JOIN role r ON e.role_id = r.id WHERE e.emp_id = ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				employee = getEmpFromResultSet(rs);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return employee;
	}

	// Đăng ký nhân viên
	public boolean insertEmployee(AdminUserBean emp) {
		String sql = "INSERT INTO emp (emp_id, emp_name, role_id, emp_position, pass) VALUES (?, ?, ?, ?, ?)";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, emp.getEmp_id());
			ps.setString(2, emp.getEmp_name());
			ps.setInt(3, emp.getRole_id());
			ps.setString(4, emp.getEmp_position());
			ps.setString(5, emp.getPass());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	// Cập nhật nhân viên
	public boolean updateUser(AdminUserBean user) {
		String sql = "UPDATE emp SET emp_name = ?, role_id = ?, emp_position = ?, pass = ? WHERE emp_id = ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, user.getEmp_name());
			ps.setInt(2, user.getRole_id());
			ps.setString(3, user.getEmp_position());
			ps.setString(4, user.getPass());
			ps.setString(5, user.getEmp_id());

			int row = ps.executeUpdate();

			ps.close();
			return row > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Dùng chung để lấy dữ liệu từ ResultSet
	private AdminUserBean getEmpFromResultSet(ResultSet rs) throws Exception {
		AdminUserBean emp = new AdminUserBean();
		emp.setId(rs.getInt("id"));
		emp.setEmp_id(rs.getString("emp_id"));
		emp.setEmp_name(rs.getString("emp_name"));
		emp.setEmp_birth_date(rs.getDate("emp_birth_date"));
		emp.setEmp_address(rs.getString("emp_address"));
		emp.setEmp_entry_date(rs.getDate("emp_entry_date"));
		emp.setRole_id(rs.getInt("role_id"));
		emp.setRole_name(rs.getString("role_name"));
		emp.setEmp_position(rs.getString("emp_position"));
		emp.setEmp_grade(rs.getString("emp_grade"));
		emp.setPass(rs.getString("pass"));
		return emp;
	}
	
	
	//delete emp
	public boolean deleteEmployeeById(String id) throws Exception {
	    String sql = "DELETE FROM emp WHERE id = ?";
	    connect();
	    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
	    	ps.setInt(1, Integer.parseInt(id));
	        return ps.executeUpdate() > 0;
	    } finally {
	        disconnect();
	    }
	    
	}
}
