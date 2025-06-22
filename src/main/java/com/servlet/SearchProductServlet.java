package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.Product;
import com.dao.ProductDAO; 


@WebServlet("/SearchProduct")
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String productId = request.getParameter("productId");
		String keyword = request.getParameter("keyword");
		
		ProductDAO dao = new ProductDAO();
		List<Product> resultList = new ArrayList<>();
		
		if (productId != null && !productId.trim().isEmpty()) {
			resultList = dao.searchById(productId.trim());
		} else if (keyword != null && !keyword.trim().isEmpty()) {
			resultList = dao.searchByKeyword(keyword.trim());
		} else {
			resultList = dao.getAllProducts(); //
		}
		
		request.setAttribute("productList", resultList);
		request.getRequestDispatcher("/user/productList.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}