package com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bean.Product;
import com.dao.ProductDAO;

@WebServlet("/productList")
public class ProductListServlet extends HttpServlet {
	private static final int PAGE_SIZE = 10;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		ProductDAO dao = new ProductDAO();
		List<Product> list = dao.getAllProducts();
		
		HttpSession session = request.getSession();
		request.setAttribute("productList", list);
		
		 //Lấy số trang hiện tại (mặc định là 1)　
		int page = 1;
		String pageParam = request.getParameter("page");
		if (pageParam != null) {
			try {
				page = Integer.parseInt(pageParam);
				if (page < 1) page = 1;
			} catch (NumberFormatException e) {
				page = 1;
			}
		}
		
		// Tính offset
		int offset = (page - 1) * PAGE_SIZE;
		
		// Bước 3: Lấy danh sách sản phẩm theo trang
		
		List<Product> productList = dao.getProductsByPage(offset, PAGE_SIZE);
		int totalProducts = dao.getProductCount();
		int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);
		
		// Bước 4: Gửi dữ liệu sang JSP
		request.setAttribute("productList", productList);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);
		
		// Điều hướng
		RequestDispatcher dispatcher1 = request.getRequestDispatcher("/user/productList.jsp");
		dispatcher1.forward(request, response);
	}
}