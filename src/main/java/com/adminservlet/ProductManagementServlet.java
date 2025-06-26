package com.adminservlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.adminbean.AdminUserBean;
import com.bean.Product;
import com.dao.ProductDAO;


@WebServlet("/ProductManagementServlet")
public class ProductManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductManagementServlet() {
        super();
        
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("loginUser") == null ) {
			response.sendRedirect(request.getContextPath() + "/admin/adminLogin.jsp");
			
			return;
		}
		
		//ユーザーのセッションを持つ
		AdminUserBean user = (AdminUserBean) session.getAttribute("loginUser");
		
		try {
			
			//SQLでor検索に対応させる
			
			//キーワード検索で使用する
			String keyword = request.getParameter("keyword");
			ProductDAO dao = new ProductDAO();
			
		    if (keyword != null && !keyword.trim().isEmpty()) {
		        List<Product> product = dao.searchByKeyword(keyword);
				request.setAttribute("productList", product);
				request.setAttribute("loginUser", user);
		        
//		    } else if(keyword) {
//		    	List<Product> product = dao.searchById(keyword);
//		    	request.setAttribute("productList",product);
//		    	request.setAttribute("loginUser",user); 

		    }else{
		    	//キーワードがなければすべてを返す
		    	List<Product> product = dao.getAllProducts();
				request.setAttribute("productList", product);
				request.setAttribute("loginUser", user);
				
		    }
			

			RequestDispatcher rd = request.getRequestDispatcher("/admin/ProductManagement.jsp");
			rd.forward(request, response);
				
		}catch(Exception e) {
			throw new ServletException("商品一覧データ取得中にエラーが発生しました。",e);
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.sendRedirect(request.getContextPath() + "ProductManagementServlet");
	}

}
