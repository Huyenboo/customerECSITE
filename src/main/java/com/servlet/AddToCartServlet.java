package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bean.CartItem;
import com.bean.Product;
import com.dao.ProductDAO;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String productId = request.getParameter("productId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String deliveryDate = request.getParameter("deliveryDate");
       

        // Lấy sản phẩm từ DB
        ProductDAO dao = new ProductDAO();
        Product product = dao.getProductById(productId);
        if (product == null) {
            response.sendRedirect("productList");
            return;
        }

        // Lấy giỏ hàng từ session
        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Kiểm tra xem sản phẩm đã có trong giỏ chưa
        boolean found = false;
        for (CartItem item : cart) {
            if (item.getProduct().getProId().equals(productId)) {
                // Cập nhật số lượng và ngày giao hàng mới
                item.setQuantity(item.getQuantity() + quantity);
                item.setDeliveryDate(deliveryDate);
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(new CartItem(product, quantity, deliveryDate));
        }

        session.setAttribute("cart", cart);

        // Quay lại danh sách sản phẩm
        response.sendRedirect("productList");
    }
}
