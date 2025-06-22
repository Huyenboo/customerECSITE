package com.admindao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.DBAccess;
import com.adminbean.AdminUser;

public class AdminUserDAO extends DBAccess {
	
	public AdminUser login(String idInput, String password) {
		AdminUser user = null;
		
		try {
			this.connect(); // 
			Connection conn = this.getConnection();
			
			String sql;
			boolean isEmpId = idInput.matches("\\d+"); // nếu chỉ toàn số → là emp_idsEmpId = idInput.matches(\\d+)
			
			if(isEmpId) {
				sql = "SELECT * FROM emp WHERE emp_id = ?  AND password = ?";
			}else {
				sql = "SELECT * FROM emp WHERE role_id = ? AND password = ?";
				
			}

			
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, idInput);
				ps.setString(2, password);
				
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					user = new AdminUser();
					user.setEmpId(rs.getInt("emp_id"));
					user.setPassword(rs.getString("password"));
					user.setRoleId(rs.getInt("role_id"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.disconnect(); //
		}
		
		return user;
	}
}
