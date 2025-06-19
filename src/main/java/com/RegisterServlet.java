package com;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        User user = new User();
        user.setCompanyName(req.getParameter("company_name"));
        user.setCompanyAddress(req.getParameter("company_address"));
        user.setPresidentPhoneNum(req.getParameter("president_phone_num"));
        user.setManagerName(req.getParameter("manager_name"));
        user.setManagerEmail(req.getParameter("manager_email"));
        user.setManagerPhoneNum(req.getParameter("manager_phone_num"));
        user.setPassword(req.getParameter("password"));

        try {
            UserDAO dao = new UserDAO();
            boolean success = dao.registerUser(user);
            if (success) {
                resp.sendRedirect(req.getContextPath() + "/jsp/registerSuccess.jsp");
            } else {
                resp.getWriter().println("登録に失敗しました。もう一度お試しください。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("サーバーエラーが発生しました。");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("GETメソッドはサポートされていません。POSTでアクセスしてください。");
    }
}
