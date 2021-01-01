package com.javaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.javaweb.dao.IOrderDAO;
import com.javaweb.model.OrderModel;
import com.javaweb.service.IOrderService;

public class OrderService implements IOrderService {
	@Inject
	public IOrderDAO orderDAO;
	
	@Override
	public OrderModel findById(int id) {
		return orderDAO.findById(id);
	}
	
	@Override
	public List<OrderModel> findOrderBy(OrderModel order, Integer except) {
		return orderDAO.findAllBy(order, except);
	}
	
	@Override
	public List<OrderModel> findByStatus(String status) {
		return orderDAO.findByStatus(status);
	}

	@Override
	public Integer save(OrderModel newOrder) {
		return orderDAO.insert(newOrder);
	}

	@Override
	public void update(OrderModel order) {
		orderDAO.update(order);
	}

	@Override
	public void delete(OrderModel order) {
		orderDAO.delete(order);
	}
}
