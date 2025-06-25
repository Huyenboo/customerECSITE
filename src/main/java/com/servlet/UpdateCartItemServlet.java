package com.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bean.CartItem;

@WebServlet("/updateCartItem")
public class UpdateCartItemServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String productId = request.getParameter("productId");
		String quantityStr = request.getParameter("quantity");
		String orderArrivedDayStr = request.getParameter("orderArrivedDay");

		if (productId == null || quantityStr == null || quantityStr.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/user/cart.jsp");
			return;
		}

		int newQuantity = 0;
		try {
			newQuantity = Integer.parseInt(quantityStr);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/user/cart.jsp");
			return;
		}

		@SuppressWarnings("unchecked")
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

		if (cart != null) {
			for (CartItem item : cart) {
				if (item.getProduct().getProId().equals(productId)) {
					item.setQuantity(newQuantity);

					// Xử lý ngày giao hàng
					if (orderArrivedDayStr != null && !orderArrivedDayStr.isEmpty()) {
						try {
							Date orderArrivedDay = Date.valueOf(orderArrivedDayStr);
							item.setOrderArrivedDay(orderArrivedDay);
						} catch (IllegalArgumentException e) {
							System.out.println("⚠️ 日付の形式が不正です: " + orderArrivedDayStr);
						}
					}
					break;
				}
			}
			session.setAttribute("cart", cart);
		}

		response.sendRedirect(request.getContextPath() + "/user/cart.jsp");
	}
}
