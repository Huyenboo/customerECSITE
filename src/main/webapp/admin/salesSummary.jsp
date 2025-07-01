<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.bean.SalesSummary" %>

<html>
<head>
<meta charset="UTF-8">
<title>売上日計表</title>
<style>
    body { font-family: "Yu Gothic UI", sans-serif; padding: 30px; }
    table { width: 100%; border-collapse: collapse; margin-top: 15px; }
    th, td { border: 1px solid #333; padding: 5px; text-align: center; }
    th { background: #eee; }
    .search-box { margin-bottom: 20px; }
    h1{text-align:center}
</style>
</head>
<body>

<h1>売上日計表</h1>

<div class="search-box">
    <form method="get" action="salesSummary">
        対象月:
        <input type="month" name="month" value="<%= request.getAttribute("selectedMonth") != null ? request.getAttribute("selectedMonth") : "" %>">
        <input type="submit" value="検索">
    </form>
</div>

<table>
    <tr>
        <th>注文日</th>
        <th>会社名</th>
        <th>商品ID</th>
        <th>商品名</th>
        <th>数量</th>
        <th>単価 (円)</th>
        <th>小計 (円)</th>
    </tr>

    <%
        List<SalesSummary> list = (List<SalesSummary>) request.getAttribute("salesList");
        if (list != null && !list.isEmpty()) {
            for (SalesSummary s : list) {
    %>
    <tr>
        <td><%= s.getOrderDay() %></td>
        <td><%= s.getCompanyName() %></td>
        <td><%= s.getProductId() %></td>
        <td><%= s.getProductName() %></td>
        <td><%= s.getOrderAmount() %></td>
        <td><%= s.getProductPrice() %></td>
        <td><%= s.getSubtotal() %></td>
    </tr>
    <% }
    } else { %>
    <tr><td colspan="7">該当データがありません。</td></tr>
    <% } %>
</table>

<%
    Integer total = (Integer) request.getAttribute("totalRevenue");
    if (total != null) {
%>
<p>合計売上: <%= total %> 円</p>
<% } %>

<div>
    <%
        int currentPage = request.getAttribute("currentPage") != null ? (Integer) request.getAttribute("currentPage") : 1;
        int totalPage = request.getAttribute("totalPage") != null ? (Integer) request.getAttribute("totalPage") : 1;
        String month = request.getAttribute("selectedMonth") != null ? (String) request.getAttribute("selectedMonth") : "";
    %>

    <% if (currentPage > 1) { %>
        <a href="salesSummary?page=<%= currentPage - 1 %>&month=<%= month %>">前へ</a>
    <% } %>

    <span>ページ <%= currentPage %> / <%= totalPage %></span>

    <% if (currentPage < totalPage) { %>
        <a href="salesSummary?page=<%= currentPage + 1 %>&month=<%= month %>">次へ</a>
    <% } %>
</div>

</body>
</html>
