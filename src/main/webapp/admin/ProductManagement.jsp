<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.bean.Product"%>
<%@ page import="com.adminbean.AdminUserBean"%>
<%@ page import="com.dao.ProductDAO"%>
<%@ page import="java.util.List"%>

<%
AdminUserBean user = (AdminUserBean) session.getAttribute("loginUser");
if (user == null) {
	response.sendRedirect(request.getContextPath() + "/adminLogin.jsp");
	return;
}
@SuppressWarnings("unchecked")
List<Product> products = (List<Product>) request.getAttribute("productList");
String keyword = (String) request.getAttribute("keyword");
if (keyword == null) {
	keyword = "";
}
%>
<html>
<head>
<title>商品管理</title>
<style>
body {
    font-family: "Yu Gothic UI", sans-serif;
    background-color: #fff;
    text-align: center;
    margin: 20px;
}

h1 {
    margin-bottom: 10px;
}

p {
    margin-bottom: 20px;
}

.search-box {
    margin: 15px 0;
}

input[type="text"] {
    padding: 5px;
    width: 300px;
}

button, .link-btn, input[type="submit"] {
    padding: 5px 10px;
    margin: 5px;
    cursor: pointer;
}

a.link-btn, a {
    color: #2c7be5;
    text-decoration: underline;
    margin: 0 5px;
}

a.link-btn:hover, a:hover {
    color: #1a5fb4;
}

.table-fixed {
    margin: 0 auto;
    width: 90%;
    border-collapse: collapse;
}

.table-fixed th, .table-fixed td {
    border: 1px solid #333;
    padding: 8px;
    text-align: center;
}

th {
    background-color: #f0f0f0;
}

.pagination-controls {
    margin: 20px 0;
}

.right-align {
    text-align: right;
    width: 90%;
    margin: 0 auto;
}
</style>
</head>
<body>

<h1>商品管理</h1>

<p><%=user.getEmp_name()%> さん（<%=user.getRole_name()%>）</p>

<div class="pagination-controls">
    <button onclick="showPage(currentPage-1)" id="btnPrev">前へ</button>
    <span id="pageInfo">ページ 1</span>
    <button onclick="showPage(currentPage+1)" id="btnNext">次へ</button>
</div>

<div class="search-box">
    <form action="<%=request.getContextPath()%>/ProductManagementServlet" method="get">
        <input type="text" name="keyword" value="<%=keyword%>" placeholder="商品IDまたは名前で検索" />
        <input type="submit" value="検索" />
    </form>
</div>

<div class="right-align">
    <a href="<%=request.getContextPath()%>/AdminNewProductServlet">新規登録</a>
</div>

<table id="productTable" class="table-fixed">
    <tr>
        <th>商品ID</th>
        <th>商品名</th>
        <th>単価</th>
        <th>備考</th>
        <th>操作</th>
    </tr>
    <% if (products != null) {
           for (Product p : products) { %>
    <tr>
        <td><%=p.getProId()%></td>
        <td><%=p.getProName()%></td>
        <td>¥<%=p.getProPrice()%></td>
        <td><%=p.getProMemo() != null ? p.getProMemo() : ""%></td>
        <td>
            <a href="<%=request.getContextPath()%>/EditProductServlet?id=<%=p.getProId()%>">変更</a>
            <a href="<%=request.getContextPath()%>/DeleteProductServlet?id=<%=p.getProId()%>" onclick="return confirm('削除しますか？');">削除</a>
        </td>
    </tr>
    <% }} %>
</table>

<script>
const table = document.getElementById('productTable');
const rows = Array.from(table.getElementsByTagName('tr')).slice(1);
const recordsPerPage = 30;
let currentPage = 1;
const numPages = () => Math.ceil(rows.length / recordsPerPage);

function showPage(page) {
    if (page < 1 || page > numPages()) return;
    currentPage = page;
    const start = (page - 1) * recordsPerPage;
    const end = start + recordsPerPage;
    rows.forEach((r, i) => r.classList.toggle('hidden', i < start || i >= end));
    document.getElementById('pageInfo').textContent = 'ページ ' + page;
    document.getElementById('btnPrev').disabled = (page === 1);
    document.getElementById('btnNext').disabled = (page === numPages());
}

document.addEventListener('DOMContentLoaded', () => {
    showPage(1);
});
</script>

</body>
</html>
