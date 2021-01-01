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
import com.javaweb.model.ProductModel;
import com.javaweb.service.IProductService;
import com.javaweb.utils.JsonStringUtil;

@WebServlet (urlPatterns = {"/api-admin-product"})
public class AdminProductAPI extends HttpServlet {
	private static final long serialVersionUID = 3338084955752522243L;
	
	@Inject
	public IProductService productService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ProductModel product = JsonStringUtil.of(request.getReader()).toModel(ProductModel.class);
		ProductModel productModel = productService.save(product);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), productModel);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ProductModel product = JsonStringUtil.of(request.getReader()).toModel(ProductModel.class);
		ProductModel productModel = productService.update(product);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), productModel);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ProductModel product = JsonStringUtil.of(request.getReader()).toModel(ProductModel.class);
		productService.delete(product);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, String> results = new HashMap<>();
		results.put("success", "true");
		results.put("id", product.getId()+"");
		objectMapper.writeValue(response.getOutputStream(), results);
	}
}
