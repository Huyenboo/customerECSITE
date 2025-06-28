<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.bean.Product"%>
<%@ page import="com.adminbean.AdminUserBean"%>
<%@ page import="java.util.List"%>

<%
AdminUserBean user = (AdminUserBean) session.getAttribute("loginUser");
if (user == null) {
	response.sendRedirect(request.getContextPath() + "/admin/adminLogin.jsp");
	return;
}

List<Product> products = (List<Product>) request.getAttribute("productList");
int currentPage = request.getAttribute("currentPage") != null ? (int) request.getAttribute("currentPage") : 1;
int totalPages = request.getAttribute("totalPages") != null ? (int) request.getAttribute("totalPages") : 1;
String idSearch = request.getParameter("idSearch") != null ? request.getParameter("idSearch") : "";
String keywordSearch = request.getParameter("keywordSearch") != null ? request.getParameter("keywordSearch") : "";
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

table {
	width: 90%;
	margin: 0 auto;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #333;
	padding: 8px;
}

.search-box {
	margin: 15px 0;
}

button, .link-btn, input[type="submit"] {
	padding: 5px 10px;
	margin: 5px;
	cursor: pointer;
}

.search-container {
	display: flex;
	justify-content: center;
	gap: 20px;
	margin: 15px 0;
}

.search-form {
	display: flex;
	align-items: center;
	gap: 10px;
}

.search-form input[type="text"] {
	padding: 5px;
	width: 200px;
}

.search-form input[type="submit"] {
	padding: 5px 10px;
	cursor: pointer;
}
</style>
</head>
<body>

	<h1>商品管理</h1>


	<div class="search-container">
		<form action="<%=request.getContextPath()%>/ProductManagementServlet"
			method="get" class="search-form">
			<input type="text" name="idSearch" placeholder="商品IDで検索"
				value="<%=idSearch%>"> <input type="submit" value="ID検索">
		</form>

		<form action="<%=request.getContextPath()%>/ProductManagementServlet"
			method="get" class="search-form">
			<input type="text" name="keywordSearch" placeholder="商品名で検索"
				value="<%=keywordSearch%>"> <input type="submit"
				value="名前検索">
		</form>
	</div>

	<div style="text-align: right; width: 90%; margin: 0 auto;">
		<a href="<%=request.getContextPath()%>/AdminNewProductServlet">新規登録</a>
	</div>

	<table>
		<tr>
			<th>商品ID</th>
			<th>商品名</th>
			<th>単価</th>
			<th>備考</th>
			<th>操作</th>
		</tr>
		<%
		if (products != null) {
			for (Product p : products) {
		%>
		<tr>
			<td><%=p.getProId()%></td>
			<td><%=p.getProName()%></td>
			<td>¥<%=p.getProPrice()%></td>
			<td><%=p.getProMemo() != null ? p.getProMemo() : ""%></td>
			<td><a
				href="<%=request.getContextPath()%>/EditProductServlet?id=<%=p.getProId()%>">変更</a>
				<a
				href="<%=request.getContextPath()%>/DeleteProductServlet?id=<%=p.getProId()%>"
				onclick="return confirm('削除しますか？');">削除</a></td>
		</tr>
		<%
		}
		}
		%>
	</table>

	<div class="pagination-controls">
		<%
		if (currentPage > 1) {
		%>
		<a
			href="<%=request.getContextPath()%>/ProductManagementServlet?page=<%=currentPage - 1%>&keywordSearch=<%=keywordSearch%>&idSearch=<%=idSearch%>">前へ</a>
		<%
		}
		%>
		<span>ページ <%=currentPage%> / <%=totalPages%></span>
		<%
		if (currentPage < totalPages) {
		%>
		<a
			href="<%=request.getContextPath()%>/ProductManagementServlet?page=<%=currentPage + 1%>&keywordSearch=<%=keywordSearch%>&idSearch=<%=idSearch%>">次へ</a>
		<%
		}
		%>
	</div>

</body>
</html>
