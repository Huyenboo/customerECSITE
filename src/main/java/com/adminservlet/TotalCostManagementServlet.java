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

@WebServlet("/TotalCostManagementServlet")
public class TotalCostManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TotalCostManagementServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		AdminUserBean user = (session != null) ? (AdminUserBean) session.getAttribute("loginUser") : null;
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/adminLogin.jsp");
			return;
		}

		// 日付範囲や絞込み要件は後で追加可能
		List<TotalCost> totals = new TotalCostDAO().getAllTotals();

		request.setAttribute("totalList", totals);
		request.getRequestDispatcher("/jsp/TotalCostManagement.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
