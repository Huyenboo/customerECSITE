package com.adminservlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.SalesSummary;
import com.dao.OrderDAO;

@WebServlet("/salesSummary")
public class SalesSummaryServlet extends HttpServlet {

	private static final int ITEMS_PER_PAGE = 10;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String month = request.getParameter("month");
		if (month == null || month.isEmpty()) {
			month = java.time.LocalDate.now().withDayOfMonth(1).toString().substring(0, 7);
		}

		System.out.println("検索対象月: " + month);  // Debug giá trị month

		int page = 1;
		String pageParam = request.getParameter("page");
		if (pageParam != null && pageParam.matches("\\d+")) {
			page = Integer.parseInt(pageParam);
		}

		int offset = (page - 1) * ITEMS_PER_PAGE;

		OrderDAO dao = new OrderDAO();
		List<SalesSummary> list = dao.getSalesSummaryByMonth(month, offset, ITEMS_PER_PAGE);
		int totalCount = dao.countSalesSummaryByMonth(month);

		int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);

		int totalRevenue = 0;
		if (!list.isEmpty()) {
			totalRevenue = list.get(0).getTotalRevenue();
		}

		System.out.println("該当データ件数: " + totalCount);
		System.out.println("合計売上: " + totalRevenue);

		request.setAttribute("salesList", list);
		request.setAttribute("selectedMonth", month);
		request.setAttribute("totalRevenue", totalRevenue);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPage", totalPage);

		request.getRequestDispatcher("/admin/salesSummary.jsp").forward(request, response);
	}
}
