<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.bean.User, java.util.*"%>

<%
List<User> list = (List<User>) request.getAttribute("listUserPending");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>顧客審査</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #fff;
	padding: 30px;
	text-align: center;
}

h2 {
	color: #333;
	margin-bottom: 30px;
}

h3 {
	text-align: left;
	margin-bottom: 10px;
}

.table-container {
	display: flex;
	justify-content: center;
	margin-bottom: 20px;
}

table {
	border-collapse: collapse;
	width: 60%;
	background-color: #fff;
}

th, td {
	border: 1px solid #ccc;
	padding: 10px;
	text-align: center;
	font-size: 14px;
}

th {
	background-color: #f0f0f0;
}

.link-btn {
	background: none;
	border: none;
	color: #2c7be5;
	text-decoration: underline;
	cursor: pointer;
	font-size: 14px;
	padding: 0;
	margin: 0 5px;
}

.link-btn:hover {
	color: #1a5fb4;
	text-decoration: underline;
}

.top-btn {
	margin-top: 15px;
}
</style>
</head>
<body>

<h2>顧客審査</h2>

<h3 style="text-align: center;">待機中ユーザー</h3>

<div class="table-container">
	<table>
		<thead>
			<tr>
				<th>会社名</th>
				<th>操作</th>
			</tr>
		</thead>

		<tbody>
			<%
			if (list != null && !list.isEmpty()) {
				for (User user : list) {
			%>
			<tr>
				<td><%= user.getCompanyName() %></td>
				<td>
					<form action="<%=request.getContextPath()%>/newCustomerAllow" method="post" style="display: inline;">
						<input type="hidden" name="userId" value="<%= user.getId() %>">
						<input type="hidden" name="action" value="accept">
						<button type="submit" class="link-btn">承認</button>
					</form>

					|

					<form action="<%=request.getContextPath()%>/newCustomerAllow" method="post" style="display: inline;">
						<input type="hidden" name="userId" value="<%= user.getId() %>">
						<input type="hidden" name="action" value="reject">
						<button type="submit" class="link-btn">不可</button>
					</form>
				</td>
			</tr>
			<%
				}
			} else {
			%>
			<tr>
				<td colspan="2">新規登録がありません。</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
</div>

<div style="text-align: right; width: 60%; margin: 0 auto;">
	<form action="<%=request.getContextPath()%>/admin/managerTop.jsp" method="get">
		<button type="submit">TOPへ戻る</button>
	</form>
</div>

</body>
</html>
