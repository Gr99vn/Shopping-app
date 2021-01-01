package com.javaweb.service;

import java.util.List;

import com.javaweb.model.OrderModel;

public interface IOrderService {
	OrderModel findById(int id);
	List<OrderModel> findOrderBy(OrderModel order, Integer except);
	List<OrderModel> findByStatus(String status);
	Integer save(OrderModel newOrder);
	void update(OrderModel order);
	void delete(OrderModel order);
}
