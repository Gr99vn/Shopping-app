package com.javaweb.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import com.javaweb.dao.IOrderDAO;
import com.javaweb.mapper.OrderMapper;
import com.javaweb.model.OrderModel;

public class OrderDAO extends AbstractDAO<OrderModel> implements IOrderDAO {
	@Override
	public OrderModel findById(int id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tbl_order WHERE id = ?");
		List<OrderModel> orders = query(sql.toString(), new OrderMapper(), id);
		return orders.isEmpty() ? null : orders.get(0);
	}
	
	@Override
	public List<OrderModel> findAllBy(OrderModel order, Integer except) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tbl_order WHERE user_id = ?");
		if (except == 0) {
			sql.append(" AND status = ?");
		} else if (except == 1) {
			sql.append(" AND status <> ?");
		}
		return query(sql.toString(), new OrderMapper(), order.getUserId(), order.getStatus());
	}
	
	@Override
	public List<OrderModel> findByStatus(String status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM tbl_order WHERE status = ?");
		return query(sql.toString(), new OrderMapper(), status);
	}

	@Override
	public Integer insert(OrderModel newOrder) {
		Timestamp orderTime = new Timestamp(System.currentTimeMillis());
		StringBuilder sql = new StringBuilder("INSERT INTO tbl_order(status, order_time, user_id)")
				.append(" VALUES(?, ?, ?)");
		return insert(sql.toString(), newOrder.getStatus(), orderTime, newOrder.getUserId());
	}

	@Override
	public void update(OrderModel order) {
		Timestamp time = new Timestamp(System.currentTimeMillis()); 
		StringBuilder sql = new StringBuilder("UPDATE tbl_order SET status = ?,");
		if (order.getStatus().equals("receive")) {
			sql.append(" receive_time = ?  WHERE id = ?");
		} else if (order.getStatus().equals("deliver")) {
			sql.append(" deliver_time = ?  WHERE id = ?");
		} else if (order.getStatus().equals("confirm")) {
			sql.append(" confirm_time = ?  WHERE id = ?");
		}
		updateOrDelete(sql.toString(), order.getStatus(), time, order.getId());
	}

	@Override
	public void delete(OrderModel order) {
		String sql = "DELETE FROM tbl_order WHERE id = ?";
		updateOrDelete(sql, order.getId());
	}
}
