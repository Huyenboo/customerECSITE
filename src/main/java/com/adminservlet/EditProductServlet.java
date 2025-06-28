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
		String proUnit = request.getParameter("proUnit");
		String proUnitNumStr = request.getParameter("proUnitNum");
		String proMemo = request.getParameter("proMemo");

		// Kiểm tra dữ liệu bắt buộc
		if (proName == null || proName.isEmpty() || proUnit == null || proUnit.isEmpty() || proUnitNumStr == null
				|| proUnitNumStr.isEmpty()) {

			Product p = new Product();
			p.setId(Integer.parseInt(idStr));
			p.setProName(proName);
			p.setProUnit(proUnit);
			p.setProMemo(proMemo);

			request.setAttribute("product", p);
			request.setAttribute("errorMsg", "入力エラーです。全項目を正しく入力してください。");
			request.getRequestDispatcher("/admin/EditProduct.jsp").forward(request, response);
			return;
		}

		try {
			int id = Integer.parseInt(idStr);
			int proUnitNum = Integer.parseInt(proUnitNumStr);

			Product p = new Product();
			p.setId(id);
			p.setProName(proName);
			p.setProUnit(proUnit);
			p.setProUnitNum(proUnitNum);
			p.setProMemo(proMemo);

			boolean ok = new ProductDAO().update(p);

			if (ok) {
				// Cập nhật thành công
				request.setAttribute("message", "登録が完了しました。");
				response.sendRedirect(request.getContextPath() + "/ProductManagementServlet");
			} else {
				// Lỗi cập nhật
				request.setAttribute("product", p);
				request.setAttribute("errorMsg", "更新に失敗しました。");
				request.getRequestDispatcher("/admin/EditProduct.jsp").forward(request, response);
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			Product p = new Product();
			p.setId(Integer.parseInt(idStr));
			p.setProName(proName);
			p.setProUnit(proUnit);
			p.setProMemo(proMemo);

			request.setAttribute("product", p);
			request.setAttribute("errorMsg", "数値の形式が正しくありません。");
			request.getRequestDispatcher("/admin/EditProduct.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/ProductManagementServlet");
		}
	}
}
