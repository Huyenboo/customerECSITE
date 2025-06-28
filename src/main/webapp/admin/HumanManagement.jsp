<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List, com.bean.User"%>
<%@ page import="com.adminbean.AdminUserBean"%>
<%
AdminUserBean user = (AdminUserBean) session.getAttribute("loginUser");
if (user == null) {
	response.sendRedirect(request.getContextPath() + "/salesTop.jsp");
	return;
}
%>
<html>
<head>
<title>顧客管理</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #fff;
	text-align: center;
	margin: 0;
	padding: 20px;
}

h1 {
	margin-bottom: 20px;
}

.container {
	max-width: 800px;
	margin: 0 auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin: 15px auto;
}

th, td {
	border: 1px solid #333;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #eee;
}

.search-container {
	margin: 10px 0;
}

button, input[type="text"] {
	padding: 5px 10px;
	margin: 0 5px;
	font-size: 14px;
}

.action-cell a {
	margin: 0 5px;
	text-decoration: none;
	color: blue;
}

.pagination {
	margin-top: 15px;
}
</style>
</head>
<body>

	<h1>顧客管理</h1>
	<div style="text-align: right; margin-bottom: 10px;">
		<a href="<%=request.getContextPath()%>/admin/salesTop.jsp">TOPへ戻る</a>
	</div>


	<div class="container">

		<div class="search-container">
			<form action="<%=request.getContextPath()%>/HumanManagementServlet"
				method="get">
				<input type="text" name="keyword" placeholder="会社名を検索"
					value="<%=(request.getAttribute("keyword") != null) ? request.getAttribute("keyword") : ""%>">
				<button type="submit">検索</button>
			</form>
		</div>

		<table>
			<tr>
				<th>会社ID</th>
				<th>会社名</th>
				<th>担当者名</th>
				<th>電話番号</th>
				<th>住所</th>
				<th>操作</th>
			</tr>
			<%
			List<User> list = (List<User>) request.getAttribute("customerList");
			if (list != null) {
				for (User c : list) {
			%>
			<tr>
				<td><%=c.getCompanyId()%></td>
				<td><%=c.getCompanyName()%></td>
				<td><%=c.getManagerName()%></td>
				<td><%=c.getManagerPhoneNum()%></td>
				<td><%=c.getCompanyAddress()%></td>
				<td><a
					href="<%=request.getContextPath()%>/HumanEditServlet?id=<%=c.getId()%>">変更</a>
					| <a
					href="<%=request.getContextPath()%>/HumanDeleteServlet?id=<%=c.getId()%>"
					onclick="return confirm('削除しますか？');">削除</a></td>
			</tr>
			<%
			}
			}
			%>
		</table>

		<div class="pagination">
			<%
			Integer currentPageObj = (Integer) request.getAttribute("currentPage");
			Integer totalPagesObj = (Integer) request.getAttribute("totalPages");
			String keyword = (String) request.getAttribute("keyword");

			int currentPage = (currentPageObj != null) ? currentPageObj : 1;
			int totalPages = (totalPagesObj != null) ? totalPagesObj : 1;
			if (keyword == null)
				keyword = "";

			if (currentPage > 1) {
			%>
			<a
				href="<%=request.getContextPath()%>/HumanManagementServlet?page=<%=currentPage - 1%>&keyword=<%=keyword%>">前へ</a>
			<%
			}
			%>

			<%=currentPage%>
			/
			<%=totalPages%>

			<%
			if (currentPage < totalPages) {
			%>
			<a
				href="<%=request.getContextPath()%>/HumanManagementServlet?page=<%=currentPage + 1%>&keyword=<%=keyword%>">次へ</a>
			<%
			}
			%>
		</div>
</body>
</html>
