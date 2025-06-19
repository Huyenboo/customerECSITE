package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String productId = request.getParameter("productId");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		// Lấy sản phẩm từ DB hoặc danh sách tạm (DAO)
		ProductDAO dao = new ProductDAO();
		Product product = dao.getProductById(productId);
		
		// Lấy giỏ hàng từ session
		HttpSession session = request.getSession();
		List<CartItem> attribute = (List<CartItem>) session.getAttribute("cartList");
		List<CartItem> cartList = attribute;
		if (cartList == null) {
			cartList = new ArrayList<>();
		}
		
		// Kiểm tra nếu sản phẩm đã có thì tăng số lượng
		boolean found = false;
		for (CartItem item : cartList) {
			if (item.getProduct().getProId().equals(productId)) {
				item.setQuantity(item.getQuantity() + quantity);
				found = true;
				break;
			}
		}
		
		if (!found) {
			CartItem newItem = new CartItem(product, quantity);
			cartList.add(newItem);
		}
		
		session.setAttribute("cartList", cartList);
		
		// Quay lại trang gốc
		response.sendRedirect("/jsp/productList.jsp");
	}
}