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
.container {
	max-width: 800px;
	margin: 20px auto;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #333;
	padding: 5px;
}

.button {
	margin: 5px;
}
</style>
</head>
<body>
	<div class="container">
		<h1>顧客管理</h1>
		<p><%=user.getEmp_name()%>
			さん（<%=user.getRole_name()%>）
		</p>

		<!--    <a href="/HumanChangeServlet" class="button">編集</a>-->

		<table>
			<tr>
				<th>会社名</th>
				<th>担当者名</th>
				<th>電話番号</th>
				<th>メールアドレス</th>
				<th>住所</th>
				<th>操作</th>


			</tr>
			<%
			List<User> list = (List<User>) request.getAttribute("customerList");
			if (list != null) {
				for (User c : list) {
			%>
			<tr>
				<td><%=c.getCompanyName()%></td>
				<td><%=c.getManagerName()%></td>
				<td><%=c.getManagerPhoneNum()%></td>
				<td><%=c.getManagerEmail()%></td>
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
	</div>
</body>
</html>
