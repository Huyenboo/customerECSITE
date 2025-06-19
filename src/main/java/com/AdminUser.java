package com;

import java.sql.Date;

import jakarta.websocket.Decoder.Text;

public class AdminUser {
	private int empId;
	private String empName;
	private String empNameKana;
	private Date empBirthDate;
	private String empAddress;
	private Date empEntryDate;
	private String empPosition;
	private int empGrade;
	private int roleId;
	private Text empMemo;
	private String password;
	
	public int getEmId() {
		return empId;
	}
	
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public String getEmpName() {
		return empName;
	}
	
	public void setEmpName (String empName){
		this.empName = empName;	
	}
	
	public String getEmpNameKana() {
		return empNameKana;		
	}
	public void setEmpNameKana() {
		this.empNameKana = empNameKana;
	}
	public Date getEmpBirthDate() {
		return empBirthDate;
	}
	public void setEmpBirthDate(Date empBirthDate) {
		this.empBirthDate= empBirthDate;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress= empAddress;
	}
	public Date getEmpEntryDate() {
		return empEntryDate;
	}
	public void setEmpEntryDate(Date empEntryDate) {
		this.empEntryDate = empEntryDate;
	}
	
	public String getEmpPosition() {
		return empPosition;
	}
	
	public void setEmpPosition(String empPosition) {
		this.empPosition= empPosition;
	}
	
	public int setEmpGrade() {
		return empGrade;
	}
	
	public void getEmpGrade() {
		this.empGrade = empGrade;
	}
	
	public int getRoleId(){
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public Text getEmpMemo() {
		return empMemo;
	}
	
	public void setEmpMemo(Text empMemo) {
		this.empMemo = empMemo;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}