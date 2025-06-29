package com.adminservlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.CartItem;
import com.dao.OrderDAO;

/**
 * Servlet implementation class OrderDetailUpdate
 */
@WebServlet("/OrderDetailUpdate")
public class OrderDetailUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		String memo = request.getParameter("memo");
		
		OrderDAO dao = new OrderDAO();
		boolean isUpdateMemo = dao.updateMemo(userId, memo);
		if(isUpdateMemo) {
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

			// Tìm kiếm theo tên khách hàng
			String keyword = request.getParameter("userName");

			List<CartItem> orders;
			int totalOrders = 0;

			if (keyword != null && !keyword.trim().isEmpty()) {
				orders = dao.searchOrdersByCustomerName(keyword, offset, limit);
				totalOrders = dao.countOrdersByCustomerName(keyword);
			} else if (request.getParameter("userId") != null && !request.getParameter("userId").isEmpty()) {
				orders = dao.getAllOrderIdByUserId(request.getParameter("userId"));
				totalOrders = orders.size();
			} else {
				orders = dao.getAllOrder(offset, limit);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
