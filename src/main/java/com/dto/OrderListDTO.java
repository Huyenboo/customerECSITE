package com.dto;

import java.sql.Date;

public class OrderListDTO {

	private Date orderDay;
	private int orderId;
	private String companyName;
	private String proId;
	private String proName;
	private int amount;
	private double proPrice;
	private double subTotal;
	private Date arrivedDay;
	private String memo;
	public OrderListDTO(Date orderDay, int orderId, String companyName, String proId, String proName, int amount,
			double proPrice, double subTotal, Date arrivedDay, String memo) {
		super();
		this.orderDay = orderDay;
		this.orderId = orderId;
		this.companyName = companyName;
		this.proId = proId;
		this.proName = proName;
		this.amount = amount;
		this.proPrice = proPrice;
		this.subTotal = subTotal;
		this.arrivedDay = arrivedDay;
		this.memo = memo;
	}
	public OrderListDTO() {
		super();
	}
	public Date getOrderDay() {
		return orderDay;
	}
	public void setOrderDay(Date orderDay) {
		this.orderDay = orderDay;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getProPrice() {
		return proPrice;
	}
	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public Date getArrivedDay() {
		return arrivedDay;
	}
	public void setArrivedDay(Date arrivedDay) {
		this.arrivedDay = arrivedDay;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	}


