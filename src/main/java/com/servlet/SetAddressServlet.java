package com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class SetAddressServlet
 */
@WebServlet("/setAddress")
public class SetAddressServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String address = request.getParameter("address");
        HttpSession session = request.getSession();

        if (address != null && !address.isEmpty()) {
            session.setAttribute("address", address);
        }

        response.sendRedirect(request.getContextPath() + "/user/orderConfirm.jsp");
    }
}
