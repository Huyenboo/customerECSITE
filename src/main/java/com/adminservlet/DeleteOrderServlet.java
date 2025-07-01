package com.adminservlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.dao.OrderDAO;

@WebServlet("/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginUser") == null) {
			response.sendRedirect(request.getContextPath() + "/user/login.jsp");
			return;
		}

		String id = request.getParameter("id");
		if (id != null) {
			try {
				new OrderDAO().deleteById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect(request.getContextPath() + "/OrderManagementServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
