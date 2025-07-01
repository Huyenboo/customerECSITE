package com.adminservlet;

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

@WebServlet("/AdminNewProductServlet")
public class AdminNewProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// Chuyển đến màn hình đăng ký sản phẩm
		req.getRequestDispatcher("/admin/NewProductInsert.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		try {
			// Lấy dữ liệu từ form
			String proId = req.getParameter("proId");
			String proName = req.getParameter("proName");
			String priceStr = req.getParameter("proPrice");
			String proUnitNum = req.getParameter("proUnitNum");
			String proMemo = req.getParameter("proMemo");

			// Kiểm tra dữ liệu đầu vào
			if (proId == null || proId.isEmpty() ||
					proName == null || proName.isEmpty() ||
					priceStr == null || priceStr.isEmpty()) {

				req.setAttribute("error", "全ての必須項目を入力してください。");
				req.getRequestDispatcher("/admin/NewProductInsert.jsp").forward(req, res);
				return;
			}

			double proPrice = Double.parseDouble(priceStr.trim());

			// Set vào Bean
			Product p = new Product();
			p.setProId(proId);
			p.setProName(proName);
			p.setProPrice(proPrice);
			p.setProMemo(proMemo);

			try {
				if (proUnitNum != null && !proUnitNum.isEmpty()) {
					p.setProUnitNum(Integer.parseInt(proUnitNum));
				}
			} catch (NumberFormatException ignored) {
				// Nếu số lượng nhập sai, set về 0 hoặc bỏ qua
				p.setProUnitNum(0);
			}

			// Gọi DAO để lưu
			ProductDAO dao = new ProductDAO();
			List<Product> productList = new ArrayList<>(); 
			productList = dao.getListProductById(proId);
			if(productList.size() > 0) {
				req.setAttribute("error", "登録に失敗しました");
				req.getRequestDispatcher("/admin/NewProductInsert.jsp").forward(req, res);
				 return;
			}
			boolean success = dao.insertProduct(p);

			if (success) {
				req.setAttribute("success", "登録成功しました");
				req.getRequestDispatcher("/admin/NewProductInsert.jsp").forward(req, res);
			} else {
				req.setAttribute("error", "登録に失敗しました");
				req.getRequestDispatcher("/admin/NewProductInsert.jsp").forward(req, res);
			}
			

		} catch (NumberFormatException e) {
			req.setAttribute("error", "価格が不正です。数値を入力してください。");
			req.getRequestDispatcher("/admin/NewProductInsert.jsp").forward(req, res);

		} catch (Exception e) {
			e.printStackTrace(); // Log chi tiết lỗi
			req.setAttribute("error", "システムエラーが発生しました。");
			req.getRequestDispatcher("/admin/NewProductInsert.jsp").forward(req, res);
		}
	}
}
