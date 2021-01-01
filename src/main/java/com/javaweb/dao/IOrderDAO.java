package com.javaweb.dao;

import java.util.List;

import com.javaweb.model.OrderModel;

public interface IOrderDAO extends GenericDAO<OrderModel>{
	OrderModel findById(int id);
	List<OrderModel> findAllBy(OrderModel order, Integer except);
	List<OrderModel> findByStatus(String status);
	Integer insert(OrderModel newOrder);
	void update(OrderModel order);
	void delete(OrderModel order);
}
