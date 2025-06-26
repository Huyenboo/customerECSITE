package com.admindao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.DBAccess;
import com.adminbean.AdminUserBean;


public class AdminLoginDAO extends DBAccess{
	
	public AdminUserBean login (String emp_id , String pass)
	throws Exception{
		
		connect();
		
		AdminUserBean user = null;
		
		try {
			
			Connection conn = getConnection();
			
			//名前、ロールid、ロール名、ロールコメントを持ってくる。
			String sql = "SELECT e.emp_name, e.role_id, r.role_name, r.description " +
	                "FROM emp e " +
	                "LEFT JOIN role r ON e.role_id = r.id " +
	                "WHERE e.emp_id = ? AND e.pass = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,emp_id);
			ps.setString(2, pass);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new AdminUserBean();
				user.setEmp_id(emp_id);
				user.setPass(pass);
				user.setEmp_name(rs.getString("emp_name"));			
				user.setRole_id(rs.getInt("role_id"));
				user.setRole_name(rs.getString("role_name"));
//				user.setRole_description(rs.getString("role_description"));
			}
		}finally {
			disconnect();
		}
		return user;
	}

}
