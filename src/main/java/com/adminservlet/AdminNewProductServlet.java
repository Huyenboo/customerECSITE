package com.adminservlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bean.Product;
import com.dao.ProductDAO;

@WebServlet("/AdminNewProductServlet")
public class AdminNewProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // 登録画面へ遷移（フォーム表示）
        req.getRequestDispatcher("/admin/NewProductInsert.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	
        req.setCharacterEncoding("UTF-8");

        // 入力フォームから値を取得
        String proId = req.getParameter("proId");
        String proName = req.getParameter("proName");
        double proPrice = Double.parseDouble(req.getParameter("proPrice")) ;
        String proUnitNum = req.getParameter("proUnitNum");
        String proMemo = req.getParameter("proMemo");

        // Bean にセット
        Product p = new Product();
        p.setProId(proId);
        p.setProName(proName);
        try { p.setProUnitNum(Integer.parseInt(proUnitNum)); }
        catch (NumberFormatException ignored) {}
        p.setProPrice(proPrice);
        p.setProMemo(proMemo);

        // DAO に保存
        ProductDAO dao = new ProductDAO();
        boolean success = dao.insertProduct(p);

        // フラッシュメッセージ表示
        req.setAttribute("message", success ? "登録成功しました" : "登録に失敗しました");
        
        // formの再表示 or リダイレクト
        if (success) {
            res.sendRedirect("ProductManagementServlet");
        } else {
            req.getRequestDispatcher("/admin/NewProductInsert.jsp").forward(req, res);
        }
    }
}
