package com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bean.CartItem;
import com.dao.OrderDAO;

@WebServlet("/OrderHistory")
public class OrderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		System.out.println(userId);
		OrderDAO dao = new OrderDAO();
		List<CartItem> listOrder = dao.getAllOrderIdByUserId(userId);
		if (listOrder != null) {
			HttpSession session = request.getSession();
			session.setAttribute("listOrder", listOrder);
			response.sendRedirect(request.getContextPath() + "/user/OrderHistory.jsp");
	
		} else {
			response.sendRedirect(request.getContextPath() + "/user/OrderHistory.jsp");
		}
	}
}
