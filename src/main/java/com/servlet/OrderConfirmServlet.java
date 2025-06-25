
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

@WebServlet("/orderConfirm")
public class OrderConfirmServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		List<CartItem> cartList = (List<CartItem>) session.getAttribute("cart");
		String address = (String) session.getAttribute("address");

		System.out.println("=== orderConfirm 呼び出し確認 ===");
		System.out.println("Cart Size: " + (cartList != null ? cartList.size() : 0));
		System.out.println("住所: " + address);

		// Kiểm tra giỏ hàng hợp lệ
		if (cartList == null || cartList.isEmpty()) {
			System.out.println("カートが空です。戻ります。");
			response.sendRedirect(request.getContextPath() + "/user/cart.jsp");
			return;
		}

		// Kiểm tra tất cả sản phẩm đã có ngày giao hàng
		for (CartItem item : cartList) {
			if (item.getOrderArrivedDay() == null) {
				System.out.println("納期未入力の商品があります。戻ります。");
				response.sendRedirect(request.getContextPath() + "/user/cart.jsp");
				return;
			}
		}

		// Kiểm tra địa chỉ
		if (address == null || address.isEmpty()) {
			System.out.println("住所情報が不足しています。戻ります。");
			response.sendRedirect(request.getContextPath() + "/user/cart.jsp");
			return;
		}

		// Đủ điều kiện, chuyển đến trang xác nhận
		request.setAttribute("address", address);
		request.getRequestDispatcher("/user/orderConfirm.jsp").forward(request, response);
	}
}
