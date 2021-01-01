package com.javaweb.controller.web.api;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.model.BookedProductModel;
import com.javaweb.model.OrderModel;
import com.javaweb.service.IBookedProductService;
import com.javaweb.service.IOrderService;
import com.javaweb.utils.JsonStringUtil;

import java.io.IOException;
import java.util.HashMap;

@WebServlet (urlPatterns = {"/api-order"})
public class OrderAPI extends HttpServlet {
	private static final long serialVersionUID = 6424674043612850461L;

	@Inject
	public IOrderService orderService;
	
	@Inject
	public IBookedProductService bookedProductService;
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		OrderModel order = (OrderModel) JsonStringUtil.of(request.getReader()).toModel(OrderModel.class);
		int orderId = orderService.save(order);
		int[] bookedProducts = order.getBookedProductIds();
		for (int i = 0; i < bookedProducts.length; i++) {
			BookedProductModel bookedProduct = bookedProductService.findOne(bookedProducts[i]);
			bookedProduct.setOrderId(orderId);
			bookedProductService.update(bookedProduct);
		}
		HashMap<String, String> results = new HashMap<>();
		results.put("redirect",request.getContextPath() + "/order");
		objectMapper.writeValue(response.getOutputStream(), results);
		
	}

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		OrderModel order = JsonStringUtil.of(request.getReader()).toModel(OrderModel.class);
		if (order.getId() != -1 && orderService.findById(order.getId()).getStatus().equals("order")) {
			orderService.delete(order);
		}
		HashMap<String, String> results = new HashMap<>();
		results.put("type", "delete");
		results.put("result", "success");
		results.put("redirect",request.getContextPath() + "/order");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), results);
    }
}
