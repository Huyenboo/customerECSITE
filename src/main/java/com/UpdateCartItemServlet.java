package com;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateCartItem")
public class UpdateCartItemServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String productId = request.getParameter("productId");
		int newQuantity = Integer.parseInt(request.getParameter("quantity"));
			
		HttpSession session = request.getSession();
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		
		if (cart != null) {
			for (CartItem item : cart) {
				if (item.getProduct().getProId().equals(productId)) {
					item.setQuantity(newQuantity);
					break;
				}
			}
			session.setAttribute("cart", cart);
		}
		
		response.sendRedirect("cart");
	}
}
