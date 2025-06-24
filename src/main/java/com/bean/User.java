package com.bean;

import java.sql.Timestamp;

public class User {
	private int id;
	private String companyId;
	private String companyName;
	private String companyAddress;
	private String presidentPhoneNum;
	private String managerName;
	private String managerEmail;
	private String managerPhoneNum;
	private String password;
	private String status;
	private Timestamp requestedAt;
	private Timestamp approvedAt;
	private Integer approvedBy;
	private String rejectionReason;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getCompanyId() { return companyId; }
	public void setCompanyId(String companyId) { this.companyId = companyId; }
	
	public String getCompanyName() { return companyName; }
	public void setCompanyName(String companyName) { this.companyName = companyName; }
	
	public String getCompanyAddress() { return companyAddress; }
	public void setCompanyAddress(String companyAddress) { this.companyAddress = companyAddress; }
	
	public String getPresidentPhoneNum() { return presidentPhoneNum; }
	public void setPresidentPhoneNum(String presidentPhoneNum) { this.presidentPhoneNum = presidentPhoneNum; }
	
	public String getManagerName() { return managerName; }
	public void setManagerName(String managerName) { this.managerName = managerName; }
	
	public String getManagerEmail() { return managerEmail; }
	public void setManagerEmail(String managerEmail) { this.managerEmail = managerEmail; }
	
	public String getManagerPhoneNum() { return managerPhoneNum; }
	public void setManagerPhoneNum(String managerPhoneNum) { this.managerPhoneNum = managerPhoneNum; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getStatus() { return status; }
	public void setStatus(String status ) { this.status = status ; }
	
	public Timestamp getRequestedAt() { return requestedAt; }
	public void setRequestedAt(Timestamp requestedAt) { this.requestedAt = requestedAt; }
	
	public Timestamp getApprovedAt() { return approvedAt; }
	public void setApprovedAt(Timestamp approvedAt) { this.approvedAt = approvedAt; }
	
	public Integer getApprovedBy() { return approvedBy; }
	public void setApprovedBy(Integer approvedBy) { this.approvedBy = approvedBy; }
	
	public String getRejectionReason() { return rejectionReason; }
	public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
}
