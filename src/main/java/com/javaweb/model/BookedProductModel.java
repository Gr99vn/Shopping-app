package com.javaweb.model;

import java.sql.Timestamp;

public class BookedProductModel {
	private int id;
	private int userId;
	private UserModel user;
	private int productId;
	private ProductModel product;
	private int colorSourceId;
	private ColorSourceModel colorSource;
	private float saleOff;
	private Timestamp time;
	private int quantity;
	private int orderId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public ProductModel getProduct() {
		return product;
	}
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	public int getColorSourceId() {
		return colorSourceId;
	}
	public void setColorSourceId(int colorSourceId) {
		this.colorSourceId = colorSourceId;
	}
	public ColorSourceModel getColorSource() {
		return colorSource;
	}
	public void setColorSource(ColorSourceModel colorSource) {
		this.colorSource = colorSource;
	}
	public float getSaleOff() {
		return saleOff;
	}
	public void setSaleOff(float saleOff) {
		this.saleOff = saleOff;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
