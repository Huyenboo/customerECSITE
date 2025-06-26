package com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.Product;
import com.dao.ProductDAO;

@WebServlet("/productList")
public class ProductListServlet extends HttpServlet {
	
	private static final int PAGE_SIZE = 10;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		ProductDAO dao = new ProductDAO();
		
		// Tổng số sản phẩm　全て商品数
		int totalProducts = dao.getProductCount();

		// Tổng số trang　全てページ数の合計
		int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);

		// Lấy trang hiện tại từ request, mặc định là 1。requestから今のページ番号を取得
		int page = 1;
		String pageParam = request.getParameter("page");
		if (pageParam != null) {
			try {
				page = Integer.parseInt(pageParam);
				if (page < 1) page = 1;
				if (page > totalPages) page = totalPages;  // Không cho vượt tổng số trang
			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		// Tính offset
		int offset = (page - 1) * PAGE_SIZE;

		// Lấy danh sách sản phẩm theo trang　ページごとListをget
		List<Product> productList = dao.getProductsByPage(offset, PAGE_SIZE);

		// Gửi dữ liệu sang JSPに渡す
		request.setAttribute("productList", productList);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);

		// Điều hướng
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user/productList.jsp");
		dispatcher.forward(request, response);
	}
}
