package com.javaweb.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.model.ColorModel;
import com.javaweb.model.OrderModel;
import com.javaweb.model.ProductModel;
import com.javaweb.service.IBookedProductService;
import com.javaweb.service.IColorService;
import com.javaweb.service.IOrderService;
import com.javaweb.service.IProductService;
import com.javaweb.service.IUserService;

@WebServlet(urlPatterns = { "/admin-home", "/admin-product-add", "/admin-product-modify", "/admin-product-search", "/admin-orders"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = -6922552214992789789L;

	@Inject
	public IColorService colorService;

	@Inject
	public IProductService productService;
	
	@Inject
	public IOrderService orderService;
	
	@Inject
	public IBookedProductService bookedProductService;
	
	@Inject
	public IUserService userService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getRequestURI().startsWith(request.getContextPath() + "/admin-home")) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/home.jsp");
			rd.forward(request, response);
		} else if (request.getRequestURI().startsWith(request.getContextPath() + "/admin-product-add")) {
			List<ColorModel> colors = colorService.findAll();
			request.setAttribute("COLORS", colors);
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/add_product.jsp");
			rd.forward(request, response);
		} else if (request.getRequestURI().startsWith(request.getContextPath() + "/admin-product-modify")) {
			List<ColorModel> colors = colorService.findAll();
			request.setAttribute("COLORS", colors);
			Integer id = Integer.parseInt(request.getParameter("id"));
			if (id != null) {
				ProductModel product = productService.findById(id);
				request.setAttribute("PRODUCT", product);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/modify_product.jsp");
			rd.forward(request, response);
		} else if (request.getRequestURI().startsWith(request.getContextPath() + "/admin-product-search")) {
			String name = request.getParameter("name");
			if (name != null) {
				List<ProductModel> products = productService.findByName(name);
				request.setAttribute("PRODUCTLIST", products);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/search_product.jsp");
			rd.forward(request, response);
		} else if (request.getRequestURI().startsWith(request.getContextPath() + "/admin-orders")) {
			String status = request.getParameter("status");
			if (status != null) {
				List<OrderModel> orders = orderService.findByStatus(status);
				for (OrderModel ord : orders) {
					ord.setBookedProducts(bookedProductService.findByUserIdAndOrder(ord.getUserId(), ord.getId()));
					ord.setUser(userService.findById(ord.getUserId()));
				}
				request.setAttribute("ORDERLIST", orders);
				request.setAttribute("TYPE", "order");
				request.setAttribute("STATUS", status);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/order.jsp");
			rd.forward(request, response);
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
