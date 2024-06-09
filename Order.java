package com.example.email.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
	
	@Id
	private String orderId;
	private String userId;
	private List<Product> Products;
	private double totalPrice;
	private String status;
	private String orderDate;
	private Date deliveryDate;
	private String deliveryOptionId;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Product> getProducts() {
		return Products;
	}
	public void setProducts(List<Product> products) {
		Products = products;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date date) {
		this.deliveryDate = date;
	}
	public String getDeliveryOptionId() {
		return deliveryOptionId;
	}
	public void setDeliveryOptionId(String deliveryOptionId) {
		this.deliveryOptionId = deliveryOptionId;
	}
}
