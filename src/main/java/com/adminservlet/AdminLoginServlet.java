package com.adminservlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.adminbean.AdminUser;
import com.admindao.AdminUserDAO;

@WebServlet("/adminLogin") 
public class AdminLoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Lấy dữ liệu từ form
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		// DAO を呼び出すloginチェック、
		AdminUserDAO dao = new AdminUserDAO();
		AdminUser user = dao.login(userId, password);
		
		if (user != null) {
			//sessionにuser 情報を保存
			HttpSession session = request.getSession();
			session.setAttribute("adminUser", user);
			
			// Điều hướng theo role
			
			int role = user.getRoleId();

			if ("role_admin".equals(role)) {
				response.sendRedirect(request.getContextPath() + "/jsp/companyList");
			} else if ("role_sales".equals(role)) {
				response.sendRedirect(request.getContextPath() + "/jsp/salesPage.jsp");
			} else if ("role_wholesale".equals(role)) {
				response.sendRedirect(request.getContextPath() + "/jsp/order.jsp");
			} else {
				response.sendRedirect(request.getContextPath() + "/error.jsp");
			}

		} else {
			// ログイン失敗 → login画面に戻す
			request.setAttribute("error", "ユーザーIDまたはパスワードが正しくありません。");
			request.getRequestDispatcher("/jsp/adminLogin.jsp").forward(request, response);
		}
	}
}
