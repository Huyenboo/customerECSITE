package com.adminservlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.Product;
import com.dao.ProductDAO;

@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		if (id == null || id.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/ProductManagementServlet");
			return;
		}

		Product p = new ProductDAO().getProductById(id);
		if (p == null) {
			response.sendRedirect(request.getContextPath() + "/ProductManagementServlet");
			return;
		}

		request.setAttribute("product", p);
		request.getRequestDispatcher("/admin/EditProduct.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String idStr = request.getParameter("id");
		String proName = request.getParameter("proName");
		String priceStr = request.getParameter("proPrice");
		String proUnitNumStr = request.getParameter("proUnitNum");
		String proMemo = request.getParameter("proMemo");
		String proId = request.getParameter("proId");
		Product p = new Product();
		try {
			// Đưa dữ liệu tạm vào bean để nếu có lỗi vẫn hiển thị lại
			p.setId(Integer.parseInt(idStr));
			p.setProName(proName);
			p.setProMemo(proMemo);
			
			
			p.setProId(proId);
			// Kiểm tra dữ liệu bắt buộc
			if (proName == null || proName.isEmpty() ||
			    priceStr == null || priceStr.trim().isEmpty() ||
			    proUnitNumStr == null || proUnitNumStr.trim().isEmpty()) {
				Product getProductError = new Product();
				getProductError= new ProductDAO().getProductById(proId);

				request.setAttribute("product", getProductError);
				
				request.setAttribute("errorMsg", "全ての必須項目を正しく入力してください。");
				request.getRequestDispatcher("/admin/EditProduct.jsp").forward(request, response);
				return;
			}

			// Chuyển đổi dữ liệu với bắt lỗi riêng từng trường
			double proPrice = 0;
			int proUnitNum = 0;

			try {
				proPrice = Double.parseDouble(priceStr.trim());
			} catch (NumberFormatException e) {
				request.setAttribute("product", p);
				request.setAttribute("errorMsg", "価格は正しい数値を入力してください。");
				request.getRequestDispatcher("/admin/EditProduct.jsp").forward(request, response);
				return;
			}

			try {
				proUnitNum = Integer.parseInt(proUnitNumStr.trim());
			} catch (NumberFormatException e) {
				request.setAttribute("product", p);
				request.setAttribute("errorMsg", "数量は正しい整数を入力してください。");
				request.getRequestDispatcher("/admin/EditProduct.jsp").forward(request, response);
				return;
			}

			// Cập nhật dữ liệu đầy đủ vào bean
			p.setProPrice(proPrice);
			p.setProUnitNum(proUnitNum);

			// Gọi DAO để update
			boolean ok = new ProductDAO().update(p);

			if (ok) {
				request.getSession().setAttribute("success", "変更成功しました。");
				response.sendRedirect(request.getContextPath() + "/ProductManagementServlet");
			} else {
				request.setAttribute("product", p);
				request.setAttribute("errorMsg", "更新に失敗しました。もう一度試してください。");
				request.getRequestDispatcher("/admin/EditProduct.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMsg", "システムエラーが発生しました。");
			response.sendRedirect(request.getContextPath() + "/ProductManagementServlet");
		}
	}
}
