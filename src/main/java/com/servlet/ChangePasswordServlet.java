package com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bean.User;
import com.dao.UserDAO;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String currentPassword = req.getParameter("currentPassword");
		String newPassword = req.getParameter("newPassword");
		String confirmPassword = req.getParameter("confirmPassword");
		UserDAO dao = new UserDAO();
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		
		String hashCurrPass = dao.hashPassword(currentPassword);
		if (loginUser == null) {
			resp.sendRedirect(req.getContextPath() + "/user/login.jsp");
			return;
		}
		
		String phone = loginUser.getManagerPhoneNum();
		
		if (!hashCurrPass.equals(loginUser.getPassword())) {
			req.setAttribute("error", "現在のパスワードが正しくありません。");
			req.getRequestDispatcher("/user/passChange.jsp").forward(req, resp);
			return;
		}
		
		if (!newPassword.equals(confirmPassword)) {
			req.setAttribute("error", "新しいパスワードと確認パスワードが一致しません。");
			req.getRequestDispatcher("/jsp/passChange.jsp").forward(req, resp);
			return;
		}
		
		if (dao.updatePassword(phone, newPassword)) {
			session.setAttribute("message", "パスワードが正常に変更されました。");
			resp.sendRedirect(req.getContextPath() + "/user/customer.jsp");
		} else {
			req.setAttribute("error", "パスワードの更新に失敗しました。");
			req.getRequestDispatcher("/user/passChange.jsp").forward(req, resp);
		}
	}
}