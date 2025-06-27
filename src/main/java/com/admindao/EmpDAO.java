package com.admindao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.DBAccess;
import com.adminbean.AdminUserBean;

public class EmpDAO extends DBAccess{
	
	public ArrayList<AdminUserBean> getAllEmp (){
		ArrayList<AdminUserBean> empList = new ArrayList<>();
		String sql ="SELECT * FROM emp ";
		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
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
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			disconnect();
		}
		return empList;
		
		
		
	}

}
