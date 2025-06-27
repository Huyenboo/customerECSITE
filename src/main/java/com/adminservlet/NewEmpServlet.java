package com.adminservlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.adminbean.AdminUserBean;
import com.admindao.EmpDAO;

@WebServlet("/newEmpServlet")
public class NewEmpServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String empPosition = request.getParameter("empPosition");
		String pass = request.getParameter("pass");
		String roleIdStr = request.getParameter("role_id");
		int roleId = 0;
		if (roleIdStr != null && !roleIdStr.isEmpty()) {
			roleId = Integer.parseInt(roleIdStr);
		}
		AdminUserBean emp = new AdminUserBean();
		emp.setEmp_id(empId);
		emp.setEmp_name(empName);
		emp.setRole_id(roleId);
		emp.setEmp_position(empPosition);
		emp.setPass(pass);

		EmpDAO dao = new EmpDAO();
		boolean success = dao.insertEmployee(emp);

		if (success) {
			response.sendRedirect(request.getContextPath() + "/empListServlet");
		} else {
			request.setAttribute("message", "登録に失敗しました。");
			request.getRequestDispatcher("/admin/newEmp.jsp").forward(request, response);
		}
	}
}
