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

	private static final long serialVersionUID = 1L; // シリアル番号 (バージョン管理用)

	// GETリクエスト処理
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータ「productId」を取得
		String productId = request.getParameter("productId");

		// リクエストパラメータ「keyword」を取得
		String keyword = request.getParameter("keyword");

		// ProductDAOインスタンス生成 
		ProductDAO dao = new ProductDAO();

		// 検索結果を格納するリストを準備
		List<Product> resultList = new ArrayList<>();

		// productIdが入力された場合、IDで検索
		if (productId != null && !productId.trim().isEmpty()) {
			resultList = dao.searchById(productId.trim());

			// keywordが入力された場合、キーワードで検索
		} else if (keyword != null && !keyword.trim().isEmpty()) {
			resultList = dao.searchByKeyword(keyword.trim());

			// どちらも入力されていない場合、全商品を取得
		} else {
			resultList = dao.getAllProducts();
		}

		// 検索結果リストをリクエストスコープに格納
		request.setAttribute("productList", resultList);

		// 結果をJSP (商品一覧画面)に転送
		request.getRequestDispatcher("/user/productList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response); //
	}
}
