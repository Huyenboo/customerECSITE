package com;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteCartItem")
public class DeleteCartItemServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String productId = request.getParameter("productId");
		
		HttpSession session = request.getSession();
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
			
		if (cart != null) {
			cart.removeIf(item -> item.getProduct().getProId().equals(productId));
			session.setAttribute("cart", cart);
		}
		
		response.sendRedirect("cart");
	}
}
