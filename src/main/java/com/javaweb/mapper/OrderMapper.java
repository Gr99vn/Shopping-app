package com.javaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.model.OrderModel;

public class OrderMapper implements RowMapper<OrderModel> {
	@Override
	public OrderModel mapRow(ResultSet rs) {
		try {
			OrderModel order = new OrderModel();
			order.setId(rs.getInt("id"));
			order.setStatus(rs.getString("status"));
			order.setOrderTime(rs.getTimestamp("order_time"));
			if (order.getStatus().equals("receive")) {
				order.setReceiveTime(rs.getTimestamp("receive_time"));
			}
			if (order.getStatus().equals("deliver")) {
				order.setReceiveTime(rs.getTimestamp("receive_time"));
				order.setDeliverTime(rs.getTimestamp("deliver_time"));
			}
			if (order.getStatus().equals("confirm")) {
				order.setReceiveTime(rs.getTimestamp("receive_time"));
				order.setDeliverTime(rs.getTimestamp("deliver_time"));
				order.setConfirmTime(rs.getTimestamp("confirm_time"));
			}
			order.setUserId(rs.getInt("user_id"));
			return order;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
