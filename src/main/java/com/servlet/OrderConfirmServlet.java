//package com.servlet;
//
//import java.io.IOException;
//import java.util.List;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import com.bean.CartItem;
//
//@WebServlet("/orderConfirm")
//public class OrderConfirmServlet extends HttpServlet {
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		request.setCharacterEncoding("UTF-8");
//		
//		String confirmCheck = request.getParameter("confirmCheck");
//		if (confirmCheck == null) {
//			request.setAttribute("errMsg", "注文確認にチェックを入れてください。");
//			request.getRequestDispatcher("/user/orderConfirm.jsp").forward(request, response);
//			return;
//		}
//		
//		HttpSession session = request.getSession();
//		String userId = (String) session.getAttribute("userId");
//		String userName = (String) session.getAttribute("userName");
//		String userAddress = (String) session.getAttribute("user_address");
//		List<CartItem> cartList = (List<CartItem>) session.getAttribute("cart");
//		
//		
//		if (userId == null || userName == null || cartList == null || cartList.isEmpty()) {
//			response.sendRedirect("login.jsp");
//			return;
//		}
//		
//		OrderDAO dao = new OrderDAO();
//		boolean success = true;
//		for (CartItem item : cartList) {
//			if (!dao.insertOrder(userId, userName, item)) {
//				success = false;
//				break;
//			}
//		}
//		
//		//đặt hàng xong xóa giỏ hàng 
//		if (success) {
//			session.removeAttribute("cart");
//			response.sendRedirect("/user/orderSuccess.jsp");
//		} else {
//  	     	request.setAttribute("errMsg", "注文登録に失敗しました。");
//  	     	request.getRequestDispatcher("/user/orderConfirm.jsp").forward(request, response);
//		}
//	}
//}

//		HttpSession session = request.getSession();
//		List<CartItem> cartList = (List<CartItem>) session.getAttribute("cart");
//		String address = (String) session.getAttribute("address");
//
//		if (cartList == null || cartList.isEmpty()) {
//			response.sendRedirect(request.getContextPath() + "/cart.jsp");
//			return;
//		}
//
//		request.setAttribute("address", address);
//		request.getRequestDispatcher("/user/orderConfirm.jsp").forward(request, response);
//	}
//}



//package com.servlet;
//
//import java.io.IOException;
//import java.util.List;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import com.bean.CartItem;
//
//@WebServlet("/orderConfirm")
//public class OrderConfirmServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        request.setCharacterEncoding("UTF-8");
//
//        HttpSession session = request.getSession();
//
//        List<CartItem> cartList = (List<CartItem>) session.getAttribute("cart");
//        String address = (String) session.getAttribute("address");
//
//        // Kiểm tra dữ liệu hợp lệ
//        if (cartList == null || cartList.isEmpty()) {
//            response.sendRedirect(request.getContextPath() + "/user/cart.jsp");
//            return;
//        }
//
//        if (address == null || address.isEmpty()) {
//            response.sendRedirect(request.getContextPath() + "/user/cart.jsp");
//            return;
//        }
//
//        // Forward sang trang xác nhận
//        request.setAttribute("address", address);
//        request.getRequestDispatcher("/user/orderConfirm.jsp").forward(request, response);
//    }
//}


package com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bean.CartItem;

@WebServlet("/orderConfirm")
public class OrderConfirmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        List<CartItem> cartList = (List<CartItem>) session.getAttribute("cart");
        String address = (String) session.getAttribute("address");

        System.out.println("=== orderConfirm 呼び出し確認 ===");
        System.out.println("Cart Size: " + (cartList != null ? cartList.size() : 0));
        System.out.println("住所: " + address);

        // Kiểm tra giỏ hàng hợp lệ
        if (cartList == null || cartList.isEmpty()) {
            System.out.println("カートが空です。戻ります。");
            response.sendRedirect(request.getContextPath() + "/user/cart.jsp");
            return;
        }

        // Kiểm tra tất cả sản phẩm đã có ngày giao hàng
        for (CartItem item : cartList) {
            if (item.getOrderArrivedDay() == null) {
                System.out.println("納期未入力の商品があります。戻ります。");
                response.sendRedirect(request.getContextPath() + "/user/cart.jsp");
                return;
            }
        }

        // Kiểm tra địa chỉ
        if (address == null || address.isEmpty()) {
            System.out.println("住所情報が不足しています。戻ります。");
            response.sendRedirect(request.getContextPath() + "/user/cart.jsp");
            return;
        }

        // Đủ điều kiện, chuyển đến trang xác nhận
        request.setAttribute("address", address);
        request.getRequestDispatcher("/user/orderConfirm.jsp").forward(request, response);
    }
}


