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

	private static final int PAGE_SIZE = 5;  // số dòng mỗi trang

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		AdminUserBean user = (session != null) ? (AdminUserBean) session.getAttribute("loginUser") : null;
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/salesTop.jsp");
			return;
		}

		String keyword = request.getParameter("keyword");
		if (keyword == null) keyword = "";

		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
			if (page <= 0) page = 1;
		} catch (Exception e) {
			page = 1;
		}

		UserDAO dao = new UserDAO();
		int totalRecords = dao.countSearch(keyword);
		int totalPages = (int) Math.ceil(totalRecords * 1.0 / PAGE_SIZE);

		int offset = (page - 1) * PAGE_SIZE;

		List<User> customers = dao.searchByCompanyName(keyword, offset, PAGE_SIZE);

		request.setAttribute("customerList", customers);
		request.setAttribute("keyword", keyword);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);

		request.getRequestDispatcher("/admin/HumanManagement.jsp").forward(request, response);
	}

}
