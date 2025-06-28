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
//@WebServlet("/SearchEmployeeServlet")
//public class SearchEmployeeServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String keyword = request.getParameter("keyword") != null ? request.getParameter("keyword") : "";
//        String pageParam = request.getParameter("page");
//        int page = 1;
//        int limit = 5; // 5 bản ghi mỗi trang
//
//        if (pageParam != null) {
//            try {
//                page = Integer.parseInt(pageParam);
//            } catch (NumberFormatException e) {
//                page = 1;
//            }
//        }
//
//        int start = (page - 1) * limit;
//
//        EmpDAO dao = new EmpDAO();
//        int totalCount = dao.getTotalEmployeeCount(keyword);
//        List<AdminUserBean> resultList = dao.searchEmployeeByKeywordPaging(keyword, start, limit);
//
//        int totalPages = (int) Math.ceil((double) totalCount / limit);
//
//        request.setAttribute("empList", resultList);
//        request.setAttribute("keyword", keyword);
//        request.setAttribute("totalPages", totalPages);
//        request.setAttribute("currentPage", page);
//
//        request.getRequestDispatcher("/admin/empList.jsp").forward(request, response);
//    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doGet(request, response);
//    }
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

@WebServlet("/SearchEmployeeServlet")
public class SearchEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyword = request.getParameter("keyword") != null ? request.getParameter("keyword").trim() : "";
		String pageParam = request.getParameter("page");
		int page = 1;
		int limit = 5;

		if (pageParam != null) {
			try {
				page = Integer.parseInt(pageParam);
			} catch (NumberFormatException e) {
				page = 1;
			}
		}

		int start = (page - 1) * limit;

		EmpDAO dao = new EmpDAO();
		int totalCount;
		List<AdminUserBean> resultList;

		if (keyword.isEmpty()) {
			// Hiển thị tất cả có phân trang
			totalCount = dao.getTotalEmployeeCount();
			resultList = dao.getAllEmpPaging(start, limit);
		} else {
			// Tìm kiếm có phân trang
			totalCount = dao.getTotalEmployeeCount(keyword);
			resultList = dao.searchEmployeeByKeywordPaging(keyword, start, limit);
		}

		int totalPages = (int) Math.ceil((double) totalCount / limit);

		request.setAttribute("empList", resultList);
		request.setAttribute("keyword", keyword);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("currentPage", page);

		request.getRequestDispatcher("/admin/empList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
