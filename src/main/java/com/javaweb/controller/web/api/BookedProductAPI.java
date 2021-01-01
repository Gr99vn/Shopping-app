package com.javaweb.controller.web.api;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.model.BookedProductModel;
import com.javaweb.model.UserModel;
import com.javaweb.service.IBookedProductService;
import com.javaweb.utils.JsonStringUtil;
import com.javaweb.utils.SessionUtil;

@WebServlet (urlPatterns = {"/api-booked-product"})
public class BookedProductAPI extends HttpServlet {
	private static final long serialVersionUID = -4760931884388143197L;

	@Inject
	public IBookedProductService bookedProductService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, String> results = new HashMap<String, String>();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		if (user == null) {
			results.put("success", "false");
			results.put("redirect", request.getContextPath() + "/login?action=login&message=not_login");
		} else {
			BookedProductModel bookedProduct = JsonStringUtil.of(request.getReader()).toModel(BookedProductModel.class);
			bookedProduct.setUserId(user.getId());
			bookedProduct.setSaleOff(0);
			BookedProductModel newBookedProduct = bookedProductService.save(bookedProduct);
			if (newBookedProduct != null) {
				results.put("success", "true");
				results.put("redirect",request.getContextPath() + "/cart");
			}
		}
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), results);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BookedProductModel bookedProduct = JsonStringUtil.of(request.getReader()).toModel(BookedProductModel.class);
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
		bookedProduct.setUserId(user.getId());
		bookedProduct.setSaleOff(0);
		BookedProductModel updatedBookedProduct = bookedProductService.update(bookedProduct);
		HashMap<String, String> results = new HashMap<>();
		results.put("success", "true");
		results.put("type", "modify");
		results.put("redirect",request.getContextPath() + "/cart");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), results);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BookedProductModel bookedProduct = JsonStringUtil.of(request.getReader()).toModel(BookedProductModel.class);
		bookedProductService.delete(bookedProduct);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, String> results = new HashMap<>();
		results.put("success", "true");
		results.put("type", "delete");
		results.put("redirect",request.getContextPath() + "/cart");
		objectMapper.writeValue(response.getOutputStream(), results);
	}
}
