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
    margin: 0;
    padding: 30px;
    text-align: center;
}

h1 {
    margin-bottom: 20px;
    font-size: 22px;
}

.container {
    max-width: 900px;
    margin: 0 auto;
    text-align: center;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 15px;
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
    margin: 20px 0;
}

input[type="text"] {
    width: 300px;
    padding: 5px;
    margin-right: 5px;
}

button {
    padding: 5px 15px;
}

.pagination {
    margin: 15px 0;
}

.top-link {
    text-align: right;
    margin-bottom: 10px;
}

.top-link a {
    text-decoration: none;
    padding: 5px 10px;
    border: 1px solid #333;
    background-color: #f9f9f9;
    color: #000;
}
.top-link {
    text-align: right;
    margin-bottom: 10px;
}

.btn {
    display: inline-block;
    padding: 5px 15px;
    border: 1px solid #333;
    background-color: #fff;
    color: #000;
    text-decoration: none;
    font-size: 14px;
    cursor: pointer;
    transition: background-color 0.2s;
}
</style>
</head>
<body>

	<h1>顧客管理</h1>
	




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
		<div class="top-link">
    <a href="<%=request.getContextPath()%>/admin/salesTop.jsp" class="btn">TOPへ戻る</a>
</div>


		
		
</div>		
</body>
</html>
