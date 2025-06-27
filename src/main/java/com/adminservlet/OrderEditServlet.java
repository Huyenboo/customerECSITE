package com.adminservlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.CartItem;
import com.dao.OrderDAO;

@WebServlet("/OrderEditServlet")
public class OrderEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String orderIdParam = request.getParameter("orderId");

            if (orderIdParam == null || orderIdParam.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/OrderListServlet");
                return;
            }

            int orderId = Integer.parseInt(orderIdParam);

            OrderDAO dao = new OrderDAO();
            CartItem order = dao.getOrderById(orderId);

            if (order != null) {
                request.setAttribute("orderEdit", order);
                request.getRequestDispatcher("/admin/orderEdit.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/OrderManagementServlet");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/OrderManagementServlet");
        }
    }
}
