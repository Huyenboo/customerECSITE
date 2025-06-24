package com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bean.User;
import com.dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String adderss = req.getParameter("address");

        UserDAO dao = new UserDAO();
        User user = dao.loginUser(phone, password);

        if (user == null) {
            req.setAttribute("errMsg", "電話番号またはパスワードが違います");
            req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
            return;
        }

        String status = user.getStatus();

        if ("accept".equals(status)) {
            // Đã được phê duyệt
            HttpSession session = req.getSession();
            session.setAttribute("loginUser", user);
            session.setAttribute("userId", user.getCompanyId());
            session.setAttribute("userName", user.getManagerName());
            session.setAttribute("address", user.getCompanyAddress());

            System.out.println("ログイン成功: " + user.getManagerName());
            System.out.println("住所: " + user.getCompanyAddress());

            resp.sendRedirect(req.getContextPath() + "/user/customer.jsp");

        } else if ("pending".equals(status)) {
            req.setAttribute("errMsg", "現在、登録承認中です");
            req.getRequestDispatcher("/user/login.jsp").forward(req, resp);

        } else if ("reject".equals(status)) {
            req.setAttribute("errMsg", "登録が承認されませんでした");
            req.getRequestDispatcher("/user/login.jsp").forward(req, resp);

        } else {
            req.setAttribute("errMsg", "不明なステータスです。管理者にお問い合わせください。");
            req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
        }
    }
}
