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
import com.javaweb.model.ColorSourceModel;
import com.javaweb.service.IColorSourceService;
import com.javaweb.utils.JsonStringUtil;

@WebServlet (urlPatterns = {"/api-admin-color-source"})
public class AdminColorSourceAPI extends HttpServlet {
	private static final long serialVersionUID = 3338084955752522243L;
	
	@Inject
	public IColorSourceService colorSourceService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ColorSourceModel colorSource = JsonStringUtil.of(request.getReader()).toModel(ColorSourceModel.class);
		ColorSourceModel colorSourceModel = colorSourceService.save(colorSource);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getOutputStream(), colorSourceModel);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		ColorSourceModel colorSource = JsonStringUtil.of(request.getReader()).toModel(ColorSourceModel.class);
		colorSourceService.delete(colorSource);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, String> results = new HashMap<>();
		results.put("success", "true");
		results.put("id", colorSource.getId()+"");
		objectMapper.writeValue(response.getOutputStream(), results);
	}
}
