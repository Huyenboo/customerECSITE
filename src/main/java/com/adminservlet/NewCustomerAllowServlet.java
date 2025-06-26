package com.adminservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.User;
import com.dao.UserDAO;

@WebServlet("/newCustomerAllow")
public class NewCustomerAllowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // TODO Auto-generated method stub
		List<User> listUserPending = new ArrayList<User>();
		UserDAO dao = new UserDAO();
		listUserPending = dao.getUserPending();

		for (User user : listUserPending) {
			System.out.println(user.toString());
		}
		request.setAttribute("listUserPending", listUserPending);
		request.getRequestDispatcher("/admin/newCustomerAllow.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		int userId = Integer.parseInt(request.getParameter("userId"));

		UserDAO userDAO = new UserDAO();
		boolean updateStatusUser = userDAO.userUpdateStatus(userId);
		request.getRequestDispatcher("/admin/newCustomerAllow.jsp").forward(request, response);

	}

}
