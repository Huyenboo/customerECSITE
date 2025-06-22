//package com.servlet;
//
//import java.io.IOException;
//import java.util.List;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import com.bean.CartItem;
//
//@WebServlet("/updateCartItem")
//public class UpdateCartItemServlet extends HttpServlet {
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		String productId = request.getParameter("productId");
////		String oderId = request.getParameter("order_id");
////		String orderArrivedDay = request.getParameter("order_arrived_day");
//		
//		int newQuantity = Integer.parseInt(request.getParameter("quantity"));
//		
//		
//		
////		System.out.println(productId);
////		System.out.println(oderId );
//		System.out.println(newQuantity);
//		
//		
//		HttpSession session = request.getSession();
//		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
//		System.out.println("updatecartitem");
//		if (cart != null) {
//			for (CartItem item : cart) {
//				if (item.getProduct().getProId().equals(productId)) {
//					item.setQuantity(newQuantity);
//					break;
//				}
//			}
//			session.setAttribute("cart", cart);
//		}
//
//		response.sendRedirect(request.getContextPath() + "/user/cart.jsp");
//	}
//}

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

@WebServlet("/updateCartItem")
public class UpdateCartItemServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String productId = request.getParameter("productId");
		String quantityStr = request.getParameter("quantity");
		

		if (productId == null || quantityStr == null || quantityStr.isEmpty()) {
			request.setAttribute("errMsg", "商品IDまたは数量が指定されていません。");
			request.getRequestDispatcher("/user/cart.jsp").forward(request, response);
			return;
		}

		int newQuantity = 0;
		try {
			newQuantity = Integer.parseInt(quantityStr);
		} catch (NumberFormatException e) {
			request.setAttribute("errMsg", "数量は数値である必要があります。");
			request.getRequestDispatcher("/user/cart.jsp").forward(request, response);
			return;
		}

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

		request.getRequestDispatcher("/user/cart.jsp").forward(request, response);
	}
}

