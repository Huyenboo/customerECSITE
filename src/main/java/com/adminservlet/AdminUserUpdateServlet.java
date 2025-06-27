package com.adminservlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.adminbean.AdminUserBean;
import com.admindao.EmpDAO;

@WebServlet("/UpdateEmployeeServlet")
public class AdminUserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse rep) {
		getServletContext().log("ok");
		String userId = req.getParameter("userId");
		EmpDAO empDAO = new EmpDAO();
		AdminUserBean employee = empDAO.getEmplById(userId);
		req.setAttribute("employee", employee);
		try {
			req.getRequestDispatcher("/admin/newEmp.jsp").forward(req, rep);
		} catch (ServletException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			getServletContext().log("loi");
		}
		;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {
			// Kiểm tra đăng nhập
			AdminUserBean loginUser = (AdminUserBean) request.getSession().getAttribute("loginUser");
			if (loginUser == null) {
				response.sendRedirect(request.getContextPath() + "/ReturnAdminLoginServlet");
				return;
			}

			// Nhận dữ liệu từ form
			String emp_id = request.getParameter("emp_id");
			String emp_name = request.getParameter("emp_name");
			String emp_position = request.getParameter("emp_position");
			String pass = request.getParameter("pass");
			int role_id = Integer.parseInt(request.getParameter("role_id"));

			// Gán vào Bean
			AdminUserBean user = new AdminUserBean();
			user.setEmp_id(emp_id);
			user.setEmp_name(emp_name);
			user.setEmp_position(emp_position);
			user.setPass(pass);
			user.setRole_id(role_id);

			// Cập nhật
			EmpDAO dao = new EmpDAO();
			boolean success = dao.updateUser(user);

			if (success) {
				response.sendRedirect(request.getContextPath() + "/AdminUserListServlet");
			} else {
				request.setAttribute("errorMsg", "更新に失敗しました。");
				request.setAttribute("userEdit", user);
				request.getRequestDispatcher("/admin/adminUserEdit.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "システムエラーが発生しました。");
			request.getRequestDispatcher("/admin/adminUserEdit.jsp").forward(request, response);
		}
	}
}
