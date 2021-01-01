package com.javaweb.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderModel {
    private int id;
    private int[] bookedProductIds;
    private List<BookedProductModel> bookedProducts;
    private String status;
    private Timestamp orderTime;
    private Timestamp receiveTime;
    private Timestamp deliverTime;
    private Timestamp confirmTime;
    private int userId;
    private UserModel user;

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BookedProductModel> getBookedProducts() {
        return bookedProducts;
    }

    public void setBookedProducts(List<BookedProductModel> bookedProducts) {
        this.bookedProducts = bookedProducts;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Timestamp getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Timestamp receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Timestamp getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Timestamp deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Timestamp getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Timestamp confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int[] getBookedProductIds() {
		return bookedProductIds;
	}

	public void setBookedProductIds(int[] bookedProductIds) {
		this.bookedProductIds = bookedProductIds;
	}
	
	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
}
