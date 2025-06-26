package com.adminbean;


//empテーブルを参照 管理画面ログインで使用
import java.time.LocalDateTime;

public class AdminUserBean {
	
	private int id;
	private String emp_id;
	private String emp_name;
	private LocalDateTime emp_birth_date;
	private String emp_address;
	private LocalDateTime emp_entry_date;
	private int    role_id ;
	private String emp_postion;
	private String emp_grade ;
	private String pass ;
	
	//外部結合用（role_id)
	private String role_name;
//	private String role_description;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public LocalDateTime getEmp_birth_date() {
		return emp_birth_date;
	}
	public void setEmp_birth_date(LocalDateTime emp_birth_date) {
		this.emp_birth_date = emp_birth_date;
	}
	public String getEmp_address() {
		return emp_address;
	}
	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}
	public LocalDateTime getEmp_entry_date() {
		return emp_entry_date;
	}
	public void setEmp_entry_date(LocalDateTime emp_entry_date) {
		this.emp_entry_date = emp_entry_date;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getEmp_postion() {
		return emp_postion;
	}
	public void setEmp_postion(String emp_postion) {
		this.emp_postion = emp_postion;
	}
	public String getEmp_grade() {
		return emp_grade;
	}
	public void setEmp_grade(String emp_grade) {
		this.emp_grade = emp_grade;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
//	public String getRole_description() {
//		return role_description;
//	}
//	public void setRole_description(String role_description) {
//		this.role_description = role_description;
//	}

	

}
