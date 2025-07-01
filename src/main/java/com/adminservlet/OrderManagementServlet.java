package com.adminservlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.dao.OrderDAO;
import com.dto.OrderListDTO;

@WebServlet("/OrderManagementServlet")
public class OrderManagementServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginUser") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/adminLogin.jsp");
			return;
		}

		OrderDAO dao = new OrderDAO();

		// Phân trang
		int page = 1;
		int limit = 10;
		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
				page = 1;
			}
		}
		int offset = (page - 1) * limit;

		String keyword = request.getParameter("userName");

		List<OrderListDTO> orders;
		int totalOrders = 0;

		if (keyword != null && !keyword.trim().isEmpty()) {
			orders = dao.searchOrdersByCustomerName(keyword, offset, limit);
			totalOrders = dao.countOrdersByCustomerName(keyword);
		} else {
			orders = dao.getAllOrderList(offset, limit);
			totalOrders = dao.getTotalOrderCount();
		}

		int totalPages = (int) Math.ceil(totalOrders / (double) limit);

		request.setAttribute("orderList", orders);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("keyword", keyword != null ? keyword : "");

		request.getRequestDispatcher("/admin/OrderManagement.jsp").forward(request, response);
	}
}
