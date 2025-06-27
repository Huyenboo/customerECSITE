<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List, com.adminbean.AdminUserBean"%>
<%
List<AdminUserBean> empList = (List<AdminUserBean>) request.getAttribute("empList");
String keyword = request.getParameter("keyword") != null ? request.getParameter("keyword") : "";
%>
<html>
<head>
<title>社員一覧</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #fff;
	text-align: center;
	margin: 20px;
}

.container {
	max-width: 900px;
	margin: 0 auto;
}

h1 {
	margin-bottom: 10px;
}

p {
	margin-bottom: 20px;
}

.search-box {
	margin: 10px 0;
}

input[type="text"] {
	padding: 5px;
	width: 200px;
}

button, .link-btn {
	padding: 5px 10px;
	margin: 5px;
	cursor: pointer;
}

a.link-btn {
	background: none;
	border: none;
	color: #2c7be5;
	text-decoration: underline;
	cursor: pointer;
	padding: 0;
	margin: 0 5px;
}

a.link-btn:hover {
	color: #1a5fb4;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin: 20px 0;
}

th, td {
	border: 1px solid #ccc;
	padding: 8px;
	text-align: center;
	font-size: 14px;
}

th {
	background-color: #f0f0f0;
}

.top-btn {
	text-align: right;
	margin-top: 10px;
}
</style>
</head>
<body>

	<div class="container">

		<h1>社員一覧</h1>

		<div style="margin-bottom: 10px;">
			<button
				onclick="location.href='<%=request.getContextPath()%>/admin/newEmp.jsp'">＋新規登録</button>
		</div>

		<div class="search-box">
			<form action="EmployeeListServlet" method="get">
				<input type="text" name="keyword" placeholder="社員番号または社員名で検索"
					value="<%=keyword%>">
				<button type="submit">検索</button>
			</form>
		</div>

		<table>
			<tr>
				<th>社員ID</th>
				<th>社員名</th>
				<th>部署</th>
				<th>役職</th>
				<th>パスワード</th>
				<th>操作</th>
			</tr>

			<%
			if (empList != null && !empList.isEmpty()) {
				for (AdminUserBean emp : empList) {
			%>
			<tr>
				<td><%=emp.getEmp_id()%></td>
				<td><%=emp.getEmp_name()%></td>
				<td><%=emp.getRole_id()%></td>
				<td><%=emp.getEmp_position()%></td>
				<td><%=emp.getPass()%></td>
				<td><a
					href="<%=request.getContextPath()%>/UpdateEmployeeServlet?userId=<%=emp.getEmp_id()%>"
					class="link-btn">変更</a> <a
					href="<%=request.getContextPath()%>/EmployeeDeleteServlet?id=<%=emp.getId()%>"
					class="link-btn" onclick="return confirm('本当に削除しますか？');">削除</a></td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="6">データがありません。</td>
			</tr>
			<%
			}
			%>
		</table>

		<div class="top-btn">
			<form action="<%=request.getContextPath()%>/admin/managerTop.jsp">
				<button type="submit">TOPへ戻る</button>
			</form>
		</div>

	</div>

</body>
</html>
