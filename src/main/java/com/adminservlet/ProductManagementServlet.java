package com.adminservlet;

import java.io.IOException;
import java.util.ArrayList;
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
    private static final int RECORDS_PER_PAGE = 10;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/adminLogin.jsp");
            return;
        }

        AdminUserBean user = (AdminUserBean) session.getAttribute("loginUser");
        ProductDAO dao = new ProductDAO();

        String idSearch = request.getParameter("idSearch");
        String keywordSearch = request.getParameter("keywordSearch");
        String pageParam = request.getParameter("page");
     // Servlet 2 hoặc JSP
        String successMsg = (String) request.getSession().getAttribute("success");
        String errorMsg = (String) request.getSession().getAttribute("error");
        if (successMsg != null) {
            request.setAttribute("success", successMsg);
            request.getSession().removeAttribute("success");
        }
        if (errorMsg != null) {
            request.setAttribute("error", errorMsg);
            request.getSession().removeAttribute("error");
        }

        int page = 1;
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        List<Product> productList = new ArrayList<>();
        int totalRecords = 0;

        try {
            if (idSearch != null && !idSearch.trim().isEmpty()) {
                Product p = dao.getProductById(idSearch.trim());
                if (p != null) {
                    productList.add(p);
                }
                totalRecords = productList.size();
            } else if (keywordSearch != null && !keywordSearch.trim().isEmpty()) {
                List<Product> allMatching = dao.searchByKeyword(keywordSearch.trim());
                totalRecords = allMatching.size();

                int offset = (page - 1) * RECORDS_PER_PAGE;
                int toIndex = Math.min(offset + RECORDS_PER_PAGE, totalRecords);
                if (offset < totalRecords) {
                    productList = allMatching.subList(offset, toIndex);
                }
            } else {
                totalRecords = dao.getProductCount();
                int offset = (page - 1) * RECORDS_PER_PAGE;
                productList = dao.getProductsByPage(offset, RECORDS_PER_PAGE);
            }

            int totalPages = (int) Math.ceil(totalRecords / (double) RECORDS_PER_PAGE);

            request.setAttribute("productList", productList);
            request.setAttribute("loginUser", user);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("idSearch", idSearch);
            request.setAttribute("keywordSearch", keywordSearch);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/ProductManagement.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            throw new ServletException("商品一覧データ取得中にエラーが発生しました。", e);
        }
    }
}
