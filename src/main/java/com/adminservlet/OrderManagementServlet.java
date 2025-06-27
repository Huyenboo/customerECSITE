package com.adminservlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.adminbean.AdminUserBean;
import com.bean.CartItem;
import com.dao.OrderDAO;


@WebServlet("/OrderManagementServlet")
public class OrderManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OrderManagementServlet() {
        super();
        
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("123");
        HttpSession session = request.getSession(false);
        AdminUserBean user = (session != null) ? (AdminUserBean) session.getAttribute("loginUser") : null;
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/admin/salesTop.jsp");
            return;
        }

        String userIdParam = request.getParameter("userId");
//        String userId = "";
//        if (userIdParam != null && !userIdParam.isEmpty()) {
//            try {
//                userId = userIdParam ;
//            } catch (NumberFormatException e) {
//                userId = null;
//            }
//        }
//        
        List<CartItem> orders;
        OrderDAO dao = new OrderDAO();
        
        if (userIdParam != null ) {
            orders = dao.getAllOrderIdByUserId(userIdParam);
        } else {
            orders = dao.getAllOrderIdByUserId("0"); // userId=0なら全取得を DAO で対応
        }
        for(CartItem item : orders) {
        	System.out.println(item.toString());
        }
        request.setAttribute("orderList", orders);
        request.getRequestDispatcher("/admin/OrderManagement.jsp").forward(request, response);
    }
	
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
		
//        String userIdParam = request.getParameter("userId");
//        int userId = 0;
//        if (userIdParam != null && !userIdParam.isEmpty()) {
//            try {
//                userId = Integer.parseInt(userIdParam);
//            } catch (NumberFormatException e) {
//                userId = 0;
//            }
//        }
//
//        OrderDAO dao = new OrderDAO();
//        List<CartItem> orders;
//        if (userId > 0) {
//            orders = dao.getAllOrderIdByUserId(userId);
//        } else {
//            orders = dao.getAllOrderIdByUserId(0); // userId=0なら全取得を DAO で対応
//        }
//
//        request.setAttribute("orderList", orders);
//        request.getRequestDispatcher("/jsp/OrderManagement.jsp").forward(request, response);
   
//	}

}
