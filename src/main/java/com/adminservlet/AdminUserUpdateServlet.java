package com.adminservlet;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.adminbean.AdminUserBean;
import com.admindao.EmpDAO;

@WebServlet("/UpdateEmployeeServlet")
public class AdminUserUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String empId = req.getParameter("userId");

        if (empId == null || empId.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/empListServlet");
            return;
        }

        EmpDAO dao = new EmpDAO();
        AdminUserBean user = dao.getEmplById(empId);

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/empListServlet");
            return;
        }

        req.setAttribute("userEdit", user);
        req.getRequestDispatcher("/admin/adminUserEdit.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            AdminUserBean loginUser = (AdminUserBean) request.getSession().getAttribute("loginUser");
            if (loginUser == null) {
                response.sendRedirect(request.getContextPath() + "/ReturnAdminLoginServlet");
                return;
            }

            String emp_id = request.getParameter("emp_id");
            String emp_name = request.getParameter("emp_name");
            String emp_position = request.getParameter("emp_position");
            String pass = request.getParameter("pass");
            String roleStr = request.getParameter("role_id");

            boolean hasError = false;
            String errorMsg = "";

            if (emp_name == null || emp_name.isEmpty()
                    || emp_position == null || emp_position.isEmpty()
                    || pass == null || pass.isEmpty()
                    || roleStr == null || roleStr.isEmpty()) {
                hasError = true;
                errorMsg = "未入力項目があります。";
            } else if (emp_name.length() > 100 || emp_position.length() > 50 || pass.length() > 50) {
                hasError = true;
                errorMsg = "文字数もしくは形式が正しくありません。";
            }

            int role_id = 0;
            try {
                role_id = Integer.parseInt(roleStr);
                if (role_id < 1 || role_id > 3) {
                    hasError = true;
                    errorMsg = "権限の選択が正しくありません。";
                }
            } catch (Exception e) {
                hasError = true;
                errorMsg = "権限の選択が正しくありません。";
            }

            AdminUserBean user = new AdminUserBean();
            user.setEmp_id(emp_id);
            user.setEmp_name(emp_name);
            user.setEmp_position(emp_position);
            user.setPass(pass);
            user.setRole_id(role_id);

            if (hasError) {
                request.setAttribute("errorMsg", errorMsg);
                request.setAttribute("userEdit", user);
                request.getRequestDispatcher("/admin/adminUserEdit.jsp").forward(request, response);
                return;
            }

            EmpDAO dao = new EmpDAO();
            boolean success = dao.updateUser(user);

            if (success) {
                String msg = URLEncoder.encode("登録が完了しました。", "UTF-8");
                response.sendRedirect(request.getContextPath() + "/empListServlet?message=" + msg);
            } else {
                request.setAttribute("errorMsg", "更新に失敗しました。");
                request.setAttribute("userEdit", user);
                request.getRequestDispatcher("/admin/adminUserEdit.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "システムエラーが発生しました。");
            request.getRequestDispatcher("/admin/adminUserEdit.jsp").forward(request, response);
        }
    }
}
