package com;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		
		UserDAO dao = new UserDAO();
		User user = dao.loginUser(phone, password);
		
		if (user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", user);
			
			//if (管理者）
			if (user.getStatus() == 2) {
				resp.sendRedirect(req.getContextPath() + "/jsp/admin.jsp");
			} else {
				resp.sendRedirect(req.getContextPath() + "/jsp/customer.jsp");
			}
		} else {
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().println("<script>alert('ログイン失敗または未承認です。');history.back();</script>");
		}
	}
}
