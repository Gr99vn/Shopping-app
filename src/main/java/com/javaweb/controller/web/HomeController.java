package com.javaweb.controller.web;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.model.BookedProductModel;
import com.javaweb.model.OrderModel;
import com.javaweb.model.ProductModel;
import com.javaweb.model.UserModel;
import com.javaweb.paging.PageRequest;
import com.javaweb.paging.Pageble;
import com.javaweb.service.IBookedProductService;
import com.javaweb.service.IOrderService;
import com.javaweb.service.IProductService;
import com.javaweb.service.IUserService;
import com.javaweb.sort.Sorter;
import com.javaweb.utils.JspFormUtil;
import com.javaweb.utils.SessionUtil;


@WebServlet(urlPatterns = {"/home","/product", "/cart", "/order", "/login", "/logout", "/register", "/search"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = -1433144179868655476L;

	@Inject
	public IUserService userService;
	
	@Inject
	public IProductService productService;
	
	@Inject
	public IBookedProductService bookedProductService;

	@Inject
	public IOrderService orderService;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		String action = request.getParameter("action");
		String productId = request.getParameter("pid");
		String cartId = request.getParameter("cid");
 		
		//		product
		if (request.getRequestURI().startsWith(request.getContextPath()+"/product") && action == null && cartId == null && productId != null) {
			ProductModel product = productService.findById(Integer.parseInt(productId));
			request.setAttribute("PRODUCT", product);
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/product.jsp");
			rd.forward(request, response);
		}
		//		cart
		else if (request.getRequestURI().startsWith(request.getContextPath()+"/cart") && action == null) {
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
			if (user != null) {
				List<BookedProductModel> bookedProducts = bookedProductService.findByUserIdAndOrder(user.getId(), null);
				request.setAttribute("CARTLIST", bookedProducts);
				RequestDispatcher rd = request.getRequestDispatcher("/views/web/cart.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/login?action=login&message=not_login");
			}
		}
		//		order
		else if (request.getRequestURI().startsWith(request.getContextPath()+"/order") && action == null) {
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
			if (user != null) {
				OrderModel order = new OrderModel();
				order.setUserId(user.getId());
				order.setStatus("confirm");
				List<OrderModel> orders = orderService.findOrderBy(order, 1);
				for (OrderModel ord : orders) {
					ord.setBookedProducts(bookedProductService.findByUserIdAndOrder(ord.getUserId(), ord.getId()));
				}
				request.setAttribute("ORDERLIST", orders);
				RequestDispatcher rd = request.getRequestDispatcher("/views/web/order.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/login?action=login&message=not_login");
			}
		}
		//		login
		else if (request.getRequestURI().startsWith(request.getContextPath()+"/login") && action.equals("login")) {
			String message = request.getParameter("message");
			String type = request.getParameter("type");
			if (message != null) {
				request.setAttribute("message", resourceBundle.getString(message));
			}
			if (type != null) {
				request.setAttribute("type", type);
			}
 			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} 
		//		logout
		else if (request.getRequestURI().startsWith(request.getContextPath()+"/logout") && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath() + "/login?action=login");
		} 
		//		register
		else if (request.getRequestURI().startsWith(request.getContextPath()+"/register") && action.equals("register")) {
			String message = request.getParameter("message");
			if (message != null) {
				request.setAttribute("message", resourceBundle.getString(message));
			}
 			RequestDispatcher rd = request.getRequestDispatcher("/views/web/register.jsp");
			rd.forward(request, response);
		}
		//		search
		else if (request.getRequestURI().startsWith(request.getContextPath() + "/search")) {
			ProductModel model = JspFormUtil.toModel(ProductModel.class, request);
			String name = request.getParameter("name");
			List<ProductModel> productList = productService.searchByName(name);
			if (model.getSortBy() != null && model.getSortBy().equals("product_name")
					&& model.getSortName().equals("asc")) {
				productList.sort(Comparator.comparing(ProductModel::getName));
			} else if (model.getSortBy() != null && model.getSortBy().equals("product_name")
					&& model.getSortName().equals("desc")) {
				productList.sort(Comparator.comparing(ProductModel::getName).reversed());
			} else if (model.getSortBy() != null && model.getSortBy().equals("price")
					&& model.getSortName().equals("asc")) {
				productList.sort(Comparator.comparing(ProductModel::getPrice));
			} else if (model.getSortBy() != null && model.getSortBy().equals("price")
					&& model.getSortName().equals("desc")) {
				productList.sort(Comparator.comparing(ProductModel::getPrice).reversed());
			}
			model.setTotalPage(0);
			model.setResultList(productList);
			request.setAttribute("MODEL", model);
			request.setAttribute("searchData", name);
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
		//		home
		else if (request.getRequestURI().startsWith(request.getContextPath()+"/home")) {
			ProductModel model = JspFormUtil.toModel(ProductModel.class, request);
			Pageble pageble = new PageRequest(model.getPageMaxItem(), model.getCurrentPage(),
					new Sorter(model.getSortBy(), model.getSortName()));
			List<ProductModel> productList = productService.findAll(pageble);
			if (model.getSortBy() != null && model.getSortBy().equals("product_name")
					&& model.getSortName().equals("asc")) {
				productList.sort(Comparator.comparing(ProductModel::getName));
			} else if (model.getSortBy() != null && model.getSortBy().equals("product_name")
					&& model.getSortName().equals("desc")) {
				productList.sort(Comparator.comparing(ProductModel::getName).reversed());
			} else if (model.getSortBy() != null && model.getSortBy().equals("price")
					&& model.getSortName().equals("asc")) {
				productList.sort(Comparator.comparing(ProductModel::getPrice));
			} else if (model.getSortBy() != null && model.getSortBy().equals("price")
					&& model.getSortName().equals("desc")) {
				productList.sort(Comparator.comparing(ProductModel::getPrice).reversed());
			}
			model.setResultList(productList);
			model.setTotalItem(productService.getTotalItem());
			model.setTotalPage((int) Math.ceil((float)model.getTotalItem()/model.getPageMaxItem()));
			request.setAttribute("MODEL", model);
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
