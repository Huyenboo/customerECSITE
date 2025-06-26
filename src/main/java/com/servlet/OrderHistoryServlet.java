
package com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.CartItem;
import com.dao.OrderDAO;

@WebServlet("/OrderHistory")
public class OrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");

		OrderDAO dao = new OrderDAO();
		List<CartItem> listOrderDb = dao.getAllOrderIdByUserId(userId);

		if (listOrderDb != null || !listOrderDb.isEmpty()) {
			System.out.println("co list");
			//			HttpSession session = request.getSession();
			request.setAttribute("listOrder", listOrderDb);
			request.getRequestDispatcher("/user/orderHistory.jsp").forward(request, response);

		} else {
			response.sendRedirect(request.getContextPath() + "/user/orderHistory.jsp");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Giả sử userId lấy từ session (thường sau khi đăng nhập đã lưu vào session)
		String userId = String.valueOf(request.getSession().getAttribute("userId"));

		if (userId == null) {
			// Nếu chưa đăng nhập, điều hướng về trang đăng nhập
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		OrderDAO dao = new OrderDAO();
		List<CartItem> listOrderDb = dao.getAllOrderIdByUserId(userId);

		if (listOrderDb != null && !listOrderDb.isEmpty()) {
			request.setAttribute("listOrder", listOrderDb);
		}

		request.getRequestDispatcher("/user/orderHistory.jsp").forward(request, response);
	}
}
