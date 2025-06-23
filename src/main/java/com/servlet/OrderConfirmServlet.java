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

@WebServlet("/OrderConfirmServlet")
public class OrderConfirmServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String confirmCheck = request.getParameter("confirmCheck");
		if (confirmCheck == null) {
			request.setAttribute("errMsg", "注文確認にチェックを入れてください。");
			request.getRequestDispatcher("/user/orderConfirm.jsp").forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		String userAddress = (String) session.getAttribute("user_address");
		List<CartItem> cartList = (List<CartItem>) session.getAttribute("cart");
		
		
		if (userId == null || userName == null || cartList == null || cartList.isEmpty()) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		OrderDAO dao = new OrderDAO();
		boolean success = true;
		for (CartItem item : cartList) {
			if (!dao.insertOrder(userId, userName, item)) {
				success = false;
				break;
			}
		}
		
		//đặt hàng xong xóa giỏ hàng 
		if (success) {
			session.removeAttribute("cart");
			response.sendRedirect("/user/orderSuccess.jsp");
		} else {
  	     	request.setAttribute("errMsg", "注文登録に失敗しました。");
  	     	request.getRequestDispatcher("/user/orderConfirm.jsp").forward(request, response);
		}
	}
}
