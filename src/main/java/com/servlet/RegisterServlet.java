package com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.User;
import com.dao.UserDAO;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		User user = new User();
		user.setCompanyId("company_id");
		user.setCompanyName(req.getParameter("company_name"));
		user.setCompanyAddress(req.getParameter("company_address"));
		user.setPresidentPhoneNum(req.getParameter("president_phone_num"));
		user.setManagerName(req.getParameter("manager_name"));
		user.setManagerEmail(req.getParameter("manager_email"));
		user.setManagerPhoneNum(req.getParameter("manager_phone_num"));
		user.setPassword(req.getParameter("password"));
		
		try {
			UserDAO dao = new UserDAO();
			boolean success = dao.registerUser(user);
			if (success) {
				req.getRequestDispatcher("/user/registerSuccess.jsp").forward(req, res);
			} else {
				res.getWriter().println("登録に失敗しました。もう一度お試しください。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.getWriter().println("サーバーエラーが発生しました。");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		res.getWriter().println("GETメソッドはサポートされていません。POSTでアクセスしてください。");
    }
}
