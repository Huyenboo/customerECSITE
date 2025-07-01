//package com.adminservlet;
//
//import java.io.IOException;
//import java.util.List;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import com.adminbean.AdminUserBean;
//import com.admindao.EmpDAO;
//
//@WebServlet("/empListServlet")
//public class EmpListServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    public EmpListServlet() {
//        super();
//    }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		EmpDAO dao = new EmpDAO();
//		List<AdminUserBean> empList = dao.getAllEmp();
//
//		// Gửi danh sách sang JSP
//		request.setAttribute("empList", empList);
//
//		// Điều hướng tới JSP
//		 request.getRequestDispatcher("/admin/empList.jsp").forward(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//}


package com.adminservlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.adminbean.AdminUserBean;
import com.admindao.EmpDAO;

@WebServlet("/empListServlet")
public class EmpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pageParam = request.getParameter("page");
		int page = 1;
		int limit = 5;  // Số bản ghi mỗi trang

		if (pageParam != null) {
			try {
				page = Integer.parseInt(pageParam);
			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		int start = (page - 1) * limit;

		EmpDAO dao = new EmpDAO();
		int totalCount = dao.getTotalEmployeeCount();
		List<AdminUserBean> empList = dao.getAllEmpPaging(start, limit);

		int totalPages = (int) Math.ceil((double) totalCount / limit);

		// Gửi dữ liệu sang JSP
		
		String message = request.getParameter("message");
		request.setAttribute("empList", empList);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("currentPage", page);
		request.setAttribute("message", message); 
		
		

		request.getRequestDispatcher("/admin/empList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
