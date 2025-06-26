package com.adminservlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.adminbean.AdminUserBean;
import com.admindao.AdminLoginDAO;

//ログイン時に使用する
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminLoginServlet() {
        super();
      
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String emp_id = request.getParameter("emp_id");
		String pass = request.getParameter("pass");
		
		try {
			
			AdminLoginDAO dao = new AdminLoginDAO();
		    AdminUserBean user = dao.login(emp_id, pass);
		    
		    if(user != null) {
		    	
		    	HttpSession session = request.getSession();
		    	session.setAttribute("loginUser", user);
		    	
		    	switch (user.getRole_id()) {
		    	case 1: //部長　実装はしてない
		    		response.sendRedirect(request.getContextPath() +"/admin/adminTop.jsp");
		    		break;
		    	case 2 ://営業画面
		    		response.sendRedirect(request.getContextPath() +"/admin/salesTop.jsp");
		    		break;
		    	case 3://管理者画面
		    		response.sendRedirect(request.getContextPath() +"/admin/managerTop.jsp");
		    		break;
		    	default:
		    		session.invalidate();
		    		request.setAttribute("error", "不正な権限です。");
		    		request.getRequestDispatcher(request.getContextPath() +"/admin/adminLogin.jsp").forward(request, response);
		    	}
		    	
		    }else {
		    	request.setAttribute("error", "社員番号または、パスワードが正しくありません。");
		    	request.getRequestDispatcher(request.getContextPath() +"/admin/adminLogin.jsp").forward(request, response);
		    }
		}catch(Exception e) {
			throw new ServletException("ログイン処理中にエラーが発生しました。"+ e);
		}

	}

}
