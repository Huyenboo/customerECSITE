package com.adminservlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.Product;
import com.dao.ProductDAO;


@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditProductServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
	    String id = request.getParameter("id");
	    Product p = new ProductDAO().getProductById(id);
	    request.setAttribute("product", p);
	    request.getRequestDispatcher("/admin/EditProduct.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	    request.setCharacterEncoding("UTF-8");
	    Product p = new Product();
	    p.setId(Integer.parseInt(request.getParameter("id")));
	    p.setProName(request.getParameter("proName"));
	    p.setProUnit(request.getParameter("proUnit"));
	    p.setProUnitNum(Integer.parseInt(request.getParameter("proUnitNum")));
	    p.setProMemo(request.getParameter("proMemo"));

	    try {
			boolean ok = new ProductDAO().update(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    response.sendRedirect(request.getContextPath() + "/ProductManagementServlet");
		
		
	}

}
