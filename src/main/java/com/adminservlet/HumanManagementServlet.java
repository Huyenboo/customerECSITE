package com.adminservlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.adminbean.AdminUserBean;
import com.bean.User;
import com.dao.UserDAO;

@WebServlet("/HumanManagementServlet")
public class HumanManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HumanManagementServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		AdminUserBean user = (session != null) ? (AdminUserBean) session.getAttribute("loginUser") : null;
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/salesTop.jsp");
			return;
		}

		// 顧客情報取得（DAO）
		List<User> customers = new UserDAO().userList();
		request.setAttribute("customerList", customers);

		// JSP にフォワード
		request.getRequestDispatcher("/admin/HumanManagement.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
