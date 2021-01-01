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
import com.javaweb.service.IUserService;
import com.javaweb.utils.JsonStringUtil;
import com.javaweb.utils.SessionUtil;

@WebServlet (urlPatterns = {"/api-login"})
public class LoginAPI extends HttpServlet {
	private static final long serialVersionUID = -1121571422325372643L;

	@Inject
	public IUserService userService;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, String> results = new HashMap<String, String>();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserModel user = JsonStringUtil.of(request.getReader()).toModel(UserModel.class);
		int status = 1;
		user = userService.findByUsernamePasswordAndStatus(user.getUsername(), user.getPassword(), status);
		if (user != null) {
			SessionUtil.getInstance().putValue(request, "USERMODEL", user);
			results.put("success", "true");
			if (user.getRole().getCode().equals("admin")) {
				results.put("redirect", request.getContextPath() + "/admin-home");
			} else if (user.getRole().getCode().equals("user")) {
				results.put("redirect", request.getContextPath() + "/home?pageMaxItem=12&currentPage=1");
			}
		} else {
			results.put("success", "false");
			results.put("redirect", request.getContextPath() + "/login?action=login&message=username_password_invalid");
		}
		objectMapper.writeValue(response.getOutputStream(), results);
	}
}
