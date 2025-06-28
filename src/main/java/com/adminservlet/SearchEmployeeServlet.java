package com.adminservlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.adminbean.AdminUserBean;
import com.admindao.EmpDAO;

@WebServlet("/SearchEmployeeServlet")
public class SearchEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        EmpDAO dao = new EmpDAO();
        List<AdminUserBean> resultList;

        if (keyword != null && !keyword.trim().isEmpty()) {
            resultList = dao.searchEmployeeByKeyword(keyword.trim());
        } else {
            resultList = dao.getAllEmp();
        }

        request.setAttribute("empList", resultList);
        request.setAttribute("keyword", keyword);
        request.getRequestDispatcher("/admin/empList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
