//package com;
//
//import java.io.IOException;
//import java.util.List;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@WebServlet("/companyList")
//public class CompanyListServlet extends HttpServlet {
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		try {
//			UserDAO dao = new UserDAO();
//			List<company> companyList = dao.companyList(); // Lấy danh sách công ty
//			HttpSession session = request.getSession();
//			session.setAttribute("companyList", companyList); // Lưu vào session
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//			
//		// リスト表示
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/companyList.jsp");
//		dispatcher.forward(request, response);
//	}
//}
