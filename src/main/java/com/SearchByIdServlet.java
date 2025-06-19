package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/searchById")
public class SearchByIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String proId = request.getParameter("proId");
        ProductDAO dao = new ProductDAO();
        Product product = dao.getProductById(proId);
        List<Product> result = new ArrayList<>();
        if (product != null) result.add(product);

        request.setAttribute("productList", result);
        request.setAttribute("currentPage", 1);
        request.setAttribute("totalPages", 1);
        request.getRequestDispatcher("/jsp/productList.jsp").forward(request, response);
    }
}