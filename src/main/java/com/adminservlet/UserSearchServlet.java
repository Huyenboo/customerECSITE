package com.adminservlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.User;
import com.dao.UserDAO;

/**
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/userSearchServlet")
public class UserSearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");

        try {
            UserDAO dao  = new UserDAO();
            List<User> list = dao.searchByName(keyword);

            request.setAttribute("userList", list);
            request.setAttribute("keyword", keyword);
            request.getRequestDispatcher(request.getContextPath()+"/admin/newCustomerAllow.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "中にエラーが発生しました。");
            request.getRequestDispatcher(request.getContextPath()+"/admin/newCustomerAllow.jsp").forward(request, response);
        }
    }
}
