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

		// Nhận dữ liệu từ form
		String companyId = "company_id"; // Tạm thời, nếu có auto-gen hoặc khác thì chỉnh sau
		String companyName = req.getParameter("company_name");
		String companyAddress = req.getParameter("company_address");
		String presidentPhoneNum = req.getParameter("president_phone_num");
		String managerName = req.getParameter("manager_name");
		String managerEmail = req.getParameter("manager_email");
		String managerPhoneNum = req.getParameter("manager_phone_num");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirm_password");

		// Kiểm tra dữ liệu hợp lệ
		if (companyName == null || companyName.isEmpty() ||
				companyAddress == null || companyAddress.isEmpty() ||
				presidentPhoneNum == null || presidentPhoneNum.isEmpty() ||
				managerName == null || managerName.isEmpty() ||
				managerEmail == null || managerEmail.isEmpty() ||
				managerPhoneNum == null || managerPhoneNum.isEmpty() ||
				password == null || password.isEmpty() ||
				confirmPassword == null || confirmPassword.isEmpty() ||
				!password.equals(confirmPassword)) {

			req.setAttribute("errMsg", "入力エラーです。ご確認ください。");
			req.getRequestDispatcher("/user/register.jsp").forward(req, res);
			return;
		}

		// Tạo đối tượng User
		User user = new User();
		user.setCompanyId(companyId);
		user.setCompanyName(companyName);
		user.setCompanyAddress(companyAddress);
		user.setPresidentPhoneNum(presidentPhoneNum);
		user.setManagerName(managerName);
		user.setManagerEmail(managerEmail);
		user.setManagerPhoneNum(managerPhoneNum);
		user.setPassword(password);

		// Tiến hành đăng ký
		try {
			UserDAO dao = new UserDAO();
			boolean success = dao.registerUser(user);
			if (success) {
				req.getRequestDispatcher("/user/registerSuccess.jsp").forward(req, res);
			} else {
				req.setAttribute("errMsg", "登録に失敗しました。もう一度お試しください。");
				req.getRequestDispatcher("/user/register.jsp").forward(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "サーバーエラーが発生しました。");
			req.getRequestDispatcher("/user/register.jsp").forward(req, res);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		res.getWriter().println("GETメソッドはサポートされていません。POSTでアクセスしてください。");
	}
}
