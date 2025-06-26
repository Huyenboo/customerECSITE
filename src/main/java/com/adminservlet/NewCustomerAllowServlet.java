package com.adminservlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.User;
import com.dao.UserDAO;

@WebServlet("/newCustomerAllow")
public class NewCustomerAllowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDAO dao = new UserDAO();
		List<User> listUserPending = dao.getUserPending();

		request.setAttribute("listUserPending", listUserPending);
		request.getRequestDispatcher("/admin/newCustomerAllow.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		String action = request.getParameter("action");  // "accept" hoặc "reject"

		UserDAO dao = new UserDAO();
		boolean success = false;

		if ("accept".equals(action)) {
			success = dao.userUpdateStatus(userId, "accept");
		} else if ("reject".equals(action)) {
			success = dao.userUpdateStatus(userId, "reject");
		}

		if (!success) {
			request.setAttribute("error", "ステータス更新に失敗しました。");
		}

		// Sau khi cập nhật, load lại danh sách
		List<User> listUserPending = dao.getUserPending();
		request.setAttribute("listUserPending", listUserPending);
		request.getRequestDispatcher("/admin/newCustomerAllow.jsp").forward(request, response);
	}
}
