package com;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<CartItem> cartList = (List<CartItem>) session.getAttribute("cart");
		
		// Nếu chưa có giỏ hàng, tạo mới
		if (cartList == null) {
			cartList = new java.util.ArrayList<>();
			session.setAttribute("cart", cartList);
		}
		
		request.setAttribute("cartList", cartList);
		request.getRequestDispatcher("/jsp/cart.jsp").forward(request, response);
	}
}
