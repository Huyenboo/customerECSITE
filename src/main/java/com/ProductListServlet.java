package com;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/productList")
public class ProductListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO dao = new ProductDAO();
		List<Product> list = dao.getAllProducts();
		
		HttpSession session = request.getSession();
		session.setAttribute("productList", list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/productList.jsp");
		dispatcher.forward(request, response);
	}
}
