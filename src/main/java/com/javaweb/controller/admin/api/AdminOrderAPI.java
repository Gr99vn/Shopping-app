package com.javaweb.controller.admin.api;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.model.OrderModel;
import com.javaweb.service.IOrderService;
import com.javaweb.utils.JsonStringUtil;

@WebServlet (urlPatterns = {"/api-admin-order"})
public class AdminOrderAPI extends HttpServlet {
	private static final long serialVersionUID = 4772654346055715551L;
	 
	@Inject
	public IOrderService orderService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		OrderModel order = JsonStringUtil.of(request.getReader()).toModel(OrderModel.class);
		if (order.getId() != -1 && order.getStatus() != null) {
			orderService.update(order);
		}
		HashMap<String, String> results = new HashMap<>();
		results.put("type", "update");
		results.put("result", "success");
		results.put("status", order.getStatus());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), results);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		OrderModel order = JsonStringUtil.of(request.getReader()).toModel(OrderModel.class);
		if (order.getId() != -1) {
			orderService.delete(order);
		}
		HashMap<String, String> results = new HashMap<>();
		results.put("type", "delete");
		results.put("result", "success");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), results);
	}
}
