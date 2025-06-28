package com.admindao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.DBAccess;
import com.adminbean.AdminUserBean;

public class EmpDAO extends DBAccess {

	public ArrayList<AdminUserBean> getAllEmp() {
		ArrayList<AdminUserBean> empList = new ArrayList<>();
		String sql = "SELECT * FROM emp ";
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				AdminUserBean emp = new AdminUserBean();
				emp.setId(rs.getInt("id"));
				emp.setEmp_id(rs.getString("emp_id"));
				emp.setEmp_name(rs.getString("emp_name"));
				emp.setEmp_birth_date(rs.getDate("emp_birth_date"));
				emp.setEmp_address(rs.getString("emp_address"));
				emp.setEmp_entry_date(rs.getDate("emp_entry_date"));
				emp.setRole_id(rs.getInt("role_id"));
				emp.setEmp_position(rs.getString("emp_position"));
				emp.setEmp_grade(rs.getString("emp_grade"));
				emp.setPass(rs.getString("pass"));
				empList.add(emp);
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

	public boolean insertEmployee(AdminUserBean emp) {
		String sql = "INSERT INTO emp (emp_id, emp_name, role_id, emp_position, pass) VALUES (?, ?, ?, ?, ?)";
		System.out.println(emp.getEmp_position());
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, emp.getEmp_id());
			ps.setString(2, emp.getEmp_name());
			ps.setInt(3, emp.getRole_id());
			ps.setString(4, emp.getEmp_position());
			ps.setString(5, emp.getPass());

			int rows = ps.executeUpdate();
			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	//社員検索
	public List<AdminUserBean> searchEmployeeByKeyword(String keyword) {
	    List<AdminUserBean> list = new ArrayList<>();
	    String sql = "SELECT e.*, r.role_name FROM emp e LEFT JOIN role r ON e.role_id = r.id WHERE e.emp_id LIKE ? OR e.emp_name LIKE ?";

	    try {
	        connect();
	        PreparedStatement ps = getConnection().prepareStatement(sql);
	        ps.setString(1, "%" + keyword + "%");
	        ps.setString(2, "%" + keyword + "%");
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
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
	            list.add(emp);
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

	//AdminUser変更
	public boolean updateUser(AdminUserBean user) {
		String sql = """
				UPDATE emp
				SET emp_name = ?, role_id = ?, emp_position = ?, pass = ?
				WHERE emp_id = ?
				""";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);

			ps.setString(1, user.getEmp_name());
			ps.setInt(2, user.getRole_id());
			ps.setString(3, user.getEmp_position());
			ps.setString(4, user.getPass());
			ps.setString(5, user.getEmp_id());

			int result = ps.executeUpdate();
			return result > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public AdminUserBean getEmplById(String id) {
		AdminUserBean employee = new AdminUserBean();
		String sql = "SELECT * FROM emp WHERE emp_id = ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				employee.setId(rs.getInt("id"));
				employee.setEmp_id(rs.getString("emp_id"));
				employee.setEmp_name(rs.getString("emp_name"));
				employee.setEmp_birth_date(rs.getDate("emp_birth_day"));
				employee.setEmp_address(rs.getString("emp_adess"));
				employee.setEmp_entry_date(rs.getDate("emp_entry_date"));
				employee.setRole_id(rs.getInt("role_id"));
				employee.setEmp_position(rs.getString("emp_position"));
				employee.setEmp_grade(rs.getString("emp_grade"));
				employee.setPass(rs.getString("pass"));
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
}
