package com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.Product;
import com.dao.ProductDAO;

@WebServlet("/newProductServlet")
public class NewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ProductDAO dao = new ProductDAO();
	    List<Product> productList = dao.getNewestProducts();

	    request.setAttribute("productList", productList);
	    request.getRequestDispatcher("/user/newProductList.jsp").forward(request, response);
	}
}

