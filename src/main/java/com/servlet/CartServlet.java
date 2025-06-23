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

@WebServlet("/showCart")
public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // ここでセッションからcartListを取得（保持されている）
        List<CartItem> cartList = (List<CartItem>) session.getAttribute("cart");
        request.setAttribute("cartList", cartList); // JSPへ渡す
        request.getRequestDispatcher("/user/cart.jsp").forward(request, response);
        
    }
}

