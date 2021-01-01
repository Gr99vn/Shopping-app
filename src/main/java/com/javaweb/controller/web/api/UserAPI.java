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
import com.javaweb.model.UserModel;
import com.javaweb.service.IProductService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.JsonStringUtil;

@WebServlet(urlPatterns = {"/api-user"})
public class UserAPI extends HttpServlet {
	private static final long serialVersionUID = 9049698711137836231L;
	
	@Inject
	public IUserService userService;
	
	@Inject
	public IProductService productService;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserModel user = JsonStringUtil.of(request.getReader()).toModel(UserModel.class);
		user.setStatus(1);
		user.setRoleId(2);
		HashMap<String, String> results = new HashMap<String, String>();
		if (userService.findByUsername(user.getUsername())) {
			results.put("success", "false");
			results.put("detail", "username already exist");
		}
		else {
			userService.save(user);
			results.put("success", "true");
		}
		objectMapper.writeValue(response.getOutputStream(), results);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserModel user = JsonStringUtil.of(request.getReader()).toModel(UserModel.class);
		userService.update(user);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), "{}");
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserModel user = JsonStringUtil.of(request.getReader()).toModel(UserModel.class);
		userService.delete(user);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), "{}");
	}
}
