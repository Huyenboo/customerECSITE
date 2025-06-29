<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.adminbean.AdminUserBean, com.bean.CartItem" %>
<%
    AdminUserBean user = (AdminUserBean) session.getAttribute("loginUser");    
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/salesTop.jsp");
        return;
    }
%>
<html>
<head>
<meta charset="UTF-8">
<title>注文管理</title>
<style>
body {
    font-family: "Yu Gothic UI", sans-serif;
    background-color: #f9f9f9;
}
h1 { text-align: center; margin-top: 20px; }
.container { max-width: 950px; margin: 20px auto; }
table { width: 100%; border-collapse: collapse; margin-top: 15px; }
th, td { border: 1px solid #333; padding: 5px; text-align: center; font-size: 14px; }
th { background: #eee; }
.search-section { margin: 15px 0; text-align: center; }
button, .btn-link { padding: 5px 10px; margin: 0 5px; }
a.btn-link { text-decoration: none; background: #ddd; border: 1px solid #aaa; }
.pagination { margin-top: 15px; text-align: center; }
</style>
</head>
<body>

<div class="container">
    <h1>注文管理</h1>

    <div class="search-section">
        <form method="get" action="OrderManagementServlet" style="display:inline;">
            顧客名：
            <input type="text" name="userName" value="<%= request.getAttribute("keyword") != null ? request.getAttribute("keyword") : "" %>" />
            <input type="submit" value="検索" />
        </form>
    </div>

    <table>
        <tr>
            <th>発注日</th>
            <th>注文ID</th>
            <th>顧客名</th>
            <th>商品ID</th>
            <th>商品名</th>
            <th>数量</th>
            <th>単価</th>
            <th>小計</th>
            <th>お届け予定日</th>
            <th>備考</th>
            <th>編集</th>
        </tr>

        <%
        @SuppressWarnings("unchecked")
        List<CartItem> orders = (List<CartItem>) request.getAttribute("orderList");
        double proPrice;
        if (orders != null && !orders.isEmpty()) {
            for (CartItem ci : orders) {
            	proPrice = ci.getProduct().getProPrice();
        %>
        <tr>
            <td><%= ci.getOrderDay() %></td>
            <td><%= ci.getOrderId() %></td>
            <td><%= ci.getCompanyName() %></td>
            <td><%= ci.getProduct().getProId() %></td>
            <td><%= ci.getProduct().getProName() %></td>
            <td><%= ci.getQuantity() %></td>
            <td><%= ci.getProduct().getProPrice() %></td>
            <td><%= ci.getSubtotal(proPrice) %></td>
            <td><%= ci.getDeliveryDate() %></td>
            <td><%= ci.getOrderMemo() != null ? ci.getOrderMemo() : "" %></td>
            <td>
                <a href="OrderDetailUpdate?id=<%= ci.getOrderId() %>&memo=<%= "確定" %>">確定</a>
                <a href="OrderEditServlet?id=<%= ci.getOrderId() %>">編集</a>
                <a href="OrderDeleteServlet?id=<%= ci.getOrderId() %>" onclick="return confirm('本当に削除しますか？');">削除</a>
            </td>
        </tr>
        <% }
        } else { %>
        <tr><td colspan="11">該当する注文履歴がありません。</td></tr>
        <% } %>
    </table>

    <div class="pagination">
        <%
            Integer currentPage = (Integer) request.getAttribute("currentPage");
            Integer totalPages = (Integer) request.getAttribute("totalPages");
            String keyword = (String) request.getAttribute("keyword");
            if (currentPage == null) currentPage = 1;
            if (totalPages == null) totalPages = 1;
            if (keyword == null) keyword = "";
        %>

        <% if (currentPage > 1) { %>
            <a href="OrderManagementServlet?page=<%= currentPage - 1 %>&userName=<%= keyword %>">前へ</a>
        <% } %>
        <span><%= currentPage %> / <%= totalPages %></span>
        <% if (currentPage < totalPages) { %>
            <a href="OrderManagementServlet?page=<%= currentPage + 1 %>&userName=<%= keyword %>">次へ</a>
        <% } %>
    </div>

    <br>
    <a href="<%= request.getContextPath() %>/admin/salesTop.jsp" class="btn-link">TOPへ戻る</a>
</div>

</body>
</html>
