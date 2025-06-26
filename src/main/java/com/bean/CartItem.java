package com.bean;

import java.io.Serializable;
import java.sql.Date;

public class CartItem implements Serializable {
	private int orderId;
	private String userId;
	private String userName;
	private String orderCode;
	private int orderAmount;
	private Date orderDay;
	private Date orderArrivedDay;
	private String orderMemo;
	private Product product;
	private int quantity;
	private String deliveryDate;
	
	
	CartItem item = new CartItem(
		    orderId, userId, userName, orderCode, orderAmount,
		    orderDay, orderArrivedDay, orderMemo, product, quantity, deliveryDate);
	
	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.deliveryDate = "未定"; // default value
	}

	public CartItem(Product product, int quantity, String deliveryDate) {
		this.product = product;
		this.quantity = quantity;
		this.deliveryDate = deliveryDate;
	}

	public CartItem(int orderId, String userId, String userName, String orderCode, int orderAmount, Date orderDay,
			Date orderArrivedDay, String orderMemo, Product product, int quantity, String deliveryDate) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.userName = userName;
		this.orderCode = orderCode;
		this.orderAmount = orderAmount;
		this.orderDay = orderDay;
		this.orderArrivedDay = orderArrivedDay;
		this.orderMemo = orderMemo;
		this.product = product;
		this.quantity = quantity;
		this.deliveryDate = deliveryDate;
	}

	public CartItem() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getSubtotal() {
		return product.getProUnitNum() * quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Date getOrderDay() {
		return orderDay;
	}

	public void setOrderDay(Date orderDay) {
		this.orderDay = orderDay;
	}

	public Date getOrderArrivedDay() {
		return orderArrivedDay;
	}

	public void setOrderArrivedDay(Date orderArrivedDay) {
		this.orderArrivedDay = orderArrivedDay;
	}

	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CartItem [orderId=" + orderId + ", userId=" + userId + ", userName=" + userName + ", orderCode="
				+ orderCode + ", orderAmount=" + orderAmount + ", orderDay=" + orderDay + ", orderArrivedDay="
				+ orderArrivedDay + ", orderMemo=" + orderMemo + ", product=" + product + ", quantity=" + quantity
				+ ", deliveryDate=" + deliveryDate + "]";
	}

}
